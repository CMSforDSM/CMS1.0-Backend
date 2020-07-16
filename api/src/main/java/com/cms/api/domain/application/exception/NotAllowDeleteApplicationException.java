package com.cms.api.domain.application.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class NotAllowDeleteApplicationException extends BusinessException {

    public NotAllowDeleteApplicationException() { super(ErrorCode.NOT_ALLOW_DELETE_APPLICATION); }
}
