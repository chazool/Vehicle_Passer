package com.chazool.highwayvehiclepasser.model.responsehandle;

import lombok.Data;

@Data
public class ErrorResponse {

    private String error;
    private String timeStamp;
    private int status;
    private Object message;
    private String path;

    public static void main(String[] args) {
        Response r=Response.fail(null);
        Response v=Response.success(null);

        System.out.println(r);
        System.out.println(v);
        System.out.println(r);
    }

}
