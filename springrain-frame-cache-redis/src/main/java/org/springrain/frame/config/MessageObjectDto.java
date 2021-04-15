package org.springrain.frame.config;

public class MessageObjectDto<T> {
    /**
     * 消息的ID
     */
    private String messageId;

    /**
     *消息的时间戳
     */
    private Long messageTime;
    /**
     * 队列名称
     */
    private String queueName;
    /**
     * 队列中的对象值
     */
    private T messageObject;

    /**
     * 构造函数
     * @param messageObject
     * @param queueName
     * @param messageId
     * @param messageTime
     */
    protected MessageObjectDto(T messageObject, String queueName, String messageId, Long messageTime){
        this.messageObject =messageObject;
        this.queueName=queueName;
        this.messageId=messageId;
        this.messageTime=messageTime;

    }

    /**
     * 消息的ID
     * @return
     */
    public String getMessageId() {
        return messageId;
    }


    /**
     * 消息的时间戳
     * @return
     */

    public Long getMessageTime() {
        return messageTime;
    }

    /**
     * 队列名称
     */
    public String getQueueName() {
        return queueName;
    }

    /**
     * 队列中的对象值
     * @return
     */
    public T getMessageObject() {
        return messageObject;
    }





}
