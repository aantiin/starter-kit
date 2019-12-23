package com.antin.kit.common.exception;


import com.antin.kit.common.util.StatusCode;

public class ServiceException extends RuntimeException {
    private StatusCode code = StatusCode.ERROR;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }

    public ServiceException(StatusCode code, String message) {
        super(message);
        this.code = code;
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
    }
}
