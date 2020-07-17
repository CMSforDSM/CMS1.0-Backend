package com.cms.api.domain.post.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class PostNotFoundException extends BusinessException {

    public PostNotFoundException() { super(ErrorCode.POST_NOT_FOUND); }
}
