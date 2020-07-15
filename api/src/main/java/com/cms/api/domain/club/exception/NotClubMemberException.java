package com.cms.api.domain.club.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class NotClubMemberException extends BusinessException {

    public NotClubMemberException() { super(ErrorCode.NOT_CLUB_MEMBER); }
}
