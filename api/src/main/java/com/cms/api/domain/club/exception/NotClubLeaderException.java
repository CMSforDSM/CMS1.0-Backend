package com.cms.api.domain.club.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class NotClubLeaderException extends BusinessException {

    public NotClubLeaderException() { super(ErrorCode.NOT_CLUB_LEADER); }
}
