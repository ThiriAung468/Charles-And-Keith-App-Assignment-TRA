package com.padcmyanmar.charles.and.keith.assignment.tra.events;

public class ApiErrorEvent {

    private String errorMsg;

    public ApiErrorEvent(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
