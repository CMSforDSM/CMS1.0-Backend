package com.cms.api.domain.scout.exception;

import com.cms.api.global.error.exception.BusinessException;
import com.cms.api.global.error.exception.ErrorCode;

public class ScoutNotFoundException extends BusinessException {

    public ScoutNotFoundException() { super(ErrorCode.SCOUT_NOT_FOUND); }
}
