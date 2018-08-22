package com.people2000.user.model.exception;

import com.people2000.common.exception.BusinessException;

/**
 * Created by xiaole on 2015/11/6.
 */
public class OuserMangeException extends BusinessException {
	public OuserMangeException(String errorCode) {
		super(errorCode);
	}

	public OuserMangeException(String errorCode, String message) {
		super(errorCode, message);
	}

	public OuserMangeException(String errorCode, String message,
			Throwable throwable) {
		super(errorCode, message, throwable);
	}

	public OuserMangeException(String code, Throwable throwable) {
		super(code, throwable);
	}

	public OuserMangeException(String errorCode, Object... arguments) {
		super(errorCode, arguments);
	}
}
