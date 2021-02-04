package com.chazool.highwayvehiclepasser.transsaction.exception;

import com.chazool.highwayvehiclepasser.model.responsehandle.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException runtimeException, HttpStatus httpStatus, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimeStamp(LocalDateTime.now(ZoneId.of("")).toString());
        errorResponse.setMessage(runtimeException.getMessage());
        errorResponse.setError(httpStatus.getReasonPhrase());
        errorResponse.setPath(webRequest.getDescription(false));
        errorResponse.setStatus(httpStatus.value());

        return new ResponseEntity<>(errorResponse, httpStatus);
    }


}
