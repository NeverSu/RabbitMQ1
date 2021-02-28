package com.bfxy.base.rabbit.rabbitapi;

/**
 * @author Kylin Su
 * @date 2021/2/16 2:49 AM
 */
public interface SendCallback {

    void onSuccess();

    void onFailure();
}
