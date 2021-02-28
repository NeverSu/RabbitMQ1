package com.bfxy.base.rabbit.rabbitcommon.serializer.impl;

import com.bfxy.base.rabbit.rabbitapi.Message;
import com.bfxy.base.rabbit.rabbitcommon.serializer.Serializer;
import com.bfxy.base.rabbit.rabbitcommon.serializer.SerializerFactory;

/**
 * @author Kylin Su
 * @date 2021/2/17 8:08 PM
 */
public class JacksonSerializerFactory implements SerializerFactory {

    public static final SerializerFactory INSTANCE = new JacksonSerializerFactory();

    @Override
    public Serializer create() {
        return JacksonSerializer.createParameterType(Message.class);
    }
}
