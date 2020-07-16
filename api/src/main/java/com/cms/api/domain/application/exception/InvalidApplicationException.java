package com.cms.api.domain.application.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class InvalidApplicationException extends BusinessException {

    public InvalidApplicationException() { super(ErrorCode.INVALID_APPLICATION); }
}
