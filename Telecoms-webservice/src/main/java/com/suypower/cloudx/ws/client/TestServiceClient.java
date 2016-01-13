package com.suypower.cloudx.ws.client;

/**
 * Created by Bingdor on 2016/1/8.
 */

/**
 * desc: comment TestServiceClient.java
 *
 * @author Chaisson(chengshengwang)
 * @vision 1.0
 * @since May 13, 2011 2:17:04 PM
 */
public class TestServiceClient {
    public static void main(String[] args) throws Exception{
        CloudxAppSupport cloudxAppSupport = CloudxAppSupport.getInstance("http://localhost:8080/ws/cloudxSuiteService",
                "apmserver", "apmserverpass");
        System.out.println("res = [" + cloudxAppSupport.sayHello("Tom") + "]");
    }

}