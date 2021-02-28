package com.bfxy.base.rabbit.broker;

import com.bfxy.base.rabbit.rabbitapi.Message;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Kylin Su
 * @date 2021/2/22 2:18 AM
 */
public class MessageHolder {

    private List<Message> messages = Lists.newArrayList();

    //rawtypes, uncheked/
    public static final ThreadLocal<MessageHolder> holder = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new MessageHolder();
        }
    };

    public static void add(Message message) {
        holder.get().messages.add(message);
    }

    public static List<Message> clear() {
        List<Message> tmp = Lists.newArrayList(holder.get().messages);
        holder.remove();
        return tmp;
    }
}
