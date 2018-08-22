package com.people2000.user.business.utils;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.read.dao.UPlatformGroupRelationDAORead;
import com.people2000.user.business.write.dao.UserDAOWrite;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.model.po.ibatis.UPlatformGroupRelationPO;
import com.people2000.user.model.po.ibatis.UPlatformGroupRelationPOExample;

/**
 * 用户校验工具类
 * 
 * @author huayuefei
 *
 */
@Component
public class UserCheckUtils {
	private static Logger logger = Logger.getLogger(UserCheckUtils.class);

	private static UserDAOWrite userDAOWrite;

	private static UserDAOWrite2 userDAOWrite2;

	private static UPlatformGroupRelationDAORead uPlatformGroupRelationDAORead;

	@Autowired
	public void setUserDAOWrite(UserDAOWrite userDAOWrite) {
		UserCheckUtils.userDAOWrite = userDAOWrite;
	}

	@Autowired
	public void setuPlatformGroupRelationDAORead(
			UPlatformGroupRelationDAORead uPlatformGroupRelationDAORead) {
		UserCheckUtils.uPlatformGroupRelationDAORead = uPlatformGroupRelationDAORead;
	}

	@Autowired
	public void setUserDAOWrite2(UserDAOWrite2 userDAOWrite2) {
		UserCheckUtils.userDAOWrite2 = userDAOWrite2;
	}

	/**
	 * 校验平台所属平台组手机号是否重复
	 * 
	 * @param mobile
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	public static boolean isMobileRepeat(String mobile, Long platformId) {
		boolean b = true;
		try {
			UPlatformGroupRelationPOExample platformExample = new UPlatformGroupRelationPOExample();
			UPlatformGroupRelationPOExample.Criteria platformCriteria = platformExample
					.createCriteria();
			platformCriteria.andPlatformIdEqualTo(platformId);
			List<UPlatformGroupRelationPO> list = uPlatformGroupRelationDAORead
					.selectByExample(platformExample, false);
			if (list != null && list.size() > 0) {
				int count = userDAOWrite2.selectCountByMobileAndPlatformId(
						mobile, platformId);
				if (count == 0) {
					b = false;
				}
			} else {
				logger.info("用户未查询到平台组信息");
			}
		} catch (SQLException e) {
			logger.error("查询手机号是否重复，SQL异常");
			LogUtils.getLogger(UserCheckUtils.class).error(e.getMessage(), e);
			;
		}
		return b;
	}

	public static boolean isMobileRepeat(String mobile) {
		boolean b = true;
		try {
			int count = userDAOWrite2.selectCountByMobileAndPlatformId(mobile,
					null);
			if (count == 0) {
				b = false;
			}
		} catch (SQLException e) {
			logger.error("查询手机号是否重复，SQL异常");
			LogUtils.getLogger(UserCheckUtils.class).error(e.getMessage(), e);
			;
		}
		return b;
	}

	/**
	 * 校验用户名是否重复
	 * 
	 * @param userName
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	public static boolean isUserNameRepeat(String userName, Long platformId) {
		boolean b = true;
		try {
			UPlatformGroupRelationPOExample platformExample = new UPlatformGroupRelationPOExample();
			UPlatformGroupRelationPOExample.Criteria platformCriteria = platformExample
					.createCriteria();
			platformCriteria.andPlatformIdEqualTo(platformId);
			List<UPlatformGroupRelationPO> list = uPlatformGroupRelationDAORead
					.selectByExample(platformExample, false);
			if (list != null && list.size() > 0) {
				int count = userDAOWrite2.selectCountByUserNameAndPlatformId(
						userName, platformId);
				if (count == 0) {
					b = false;
				}
			} else {
				logger.info("用户未查询到平台组信息");
			}
		} catch (SQLException e) {
			logger.error("查询用户名是否重复，SQL异常");
			LogUtils.getLogger(UserCheckUtils.class).error(e.getMessage(), e);
			;
		}
		return b;
	}

	/**
	 * 校验用户平台参数是否存在,<b>只是检查platformId存在与否，积重不好返，TODO:返回code：0、-1、-2常量、缓存时间60、300
	 * 常量</b>
	 * 
	 * @param args
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static int checkUserValid(Long platformId) {

		if (platformId == null || platformId == 0L) {
			return -1;
		}

		Set<Long> platformIds = (Set<Long>) CacheCommUtils
				.getCache("ouser-platform-platformId");
		if (platformIds == null) {
			platformIds = new HashSet<Long>();
			CacheCommUtils.putCache("ouser-platform-platformId", platformIds,
					platformIds.size() < 1 ? 60 : 300);
		}

		return platformIds.contains(platformId) ? 0 : -2;
	}

}
