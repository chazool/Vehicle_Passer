package com.chazool.highwayvehiclepasser.driverservice.exception;

import com.chazool.highwayvehiclepasser.model.responsehandle.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.time.LocalDateTime;

//@ControllerAdvice
public class GlobalExceptionHandler  {


   // @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException runtimeException, HttpStatus httpStatus, WebRequest webRequest) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimeStamp(LocalDateTime.now().toString());
        errorResponse.setMessage(runtimeException.getMessage());
        errorResponse.setPath(webRequest.getDescription(false));
        errorResponse.setStatus(httpStatus.value());
        errorResponse.setError(httpStatus.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, httpStatus);

    }


}
