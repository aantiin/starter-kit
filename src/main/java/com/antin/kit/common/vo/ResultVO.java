package com.antin.kit.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultVO {

    private Boolean isSuccess;
    private String error;
    private Object data;

    public ResultVO() {
    }

    public ResultVO(boolean isSuccess, String error, Object data) {
        this.isSuccess = isSuccess;
        this.error = error;
        this.data = data;
    }

    @JsonProperty("isSuccess")
    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultVO{");
        sb.append("isSuccess=").append(isSuccess);
        sb.append(", error='").append(error).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
