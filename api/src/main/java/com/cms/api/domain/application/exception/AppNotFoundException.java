package com.cms.api.domain.application.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class AppNotFoundException extends BusinessException {

    public AppNotFoundException() { super(ErrorCode.APPLICATION_NOT_FOUND); }
}
