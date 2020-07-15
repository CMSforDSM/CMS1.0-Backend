package com.cms.api.domain.club.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class ClubNotFoundException extends BusinessException {

    public ClubNotFoundException() { super(ErrorCode.CLUB_NOT_FOUND); }
}
