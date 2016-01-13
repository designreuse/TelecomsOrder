package com.suypower.cloudx.ws.exception;

/**
 * Created by Bingdor on 2016/1/12.
 */
public class CloudxSOAException extends Exception {

    public CloudxSOAException() {
    }

    public CloudxSOAException(String message) {
        super(message);
    }

    public CloudxSOAException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloudxSOAException(Throwable cause) {
        super(cause);
    }
}
