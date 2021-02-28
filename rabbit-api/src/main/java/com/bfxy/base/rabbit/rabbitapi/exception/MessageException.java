package com.bfxy.base.rabbit.rabbitapi.exception;

/**
 * @author Kylin Su
 * @date 2021/2/15 11:35 PM
 */
public class MessageException extends Exception {
    private static final long serialVersionUID = 8270530774043851159L;

    public MessageException() {
        super();
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }
}
