package com.cms.api.domain.comment.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class CommentNotFoundException extends BusinessException {

    public CommentNotFoundException() { super(ErrorCode.COMMENT_NOT_FOUND); }
}
