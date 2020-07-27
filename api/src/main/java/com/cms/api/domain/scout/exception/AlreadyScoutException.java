package com.cms.api.domain.scout.exception;

import com.cms.api.domain.application.exception.AlreadyApplyException;
import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class AlreadyScoutException extends BusinessException {
    public AlreadyScoutException() { super(ErrorCode.ALREADY_SCOUT); }
}
