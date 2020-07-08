package com.cms.api.global.error;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(AuthenticationException.class)
//    private ResponseEntity<ErrorResponse> handleAuthenticationException(final AuthenticationException e) {
//
//    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final int status = errorCode.getStatus();
        return new ResponseEntity<>(new ErrorResponse(status, e.getMessage(),
                errorCode.getCode()), HttpStatus.valueOf(status));
    }
}
