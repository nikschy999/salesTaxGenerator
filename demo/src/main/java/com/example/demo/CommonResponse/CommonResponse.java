package com.example.demo.CommonResponse;

public class CommonResponse<T> {
    private int responseCode = 200;
    private T responseObject;
    private boolean isProcessingSuccess = true;

    public int getResponseCode(int i) {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public T getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }

    public boolean isProcessingSuccess() {
        return isProcessingSuccess;
    }

    public void setProcessingSuccess(boolean processingSuccess) {
        isProcessingSuccess = processingSuccess;
    }
}
