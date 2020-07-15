package com.cms.api.domain.club.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class ClubDuplicateException extends BusinessException {

    public ClubDuplicateException() { super(ErrorCode.CLUB_DUPLICATE); }
}
