package com.chazool.highwayvehiclepasser.model.responsehandle;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Response {


    private Status status;
    private boolean action;
    private Object data;

    private enum Status {
        SUCCESS, FAIL, SYSTEM_DOWN
    }

    private Response(Status status, boolean action, Object data) {
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

    public static Response systemDown(Object data) {
        return new Response(Status.SYSTEM_DOWN, false, data);
    }


}
