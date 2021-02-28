package com.bfxy.base.rabbit.broker;

import com.bfxy.base.rabbit.entity.BrokerMessage;
import com.bfxy.base.rabbit.rabbitapi.Message;
import com.bfxy.base.rabbit.rabbitapi.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Kylin Su
 * @date 2021/2/16 6:51 PM
 */
@Slf4j
@Component
public class RabbitBrokerImpl implements RabbitBroker {

    @Autowired
    private RabbitTemplateContainer rabbitTemplateContainer;

    @Override
    public void rapidSend(Message message) {
        message.setMessageType(MessageType.RAPID);
        sendKernel(message);
    }

    /**
     *  $sendKernel 发送消息的核心方法 使用异步线程池进行发送消息
     * @param message
     */
    private void sendKernel(Message message) {
        AsyncBaseQueue.submit((Runnable) () -> {
            CorrelationData correlationData =
                    new CorrelationData(String.format("%s#%s#%s",
                            message.getMessageId(),
                            System.currentTimeMillis(),
                            message.getMessageType()));
            String topic = message.getTopic();
            String routingKey = message.getRoutingKey();
            RabbitTemplate rabbitTemplate = rabbitTemplateContainer.getTemplate(message);
            rabbitTemplate.convertAndSend(topic, routingKey, message, correlationData);
            log.info("#RabbitBrokerImpl.sendKernel# send to rabbitmq, messageId: {}", message.getMessageId());
        });
    }

    @Override
    public void confirmSend(Message message) {
        message.setMessageType(MessageType.CONFIRM);
        sendKernel(message);
    }

    @Override
    public void reliantSend(Message message) {
        message.setMessageType(MessageType.RELIANT);

    }

    @Override
    public void sendMessages() {

    }
}
