package com.suypower.cloudx.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Bingdor on 2016/1/12.
 */
@WebService
public interface ICloudxSuiteService {
    @WebMethod(operationName="sayHello",action="sayHello",exclude=false)
    public String sayHello(@WebParam(name="name") String name);
}
