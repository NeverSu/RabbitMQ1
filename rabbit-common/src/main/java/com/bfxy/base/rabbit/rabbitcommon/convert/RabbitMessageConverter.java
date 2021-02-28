package com.bfxy.base.rabbit.rabbitcommon.convert;

import com.google.common.base.Preconditions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.messaging.support.GenericMessage;

/**
 * @author Kylin Su
 * @date 2021/2/22 1:43 AM
 */
public class RabbitMessageConverter implements MessageConverter {

    private GenericMessageConverter delegate;

    public RabbitMessageConverter(GenericMessageConverter genericMessageConverter) {
        Preconditions.checkNotNull(genericMessageConverter);
        this.delegate = genericMessageConverter;
    }

    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        com.bfxy.base.rabbit.rabbitapi.Message message = (com.bfxy.base.rabbit.rabbitapi.Message) o;
        messageProperties.setDelay(message.getDelayMills());
        return this.delegate.toMessage(o, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        com.bfxy.base.rabbit.rabbitapi.Message msg = (com.bfxy.base.rabbit.rabbitapi.Message) this.delegate.fromMessage(message);
        return msg;
    }
}
