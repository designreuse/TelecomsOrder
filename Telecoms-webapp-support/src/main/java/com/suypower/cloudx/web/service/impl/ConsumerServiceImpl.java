package com.suypower.cloudx.web.service.impl;

import com.suypower.cloudx.web.dao.ConsumerMapper;
import com.suypower.cloudx.web.entity.Consumer;
import com.suypower.cloudx.web.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhengtao on 2015/12/29.
 */
@Service
public class ConsumerServiceImpl implements IConsumerService {

    private ConsumerMapper consumerMapper;

    @Autowired
    public void setConsumerMapper(ConsumerMapper consumerMapper) {
        this.consumerMapper = consumerMapper;
    }

    @Override
    public List<Consumer> queryConsumers(Map<String, String> params) {
        return consumerMapper.queryConsumers(params);
    }
}
