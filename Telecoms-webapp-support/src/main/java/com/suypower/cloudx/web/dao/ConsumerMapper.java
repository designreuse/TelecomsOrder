package com.suypower.cloudx.web.dao;

import com.suypower.cloudx.web.entity.Consumer;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhengtao on 2015/12/29.
 */
public interface ConsumerMapper {

    /**
     * 根据条件查询客户列表
     * @param params
     * @return
     */
    public List<Consumer> queryConsumers(Map<String, String> params);

}
