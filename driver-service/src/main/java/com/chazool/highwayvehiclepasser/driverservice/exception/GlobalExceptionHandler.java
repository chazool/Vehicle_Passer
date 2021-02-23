package com.chazool.highwayvehiclepasser.driverservice.exception;

import com.chazool.highwayvehiclepasser.model.responsehandle.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneId;

//@ControllerAdvice
public class GlobalExceptionHandler {


/*  @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException runtimeException, HttpStatus httpStatus, WebRequest webRequest) {
        System.out.println("===>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 1111111111111111111");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimeStamp(LocalDateTime.now(ZoneId.of("Asia/Colombo")).toString());
        errorResponse.setMessage(runtimeException.getMessage());
        errorResponse.setPath(webRequest.getDescription(false));
        errorResponse.setStatus(httpStatus.value());
        errorResponse.setError(httpStatus.getReasonPhrase());
        System.out.println("===>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 222222222222222222");

        return new ResponseEntity<>(errorResponse, httpStatus);

    }*/




}
