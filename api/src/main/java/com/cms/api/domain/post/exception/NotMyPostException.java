package com.cms.api.domain.post.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class NotMyPostException extends BusinessException {

    public NotMyPostException() { super(ErrorCode.NOT_MY_POST); }
}
