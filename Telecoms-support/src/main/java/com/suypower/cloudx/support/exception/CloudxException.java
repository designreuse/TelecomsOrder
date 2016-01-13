package com.suypower.cloudx.support.exception;

/**
 * Created by Bingdor on 2015/11/13.
 */
public class CloudxException extends Exception {
    public CloudxException() {
    }

    public CloudxException(String message) {
        super(message);
    }

    public CloudxException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloudxException(Throwable cause) {
        super(cause);
    }
}
