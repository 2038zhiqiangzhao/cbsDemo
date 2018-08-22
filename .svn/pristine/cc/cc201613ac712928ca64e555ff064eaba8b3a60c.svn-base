package com.people2000.user.business.write.dao.ext;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.odianyun.common.ocache.CacheProxy;
import com.people2000.user.business.utils.UserActionLogEnum;
import com.people2000.user.business.write.dao.UserActionLogDAOWriteImpl;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserActionLogPO;

@Repository("userActionLogDAOWrite")
public class UserActionLogDAOWriteImpl2 extends UserActionLogDAOWriteImpl
		implements UserActionLogDAOWrite2 {
	private Logger log = LoggerFactory
			.getLogger(UserActionLogDAOWriteImpl2.class);

	@Resource(name = "sqlMapClientWrite")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	// @Resource(name = "cache")
	public void setCache(CacheProxy cache) {
		this.cache = cache;
	}

	public Long insertUserActionLog(User user, int type) throws SQLException {
		// 当类型为完善信息时
		if (type == UserActionLogEnum.completeUser.getType()) {
			Map<String, Long> param = new HashMap();
			param.put("type", Long.parseLong(type + ""));
			param.put("userId", user.getId());
			List list = sqlMapClient
					.queryForList("u_user_action_log2.selectByUserIdAndTypeForUpdate");

			if (list.isEmpty()) {
				return 0l;
			}
		}
		try {
			UserActionLogPO userLog = new UserActionLogPO();
			userLog.setChannel("ouser");
			userLog.setCompanyId(user.getCompanyId());
			userLog.setType(type);
			userLog.setUserId(user.getId());
			userLog.setRemark(UserActionLogEnum.getName(type));
			return insert(userLog);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return 0l;
	}

}
