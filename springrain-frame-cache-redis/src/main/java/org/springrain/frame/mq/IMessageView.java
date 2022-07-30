package org.springrain.frame.mq;


import org.springrain.frame.util.Page;

import java.util.List;

/**
 * @ClassName IMessageView
 * @Description 消息队列中的消息查看
 * @date 2022/7/30 12:23
 * @Auther ysk
 * @Version 1.0
 */
public interface IMessageView<T> {
    /**
     * 获取未确认的消息
     * @param size 返回值List的大小
     * @return 消息列表
     */
    List<MessageObjectDto<T>> getUnAckMessage(Integer size);

    /**
     * 分页获取所有消息
     */
    Page<List<MessageObjectDto<T>>> getMessagePage(int pageNo, int pageSize);
}
