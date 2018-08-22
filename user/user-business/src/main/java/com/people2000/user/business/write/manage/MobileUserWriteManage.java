package com.people2000.user.business.write.manage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.model.vo.UserInfoFromOldVO;

public interface MobileUserWriteManage {

	/**
	 * 用户修改密码
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 
	 * </pre>
	 */
	HashMap<String, Object> updatePassWordWithTx(MobileUserVO userDto)
			throws Exception;

	/**
	 * 注册
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 
	 * </pre>
	 */
	HashMap<String, Object> registerWithTx(MobileUserVO userDto)
			throws Exception;

	/**
	 * 发送验证码
	 *
	 * @param captchas
	 * @return string code
	 */
	HashMap<String, Object> sendCodeWithTx(MobileUserVO captchas)
			throws Exception;

	/**
	 * 发送验证码
	 *
	 * @param captchas
	 * @return string code
	 */
	HashMap<String, Object> sendCaptchasWithTx(MobileUserVO captchas,
			HttpServletRequest request) throws Exception;

	/**
	 * 校验验证码
	 *
	 * @param captchas
	 * @return
	 * @throws Exception
	 */
	HashMap<String, Object> checkCodeWithTx(MobileUserVO captchas)
			throws Exception;

	/**
	 * 校验手机号码和用户名是否匹配
	 *
	 * @param userVO
	 * @return
	 */
	HashMap<String, Object> checkUsernameAndMobile(MobileUserVO userVO)
			throws SQLException;

	/**
	 * 根据id更新用户
	 *
	 * @param user
	 * @return
	 */
	HashMap<String, Object> updateByIdWithTx(User user) throws SQLException;

	/**
	 * 查询手机号码是否被注册
	 *
	 * @param userAndCaptchas
	 * @return
	 */
	HashMap<String, Object> isRepeatPhone(MobileUserVO userAndCaptchas);

	/**
	 * 忘记密码查询手机号码是否被注册
	 *
	 * @param userAndCaptchas
	 * @return
	 */
	public HashMap<String, Object> isRepeatPhoneFormLocal(
			MobileUserVO userAndCaptchas) throws SQLException;

	/**
	 * 根据id和手机号码查询该用户是否绑定的手机号码和验证的手机号码是否匹配
	 *
	 * @param mobileUserVO
	 * @return
	 */
	boolean hasMobile(MobileUserVO mobileUserVO) throws SQLException;

	/**
	 * 根据id查询用户是否已经绑定手机号码
	 *
	 * @param mobileUserVO
	 * @return
	 */
	boolean hasAMobile(MobileUserVO mobileUserVO) throws SQLException;

	/**
	 * 根据手机号码和公司id查询
	 *
	 * @param mobile
	 * @param companyId
	 * @return
	 */
	boolean isRepeatMobile(String mobile, Long companyId) throws SQLException;

	/**
	 * 根据密码查询是否正确
	 *
	 * @param userAndCaptchas
	 * @return
	 */
	boolean checkPasswordByUt(MobileUserVO userAndCaptchas) throws SQLException;

	/**
	 * 根据用户id修改手机号码
	 *
	 * @param userAndCaptchas
	 */
	void modifyMobileWithTx(MobileUserVO userAndCaptchas);

	/**
	 * 根据手机号码查询，是否有校验过的验证码
	 *
	 * @param mobileUserVO
	 * @return
	 */
	boolean hasAvailableCode(MobileUserVO mobileUserVO);

	/**
	 * 添加用户记录-从老系统迁移数据
	 */

	User addUserWhereInOldWithTx(UserInfoFromOldVO userInfoFromOld)
			throws SQLException;

	Map<String, Object> getCaptchasByMobile(MobileUserVO userDto)
			throws Exception;

	void sendMsg(String mobile, String userId);

	HashMap<String, Object> updateBussinessPassWord(MobileUserVO userDto)
			throws Exception;
}
