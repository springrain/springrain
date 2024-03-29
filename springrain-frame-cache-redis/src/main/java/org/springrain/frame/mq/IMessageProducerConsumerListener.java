package org.springrain.frame.mq;

import org.springrain.frame.util.Page;

import java.util.List;

/**
 * 用于声明注入的监听器接口,使用了JDK动态代理,必须使用这个接口声明注入实现
 * AbstractMessageProducerConsumerListener 实现了StreamListener和IMessageProducerConsumerListener两个接口,
 * 注入使用IMessageProducerConsumerListener接口,也就屏蔽了StreamListener原生接口,避免调错方法.
 * 具体的监听器实现 还是要继承 AbstractMessageProducerConsumerListener
 * 例如 </br>
 * <code>
 *
 * @param <T> 需要放入队列的对象
 * @Component("userMessageProducerConsumerListener") public class UserMessageProducerConsumerListener extends AbstractMessageProducerConsumerListener<User>
 * <p>
 * //sendProducerMessage 方法实现 return super.sendProducerMessage();
 *
 * </code>
 *
 * <code>
 * @Resource IMessageProducerConsumerListener<User> userMessageProducerConsumerListener;
 * </code>
 */
public interface IMessageProducerConsumerListener<T> {
    /**
     * 不要手动调用这个方法!!!!!!!!!不要手动调用这个方法!!!!!!!!!不要手动调用这个方法!!!!!!!!!
     * <p>
     * 消费消息,隔离Redis API,如果返回true则自动应答,如果返回false,认为消息处理失败.
     * 暂时注释这个方法,避免手动挡调用,这个方法是 StreamListener的onMessage主动调用的,正常不需要手动调用
     * 还是要留着这个方法,用于增加 类似 @Transactional 事务注解扩展,因为是面向接口注入的
     *
     * @param messageObjectDto
     * @return
     */
    boolean onMessage(MessageObjectDto<T> messageObjectDto) throws Exception;

    /**
     * 重试消息,项目启动时会重试一次,业务代码自行实现根据调度重试
     * 避免死循环,最多1000次.如果单次返回的所有消息都是异常的,终止重试.
     * 如果全部重试成功,返回null.如果还有部分失败,就返回失败的消息记录
     *
     * @return 返回重试失败的消息记录对象
     */
    List<MessageObjectDto<T>> retryFailMessage() throws Exception;

    /**
     * 生产者向消息队列发送消息
     *
     * @param message
     * @return
     */
    MessageObjectDto<T> sendProducerMessage(T message) throws Exception;

    /**
     * 消息队列的名称,redis里就是stream的名称
     *
     * @return
     */
    String getQueueName();


    /**
     * 是否可用,默认为true
     * @return
     */
    default  boolean getEnable(){
        return true;
    }
    /**
     * 获取未确认的消息
     * @param size 返回值List的大小
     * @return 消息列表
     */
    List getUnAckMessage(Integer size);

    /**
     * 分页获取所有消息
     */
    Page<List<MessageObjectDto<T>>> getMessagePage(int pageNo, int pageSize);

    /**
     * 强制应答指定消息,不考虑业务执行结果
     * @param messageId 消息id
     * @param isRetryBusiness 已应答过得消息是否重试业务逻辑，
     *                        true:须考虑消息消费者的幂等性处理，每次调用都会重试业务逻辑
     *                        false:消息在调用此方法之前未应答成功的，最少执行一次业务逻辑
     */
    boolean forceAckMessage(String messageId, boolean isRetryBusiness);

}
