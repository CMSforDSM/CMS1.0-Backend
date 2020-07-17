package com.cms.api.domain.comment.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class CannotAddCommentException extends BusinessException {

    public CannotAddCommentException() { super(ErrorCode.CANNOT_ADD_COMMENT); }
}
