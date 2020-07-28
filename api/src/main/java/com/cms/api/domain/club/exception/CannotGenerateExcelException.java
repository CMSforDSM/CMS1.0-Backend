package com.cms.api.domain.club.exception;

import com.cms.api.domain.comment.exception.CannotAddCommentException;
import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class CannotGenerateExcelException extends BusinessException {
    public CannotGenerateExcelException() { super(ErrorCode.CANNOT_GENERATE_EXCEL); }
}
