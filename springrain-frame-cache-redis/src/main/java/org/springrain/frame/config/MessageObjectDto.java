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

    protected MessageObjectDto(T messageObject, String queueName, String messageId, Long messageTime){
        this.messageObject =messageObject;
        this.queueName=queueName;
        this.messageId=messageId;
        this.messageTime=messageTime;

    }


    public String getMessageId() {
        return messageId;
    }



    public Long getMessageTime() {
        return messageTime;
    }


    public String getQueueName() {
        return queueName;
    }


    public T getMessageObject() {
        return messageObject;
    }





}
