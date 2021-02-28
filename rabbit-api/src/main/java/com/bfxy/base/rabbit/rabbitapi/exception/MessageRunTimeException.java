package com.bfxy.base.rabbit.rabbitapi.exception;

/**
 * @author Kylin Su
 * @date 2021/2/15 11:35 PM
 */
public class MessageRunTimeException extends RuntimeException {
    private static final long serialVersionUID = -3009252987780354592L;

    public MessageRunTimeException() {
        super();
    }

    public MessageRunTimeException(String message) {
        super(message);
    }

    public MessageRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageRunTimeException(Throwable cause) {
        super(cause);
    }
}
