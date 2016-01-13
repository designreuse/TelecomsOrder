package com.suypower.cloudx.web.service.impl;

import com.suypower.cloudx.web.dao.OverviewMapper;
import com.suypower.cloudx.web.service.IOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bingdor on 2015/12/28.
 */
@Service
public class OverviewServiceImpl implements IOverviewService {
    @Autowired
    private OverviewMapper overviewMapper;

    public Map<String,Object> findOverviewData(String orgNo) {
        String[] keys = new String[]{"RCV_AMT","OWE_AMT","ROOM_AREA","CONS_NUM"};
        String[] rates = new String[]{"RCV_AMT_RATE","OWE_AMT_RATE","ROOM_AREA_RATE","CONS_NUM_RATE"};
        Map<String,Object> map = new HashMap<String, Object>();
        for (int i = 0;i < keys.length;i++){
            map.put(keys[i],String.format("%.2f",Math.random()*1000000));
            map.put(rates[i],String.format("%.2f%%",Math.random()*100));
        }
        return map;
    }

    public List<Map<?, ?>> findYearMonthRcv(String orgNo) {
        return overviewMapper.queryYearMonthRcv(orgNo);
    }

    public Map<?, ?> findYearCharge(String orgNo) {
        return overviewMapper.queryYearCharge(orgNo);
    }

}
