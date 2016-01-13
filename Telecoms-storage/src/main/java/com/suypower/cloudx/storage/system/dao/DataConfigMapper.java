package com.suypower.cloudx.storage.system.dao;

import com.suypower.cloudx.storage.system.entity.DataConfig;

/**
 * Created by Bingdor on 2015/12/8.
 */
public interface DataConfigMapper {
    public DataConfig queryDataConfig(String configName);
}
