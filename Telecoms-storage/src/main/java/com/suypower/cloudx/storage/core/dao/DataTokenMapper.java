package com.suypower.cloudx.storage.core.dao;

import com.suypower.cloudx.storage.core.entity.DataToken;

/**
 * Created by Bingdor on 2015/12/8.
 */
public interface DataTokenMapper {
    public DataToken queryDataToken(String token);

    public Integer insertDataToken(DataToken dataToken);

    public Integer updateDataToken(String token);
}
