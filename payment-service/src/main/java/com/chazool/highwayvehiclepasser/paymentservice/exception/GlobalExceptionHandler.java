package com.chazool.highwayvehiclepasser.paymentservice.exception;

import com.chazool.highwayvehiclepasser.model.responsehandle.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException runtimeException, HttpStatus httpStatus, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimeStamp(LocalDateTime.now().toString());
        errorResponse.setMessage(runtimeException.getMessage());
        errorResponse.setError(httpStatus.getReasonPhrase());
        errorResponse.setStatus(httpStatus.value());
        errorResponse.setPath(webRequest.getDescription(false));

        return new ResponseEntity<>(errorResponse, httpStatus);
    }


}
