package com.people2000.user.business.write.manage;

import java.util.Map;

import com.people2000.user.model.exception.OuserMangeException;

/**
 * Created by xiaole on 2015/11/4.
 */
public interface VerificationWriteManage<IN, OUT> {
	// 产生验证码
	public Object generateCode(IN IN);

	// 保存code
	public void saveCode(String key, Object o, int timeOut);

	// 发送验证码
	public Map sendCode(IN IN) throws OuserMangeException;

	// 校验验证码
	public Map checkCode(IN IN);

}
