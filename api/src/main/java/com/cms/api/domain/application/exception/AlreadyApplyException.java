package com.cms.api.domain.application.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class AlreadyApplyException extends BusinessException {
    public AlreadyApplyException() { super(ErrorCode.ALREADY_APPLY); }
}
