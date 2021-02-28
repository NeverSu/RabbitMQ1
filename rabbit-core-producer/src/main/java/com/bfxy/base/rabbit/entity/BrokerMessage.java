package com.bfxy.base.rabbit.entity;

import com.bfxy.base.rabbit.rabbitapi.Message;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * $BrokerMessage 消息记录表实体映射
 *
 * @author Kylin Su
 * @date 2021/2/16 11:13 PM
 */
@Data
public class BrokerMessage implements Serializable {
    private static final long serialVersionUID = 5804412026224614301L;
    private String messageId;
    private Message message;
    private Integer tryCount = 0;
    private String status;
    private Date nextRetry;
    private Date createTime;
    private Date updateTime;
}
