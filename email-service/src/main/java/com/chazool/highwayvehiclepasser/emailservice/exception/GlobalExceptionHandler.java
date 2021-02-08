package com.chazool.highwayvehiclepasser.emailservice.exception;

import com.chazool.highwayvehiclepasser.model.responsehandle.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException runtimeException, HttpStatus httpStatus, WebRequest webRequest) {
        return getResponse(runtimeException, httpStatus, webRequest);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ErrorResponse> handleMessagingException(MessagingException messagingException, HttpStatus httpStatus, WebRequest webRequest) {
        return getResponse(messagingException, httpStatus, webRequest);
    }

    private ResponseEntity<ErrorResponse> getResponse(Exception exception, HttpStatus httpStatus, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimeStamp(LocalDateTime.now(ZoneId.of("Asia/Colombo")).toString());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(httpStatus.value());
        errorResponse.setError(httpStatus.getReasonPhrase());
        errorResponse.setPath(webRequest.getDescription(false));

        return new ResponseEntity<>(errorResponse, httpStatus);
    }


}
