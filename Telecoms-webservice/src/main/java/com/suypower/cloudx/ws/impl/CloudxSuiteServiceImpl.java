package com.suypower.cloudx.ws.impl;

import com.suypower.cloudx.ws.ICloudxSuiteService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * Created by Bingdor on 2016/1/12.
 */
@Component
@WebService(serviceName = "cloudxSuiteService",
        endpointInterface = "com.suypower.cloudx.ws.ICloudxSuiteService",
        targetNamespace = "http://ws.cloudx.suypower.com/")
public class CloudxSuiteServiceImpl implements ICloudxSuiteService {

    @Override
    public String sayHello(String name) {
        return "Hello,"+name+"!";
    }
}
