package com.bfxy.base.rabbit.broker;

import com.bfxy.base.rabbit.rabbitapi.Message;

/**
 * @author Kylin Su
 * @date 2021/2/16 6:49 PM
 */
public interface RabbitBroker {

    void rapidSend(Message message);

    void confirmSend(Message message);

    void reliantSend(Message message);

    void sendMessages();

}
