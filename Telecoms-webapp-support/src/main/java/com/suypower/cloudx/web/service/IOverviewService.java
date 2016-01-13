package com.suypower.cloudx.web.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Bingdor on 2015/12/28.
 */
public interface IOverviewService {
    public Map<String,Object> findOverviewData(String orgNo);

    public List<Map<?,?>> findYearMonthRcv(String orgNo);

    public Map<?,?> findYearCharge(String orgNo);
}
