package com.bfxy.base.rabbit.rabbitcommon.serializer;

/**
 * @author Kylin Su
 * @date 2021/2/17 8:09 PM
 */
public interface Serializer {

    byte[] serializeRaw(Object data);

    String serialize(Object data);

    <T> T deserialize(String content);

    <T> T deserialize(byte[] content);
}
