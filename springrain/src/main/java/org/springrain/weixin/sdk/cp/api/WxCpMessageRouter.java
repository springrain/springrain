package org.springrain.weixin.sdk.cp.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.weixin.sdk.common.api.WxErrorExceptionHandler;
import org.springrain.weixin.sdk.common.api.WxMessageDuplicateChecker;
import org.springrain.weixin.sdk.common.api.WxMessageInMemoryDuplicateChecker;
import org.springrain.weixin.sdk.common.util.LogExceptionHandler;
import org.springrain.weixin.sdk.cp.bean.WxCpXmlMessage;
import org.springrain.weixin.sdk.cp.bean.WxCpXmlOutMessage;

/**
 * <pre>
 * 微信消息路由器，通过代码化的配置，把来自微信的消息交给handler处理
 *
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link WxCpMessageRouterRule#next()}
 * 3. 规则的结束必须用{@link WxCpMessageRouterRule#end()}或者{@link WxCpMessageRouterRule#next()}，否则不会生效
 *
 * 使用方法：
 * WxCpMessageRouter router = new WxCpMessageRouter();
 * router
 *   .rule()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *       // 另外一个匹配规则
 *   .end()
 * ;
 *
 * // 将WxXmlMessage交给消息路由器
 * router.route(message);
 *
 * </pre>
 *
 * @author springrain
 */
public class WxCpMessageRouter {

  private static final int DEFAULT_THREAD_POOL_SIZE = 100;
  protected final Logger log = LoggerFactory.getLogger(WxCpMessageRouter.class);
  private final List<WxCpMessageRouterRule> rules = new ArrayList<>();

  private final WxCpService wxCpService;

  private ExecutorService executorService;

  private WxMessageDuplicateChecker messageDuplicateChecker;


  private WxErrorExceptionHandler exceptionHandler;

  public WxCpMessageRouter(WxCpService wxCpService) {
    this.wxCpService = wxCpService;
    this.executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
    this.messageDuplicateChecker = new WxMessageInMemoryDuplicateChecker();
    this.exceptionHandler = new LogExceptionHandler();
  }

  /**
   * <pre>
   * 设置自定义的 {@link ExecutorService}
   * 如果不调用该方法，默认使用 Executors.newFixedThreadPool(100)
   * </pre>
   *
   * @param executorService
   */
  public void setExecutorService(ExecutorService executorService) {
    this.executorService = executorService;
  }

  /**
   * <pre>
   * 设置自定义的 {@link org.springrain.weixin.sdk.common.api.WxMessageDuplicateChecker}
   * 如果不调用该方法，默认使用 {@link org.springrain.weixin.sdk.common.api.WxMessageInMemoryDuplicateChecker}
   * </pre>
   *
   * @param messageDuplicateChecker
   */
  public void setMessageDuplicateChecker(WxMessageDuplicateChecker messageDuplicateChecker) {
    this.messageDuplicateChecker = messageDuplicateChecker;
  }

  /**
   * <pre>
   * 设置自定义的{@link org.springrain.weixin.sdk.common.api.WxErrorExceptionHandler}
   * 如果不调用该方法，默认使用 {@link org.springrain.weixin.sdk.common.util.LogExceptionHandler}
   * </pre>
   *
   * @param exceptionHandler
   */
  public void setExceptionHandler(WxErrorExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  List<WxCpMessageRouterRule> getRules() {
    return this.rules;
  }

  /**
   * 开始一个新的Route规则
   */
  public WxCpMessageRouterRule rule() {
    return new WxCpMessageRouterRule(this);
  }

  /**
   * 处理微信消息
   *
   * @param wxMessage
   */
  @SuppressWarnings("rawtypes")
public WxCpXmlOutMessage route(final WxCpXmlMessage wxMessage) {
    if (isDuplicateMessage(wxMessage)) {
      // 如果是重复消息，那么就不做处理
      return null;
    }

    final List<WxCpMessageRouterRule> matchRules = new ArrayList<>();
    // 收集匹配的规则
    for (final WxCpMessageRouterRule rule : this.rules) {
      if (rule.test(wxMessage)) {
        matchRules.add(rule);
        if (!rule.isReEnter()) {
          break;
        }
      }
    }

    if (matchRules.size() == 0) {
      return null;
    }

    WxCpXmlOutMessage res = null;
    final List<Future> futures = new ArrayList<>();
    for (final WxCpMessageRouterRule rule : matchRules) {
      // 返回最后一个非异步的rule的执行结果
      if (rule.isAsync()) {
        futures.add(
          this.executorService.submit(new Runnable() {
            @Override
            public void run() {
              rule.service(wxMessage, WxCpMessageRouter.this.wxCpService, WxCpMessageRouter.this.exceptionHandler);
            }
          })
        );
      } else {
        res = rule.service(wxMessage, this.wxCpService, this.exceptionHandler);
        // 在同步操作结束，session访问结束
        this.log.debug("End session access: async=false, sessionId={}", wxMessage.getFromUserName());
      }
    }

    if (futures.size() > 0) {
      this.executorService.submit(new Runnable() {
        @Override
        public void run() {
          for (Future future : futures) {
            try {
              future.get();
              WxCpMessageRouter.this.log.debug("End session access: async=true, sessionId={}", wxMessage.getFromUserName());
              // 异步操作结束，session访问结束
            } catch (InterruptedException e) {
              WxCpMessageRouter.this.log.error("Error happened when wait task finish", e);
            } catch (ExecutionException e) {
              WxCpMessageRouter.this.log.error("Error happened when wait task finish", e);
            }
          }
        }
      });
    }
    return res;
  }

  protected boolean isDuplicateMessage(WxCpXmlMessage wxMessage) {

    String messageId = "";
    if (wxMessage.getMsgId() == null) {
      messageId = String.valueOf(wxMessage.getCreateTime())
        + "-" + String.valueOf(wxMessage.getAgentId() == null ? "" : wxMessage.getAgentId())
        + "-" + wxMessage.getFromUserName()
        + "-" + String.valueOf(wxMessage.getEventKey() == null ? "" : wxMessage.getEventKey())
        + "-" + String.valueOf(wxMessage.getEvent() == null ? "" : wxMessage.getEvent())
      ;
    } else {
      messageId = String.valueOf(wxMessage.getMsgId());
    }

    return this.messageDuplicateChecker.isDuplicate(messageId);

  }


}