package com.suypower.cloudx.web.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Bingdor on 2015/12/28.
 */
public interface OverviewMapper {
    public List<Map<?,?>> queryOverviewData(String orgNo);

    public List<Map<?,?>> queryYearMonthRcv(String orgNo);

    public Map<?,?> queryYearCharge(String orgNo);
}
