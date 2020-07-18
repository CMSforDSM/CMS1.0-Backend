package com.cms.api.domain.scout.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class NotMyScoutException extends BusinessException {

    public NotMyScoutException() { super(ErrorCode.NOT_MY_SCOUT); }
}
