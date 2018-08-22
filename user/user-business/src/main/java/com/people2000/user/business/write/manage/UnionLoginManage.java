package com.people2000.user.business.write.manage;

import java.sql.SQLException;
import java.util.Map;

import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.dto.UserUnionLoginDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.vo.MobileUserVO;

/**
 * 联合登录 Created by dailf on 2016/7/28.
 */
public interface UnionLoginManage {
	/**
	 * 保存第三方用户登录信息
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 * @throws Exception
	 */
	Object savaUnionUserInfoWithTx(UserUnionLoginDTO userUnionLoginDTO)
			throws Exception;

	/**
	 * 状态验证，放跨域攻击
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 * @throws Exception
	 */
	void stateValidate(UserUnionLoginDTO userUnionLoginDTO)
			throws RuntimeException;

	/**
	 * 获取缓存用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	UserCache getUserCache(Long userId) throws Exception;

	/**
	 * 获取预登陆参数
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 * @throws SQLException
	 */
	String getRelatedParams(UserUnionLoginDTO userUnionLoginDTO)
			throws SQLException;

	/**
	 * 检查密码
	 * 
	 * @return
	 */
	boolean checkPassword() throws SQLException;

	/**
	 * 更新用户信息
	 * 
	 * @param userVo
	 * @return
	 * @throws SQLException
	 */
	boolean bindUserPasswordWithTx(MobileUserVO userVo) throws SQLException;

	/**
	 * 用户图标展示
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<Integer, Integer> iconInvisible() throws Exception;

	/**
	 * 联合登录手机绑定
	 * 
	 * @param mobileUserVO
	 * @throws Exception
	 */
	Map<String, String> userUniqueCheckWithTx(MobileUserVO mobileUserVO)
			throws Exception;

	/**
	 * 通过unionId查询用户id
	 * 
	 * @author wupeng
	 * @date 2016/10/13
	 * @param
	 * @return
	 * @throws
	 */
	UserUnionLoginDTO getUserByUnionId(String unionId)
			throws OuserMangeException;

	/**
	 * 手机号码绑定unionId
	 * 
	 * @author wupeng
	 * @date 2016/10/13
	 * @param
	 * @return
	 * @throws
	 */
	Map<String, Object> bindUnionToMobileWithTx(UserUnionLoginDTO unionLoginDTO)
			throws OuserMangeException;

	/**
	 * 通过oauthCode获取用户的unionId
	 * 
	 * @param oauthCode
	 * @param oauthState
	 * @return
	 */
	UserUnionLoginDTO getUnionIdByCode(UserUnionLoginDTO userUnionLoginDTO);

	void saveUnionIdWithTx(UserUnionLoginDTO unionLoginDTO)
			throws OuserMangeException;
}
