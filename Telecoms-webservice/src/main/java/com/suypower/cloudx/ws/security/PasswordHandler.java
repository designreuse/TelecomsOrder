package com.suypower.cloudx.ws.security;

/**
 * Created by Bingdor on 2016/1/8.
 */

import org.apache.ws.security.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PasswordHandler implements CallbackHandler {

    private Map<String, String> passwords = new HashMap<String, String>();

    public PasswordHandler() {
        passwords.put("apmserver", "apmserverpass");
        passwords.put("apmclient", "apmclientpass");
    }

    public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        String id = pc.getIdentifier();
        pc.setPassword("apmserverpass");
    }
}
