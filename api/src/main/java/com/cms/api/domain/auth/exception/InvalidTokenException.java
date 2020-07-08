package com.cms.api.domain.auth.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException() {super(ErrorCode.INVALID_TOKEN);};
}
