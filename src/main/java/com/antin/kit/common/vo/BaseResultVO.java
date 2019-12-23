package com.antin.kit.common.vo;

public class BaseResultVO {
    private Boolean isSuccess;
    private Object error;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseResultVO{");
        sb.append("isSuccess=").append(isSuccess);
        sb.append(", error=").append(error);
        sb.append('}');
        return sb.toString();
    }
}
