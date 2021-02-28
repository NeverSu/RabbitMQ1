package com.bfxy.base.rabbit.broker;

import com.bfxy.base.rabbit.rabbitapi.Message;
import com.bfxy.base.rabbit.rabbitapi.MessageType;
import com.bfxy.base.rabbit.rabbitapi.exception.MessageRunTimeException;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *  $RabbitTemplateContainer 池化Template封装
 *  池化的优点：每一个topic对应一个RabbitTemplate
 *  1、这样可以提高发送的效率
 *  2、可以根据不同的需求定制不同的RabbitTemplate，比如每一个topic都有一个自己的routingKey 规则
 * @author Kylin Su
 * @date 2021/2/16 7:11 PM
 */
@Slf4j
@Component
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback {

    private Map<String  /* TOPIC */, RabbitTemplate> rabbitMap = Maps.newConcurrentMap();

    private Splitter splitter = Splitter.on("#");

    @Autowired
    private ConnectionFactory connectionFactory;

    public RabbitTemplate getTemplate(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message);
        String topic = message.getTopic();
        RabbitTemplate rabbitTemplate = rabbitMap.get(topic);
        if (rabbitTemplate != null) {
            return rabbitTemplate;
        }
        log.info("#RabbitTemplateContainer.getTemplate# topic: {} is not exists, create one", topic);

        RabbitTemplate newTemplate = new RabbitTemplate(connectionFactory);
        newTemplate.setExchange(topic);
        newTemplate.setRoutingKey(message.getRoutingKey());
        // 对于message的序列化方式
        newTemplate.setRetryTemplate(new RetryTemplate());
        // 只要不是rapid类型的消息，都需要进行confirm回调
        String messageType = message.getMessageType();
        if (!MessageType.RAPID.equals(messageType)) {
            newTemplate.setConfirmCallback(this);
        }

        rabbitMap.putIfAbsent(topic, newTemplate);
        return rabbitMap.get(topic);
    }

    /**
     * 无论是confirm 消息 还是reliant 消息， 发送消息以后broker都会去回调confirm
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 具体的消息应答
        // correlationData的Id按照#号进行了分隔，0是messageID，1是sendTime
        List<String> strings = splitter.splitToList(correlationData.getId());
        String messageId = strings.get(0);
        long sendTime = Long.parseLong(strings.get(1));
        String messageType = strings.get(2);
        if (ack) {
            // 当Broker 返回ACK成功时，就是更新一下日志表里对应的消息发送状态为 SEND_OK

            // 如果当前消息类型为reliant 我们就去数据库查找并进行更新
            if (MessageType.RELIANT.endsWith(messageType)) {

            }
            log.info("send message is OK, confirm messageId: {}, sendTime: {}", messageId, sendTime);
        } else {
            log.error("send message is Fail, confirm messageId: {}, sendTime: {}", messageId, sendTime);
        }
    }
}
