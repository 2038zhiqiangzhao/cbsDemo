/**
 *
 */
package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.write.dao.UserCookieDAOWrite;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.manage.MobileLoginWriteManage;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.po.ibatis.UserCookieExample;

/**
 * @author crc
 * @time 2015-4-30 下午12:27:02
 * @description <pre>
 * 用户登陆cookie
 * </pre>
 */
@Service("userCookieWriteManage")
public class UserCookieWriteManageImpl implements UserCookieWriteManage {

	@Resource(name = "userCookieDAOWrite")
	private UserCookieDAOWrite userCookieDAOWrite;

	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;

	// @Resource(name = "loginManageImpl")
	// private LoginManage loginManage;

	@Resource(name = "mobileLoginWriteManageImpl")
	private MobileLoginWriteManage mobileLoginWriteManageImpl;

	@SuppressWarnings("unchecked")
	@Override
	public UserCache getUserByLoginCookieWithTx(String cookieValue)
			throws Exception {
		// 判断缓存中是否有此用户
		UserCache userCache = SsoClientUtil.getUser(cookieValue);
		if (userCache != null) {
			return userCache;
		}
		// 判断表中是否有此用户
		UserCookieExample example = new UserCookieExample();
		example.createCriteria().andCookieValueEqualTo(cookieValue)
				.andExpirationTimeGreaterThanOrEqualTo(new Date());
		List<UserCookie> list = userCookieDAOWrite.selectByExample(example,
				false);
		if (list != null && list.size() == 1) {
			UserCookie userCookie = list.get(0);
			User uUser = userDAOWrite.selectByPrimaryKey(
					userCookie.getUserId(), false);
			userCache = mobileLoginWriteManageImpl.getCacheUser(uUser);
			SsoClientUtil.login(userCache, cookieValue);// 将用户放到缓存中
		}
		return userCache;
	}

	@Override
	public void saveUserCookieWithTx(UserCookie userCookie) throws Exception {
		// 设置有效时间
		// 生成失效时间
		Calendar timeout = Calendar.getInstance();
		timeout.setTime(new Date());
		timeout.add(Calendar.DAY_OF_YEAR, 15);
		userCookie.setExpirationTime(timeout.getTime());
		// 根据不同的公司，设置有效的ut
		// 如果是卓仕，删除之前的ut
		if (userCookie.getCompanyId() != null && 2 == userCookie.getCompanyId()) {
			UserCookieExample userCookieExample = new UserCookieExample();
			UserCookieExample.Criteria criteria = userCookieExample
					.createCriteria();
			criteria.andUserIdEqualTo(userCookie.getUserId());
			List<UserCookie> list = userCookieDAOWrite.selectByExample(
					userCookieExample, false);
			if (list != null && list.size() > 0) {
				Iterator<UserCookie> iterator = list.iterator();
				while (iterator.hasNext()) {
					UserCookie cookie = iterator.next();
					cookie.setIsDeleted(1);
					// 清除缓存
					SsoClientUtil.logout(cookie.getCookieValue());
					userCookieDAOWrite.updateByPrimaryKeySelective(cookie);
				}
			}
		}
		userCookieDAOWrite.insert(userCookie);
	}

	@Override
	public void logoutWithTx(String ut) {
		if (ut == null) {
			return;
		}
		UserCookieExample userCookieExample = new UserCookieExample();
		UserCookieExample.Criteria criteria = userCookieExample
				.createCriteria();
		criteria.andCookieValueEqualTo(ut);
		try {
			List<UserCookie> list = userCookieDAOWrite.selectByExample(
					userCookieExample, false);
			if (list != null && list.size() > 0) {
				UserCookie userCookie = list.get(0);
				userCookie.setIsDeleted(1);
				userCookieDAOWrite.updateByPrimaryKeySelective(userCookie);
			}
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
		}
	}

	@Override
	public List<UserCookie> getUserCookiesByIdWithTx(Long id)
			throws SQLException {
		UserCookieExample userCookieExample = new UserCookieExample();
		UserCookieExample.Criteria criteria = userCookieExample
				.createCriteria();
		criteria.andUserIdEqualTo(id);
		userCookieExample.setOrderByClause("create_time desc");
		List<UserCookie> list = userCookieDAOWrite.selectByExample(
				userCookieExample, false);
		return list;
	}
}
