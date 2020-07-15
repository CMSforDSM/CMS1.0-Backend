package com.cms.api.domain.club.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class AlreadyClubMemberException extends BusinessException {

    public AlreadyClubMemberException() { super(ErrorCode.ALREADY_CLUB_MEMBER); }
}
