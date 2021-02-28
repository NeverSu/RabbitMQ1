package com.bfxy.base.rabbit.rabbitapi;

/**
 * @author Kylin Su
 * @date 2021/2/17 8:06 PM
 */
public interface MessageListener {

    public void onMessage(Message message);
}
