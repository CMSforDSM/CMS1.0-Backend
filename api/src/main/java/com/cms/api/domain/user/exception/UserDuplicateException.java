package com.cms.api.domain.user.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class UserDuplicateException extends BusinessException {

    public UserDuplicateException() { super(ErrorCode.USER_DUPLICATE); }
}
