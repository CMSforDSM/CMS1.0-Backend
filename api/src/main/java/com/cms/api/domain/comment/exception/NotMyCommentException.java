package com.cms.api.domain.comment.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class NotMyCommentException extends BusinessException {

    public NotMyCommentException() { super(ErrorCode.NOT_MY_COMMENT); }
}
