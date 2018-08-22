package com.people2000.user.business.write.manage.impl;

import java.util.Map;

import com.people2000.user.business.write.manage.VerificationWriteManage;
import com.people2000.user.model.exception.OuserMangeException;

/**
 * Created by xiaole on 2015/11/5.
 */
public abstract class VerificationWriteManageImpl<IN, OUT> implements
		VerificationWriteManage<IN, OUT> {

	@Override
	public abstract Object generateCode(IN IN);

	@Override
	public abstract void saveCode(String key, Object o, int timeOut);

	@Override
	public abstract Map sendCode(IN IN) throws OuserMangeException;

	@Override
	public abstract Map checkCode(IN IN);
}
