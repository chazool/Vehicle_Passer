package com.chazool.highwayvehiclepasser.model.responsehandle;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Response {


    private Status status;
    private Boolean action;
    private Object data;

    private enum Status {
        SUCCESS, FAIL
    }

    private Response(Status status, Boolean action, Object data) {
        this.status = status;
        this.action = action;
        this.data = data;
    }

    public static Response success(Object data) {
        return new Response(Status.SUCCESS, true, data);
    }

    public static Response fail(Object data) {
        return new Response(Status.FAIL, false, data);
    }


}
