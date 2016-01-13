package com.suypower.cloudx.ws.client;

import com.suypower.cloudx.ws.exception.CloudxSOAException;
import com.suypower.cloudx.ws.ICloudxSuiteService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bingdor on 2016/1/12.
 */
public class CloudxAppSupport {
    private static CloudxAppSupport instance;
    private String serviceUrl;
    private String username;
    private String password;

    private CloudxAppSupport(String serviceUrl, String username, String password) {
        this.serviceUrl = serviceUrl;
        this.username = username;
        this.password = password;
    }

    public static CloudxAppSupport getInstance(String url, String username, String password) {
        instance = instance == null ? new CloudxAppSupport(url, username, password) : instance;
        return instance;
    }
    private ICloudxSuiteService createFactory(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ICloudxSuiteService.class);
        factory.setAddress(serviceUrl);
        Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        outProps.put(WSHandlerConstants.USER,"CloudxWsUser");
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler(){
            @Override
            public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
                WSPasswordCallback passwordCallback = (WSPasswordCallback) callbacks[0];
                passwordCallback.setIdentifier(username);
                passwordCallback.setPassword(password);
            }
        });
        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        factory.getOutInterceptors().add(wssOut);
        ICloudxSuiteService cloudxSuiteService =(ICloudxSuiteService)factory.create();
        return cloudxSuiteService;
    }

    public String sayHello(String name) throws CloudxSOAException{
        ICloudxSuiteService cloudxSuiteService = createFactory();
        try {
            return cloudxSuiteService.sayHello(name);
        }catch (Exception e){
            throw new CloudxSOAException(e);
        }
    }

}
