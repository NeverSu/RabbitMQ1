package com.bfxy.base.rabbit.service;

import com.bfxy.base.rabbit.constant.BrokerMessageStatus;
import com.bfxy.base.rabbit.entity.BrokerMessage;
import com.bfxy.base.rabbit.mapper.BrokerMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Kylin Su
 * @date 2021/2/22 2:50 AM
 */
@Service
public class MessageStoreService {

    @Autowired
    private BrokerMessageMapper brokerMessageMapper;

    public int insert(BrokerMessage brokerMessage) {
        return this.brokerMessageMapper.insert(brokerMessage);
    }

    public void success(String messageId) {
        this.brokerMessageMapper.changeBrokerMessageStatus(messageId,
                BrokerMessageStatus.SEND_OK.getCode(),
                new Date());
    }

    public void failure(String messageId) {
        this.brokerMessageMapper.changeBrokerMessageStatus(messageId,
                BrokerMessageStatus.SEND_FAIL.getCode(),
                new Date());
    }

    public List<BrokerMessage> fetchTimeOutMessage4Retry(BrokerMessageStatus  brokerMessageStatus){
        return this.brokerMessageMapper.queryBrokerMessageStatus4Timeout(brokerMessageStatus.getCode());
    }

    public int updateTryCount(String brokerMessageId) {
        return this.brokerMessageMapper.update4TryCount(brokerMessageId, new Date());
    }
}
