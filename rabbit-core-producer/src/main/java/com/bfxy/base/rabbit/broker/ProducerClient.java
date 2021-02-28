package com.bfxy.base.rabbit.broker;

import com.bfxy.base.rabbit.rabbitapi.Message;
import com.bfxy.base.rabbit.rabbitapi.MessageProducer;
import com.bfxy.base.rabbit.rabbitapi.SendCallback;
import com.bfxy.base.rabbit.rabbitapi.exception.MessageRunTimeException;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * $ProducerClient 发送消息的实际实现类
 * @author Kylin Su
 * @date 2021/2/16 3:23 AM
 */
@Component
public class ProducerClient implements MessageProducer {

    @Override
    public void send(Message message, SendCallback sendCallback) throws MessageRunTimeException {
        Preconditions.checkNotNull(message.getTopic());

    }

    @Override
    public void send(Message message) throws MessageRunTimeException {

    }

    @Override
    public void send(List<Message> messages) throws MessageRunTimeException {

    }
}
