package com.suypower.cloudx.storage.exception;

/**
 * Created by Bingdor on 2015/12/8.
 */
public class DataException extends Exception {
    public DataException() {
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataException(Throwable cause) {
        super(cause);
    }
}
