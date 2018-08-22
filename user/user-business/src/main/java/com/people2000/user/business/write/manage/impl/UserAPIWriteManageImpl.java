package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.UserActionLogEnum;
import com.people2000.user.business.write.dao.ext.UserActionLogDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.manage.UserAPIWriteManage;
import com.people2000.user.model.constant.ErrorCode;
import com.people2000.user.model.dto.UserAPIDTO;
import com.people2000.user.model.dto.UserDto;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserExample;

/**
 * Created by xiaole on 2015/11/26.
 */

@Service("userAPIWriteManage")
public class UserAPIWriteManageImpl implements UserAPIWriteManage {

	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;

	@Resource(name = "userActionLogDAOWrite")
	private UserActionLogDAOWrite2 userActionLogDAOWrite;

	private Logger logger = LogUtils.getLogger(this.getClass());

	/**
	 * 根据用户信息获取用户所有信息
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public UserAPIDTO getUserInfoByUserAPI(UserAPIDTO userAPIDTO)
			throws OuserMangeException {
		User user = new User();
		BeanUtils.copyProperties(userAPIDTO, user);

		try {
			List<User> list = userDAOWrite.selectListUserByuser(user);
			if (list != null && list.size() == 1) {
				UserAPIDTO userAPIDTO1 = new UserAPIDTO();
				BeanUtils.copyProperties(list.get(0), userAPIDTO1);
				return userAPIDTO1;
			} else {
				throw new OuserMangeException(ErrorCode.bussiness_error,
						"根据信息未查询到用户");
			}
		} catch (SQLException e) {
			logger.error("获取用户信息失败", e);
			throw new OuserMangeException(ErrorCode.system__error, "获取用户信息失败");
		}
	}

	/**
	 * 根据个别信息修改用户信息
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public Long updateByUserAPIWithTx(UserAPIDTO userAPIDTO)
			throws OuserMangeException {
		Long userId = userAPIDTO.getId();
		List<Long> userIds = userAPIDTO.getIds();
		if (userId == null && userIds == null) {
			throw new OuserMangeException(ErrorCode.input_null, "用户编号不能为空");
		}
		if (null != userId) {
			if (StringUtils.isNotEmpty(userAPIDTO.getMobile())) {
				UserDto userDto2 = new UserDto();
				userDto2.setMobile(userAPIDTO.getMobile());
				userDto2.setCompanyId(userAPIDTO.getCompanyId());
				userDto2.setId(userAPIDTO.getId());
				int cnt;
				try {
					cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
					if (cnt > 0)
						return 1l;
				} catch (SQLException e) {
					throw new OuserMangeException(ErrorCode.sql_error,
							"手机号重复判断查询出错");
				}

			}
			if (StringUtils.isNotEmpty(userAPIDTO.getUsername())) {
				UserDto userDto2 = new UserDto();
				userDto2.setUsername(userAPIDTO.getUsername());
				userDto2.setCompanyId(userAPIDTO.getCompanyId());
				userDto2.setId(userAPIDTO.getId());
				int cnt;
				try {
					cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
					if (cnt > 0)
						return 2l;
				} catch (SQLException e) {
					throw new OuserMangeException(ErrorCode.sql_error,
							"用户名重复判断查询出错");
				}

			}

			if (StringUtils.isNotEmpty(userAPIDTO.getIdentityCardName())) {
				if (null != userAPIDTO.getCompanyId()
						&& 2 == userAPIDTO.getCompanyId()) {// 卓仕才姓名不重复
					UserDto userDto2 = new UserDto();
					userDto2.setIdentityCardName(userAPIDTO
							.getIdentityCardName());
					userDto2.setCompanyId(userAPIDTO.getCompanyId());
					userDto2.setId(userAPIDTO.getId());
					int cnt;
					try {
						cnt = userDAOWrite
								.validateRepeatOrNotByParams(userDto2);
						if (cnt > 0)
							return 3l;
					} catch (SQLException e) {
						throw new OuserMangeException(ErrorCode.sql_error,
								"姓名重复判断查询出错");
					}
				}

			}

			if (StringUtils.isNotEmpty(userAPIDTO.getCompanyName())) {
				UserDto userDto2 = new UserDto();
				userDto2.setCompanyName(userAPIDTO.getCompanyName());
				userDto2.setCompanyId(userAPIDTO.getCompanyId());
				userDto2.setId(userAPIDTO.getId());
				int cnt;
				try {
					cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
					if (cnt > 0)
						return 4l;
				} catch (SQLException e) {
					throw new OuserMangeException(ErrorCode.sql_error,
							"企业名称重复判断查询出错");
				}

			}

			if (StringUtils.isNotEmpty(userAPIDTO.getOrganizationCode())) {
				UserDto userDto2 = new UserDto();
				userDto2.setOrganizationCode(userAPIDTO.getOrganizationCode());
				userDto2.setCompanyId(userAPIDTO.getCompanyId());
				userDto2.setId(userAPIDTO.getId());
				int cnt;
				try {
					cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
					if (cnt > 0)
						return 5l;
				} catch (SQLException e) {
					throw new OuserMangeException(ErrorCode.sql_error,
							"组织机构代码重复判断查询出错");
				}

			}

			if (StringUtils.isNotEmpty(userAPIDTO.getBusinessLicenseNumber())) {
				UserDto userDto2 = new UserDto();
				userDto2.setBusinessLicenseNumber(userAPIDTO
						.getBusinessLicenseNumber());
				userDto2.setCompanyId(userAPIDTO.getCompanyId());
				userDto2.setId(userAPIDTO.getId());
				int cnt;
				try {
					cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
					if (cnt > 0)
						return 6l;
				} catch (SQLException e) {
					throw new OuserMangeException(ErrorCode.sql_error,
							"营业执照号码重复判断查询出错");
				}

			}

			User userPo = null;
			try {
				userPo = userDAOWrite.selectByPrimaryKey(userId, false);
			} catch (SQLException e1) {
				logger.error("查询用户信息失败,userId=" + userId, e1);
				throw new OuserMangeException(ErrorCode.sql_error, "用户信息查询失败");
			}
			if (userPo == null) {
				throw new OuserMangeException(ErrorCode.output_null, "用户不存在");
			}
			User user = new User();
			BeanUtils.copyProperties(userAPIDTO, user);
			if (userAPIDTO.getBirthday() != null) {// 如果需要修改生日
				if (userPo.getBirthdayUpdateCount() != null
						&& userPo.getBirthdayUpdateCount() == 1) {// 已经修改过一次
					if (!userAPIDTO.getBirthday().equals(userPo.getBirthday()))
						return 7l;// 生日只能修改一次
					// throw new
					// OuserMangeException(ErrorCode.birthday_update_exceed_limit,
					// "生日只能修改一次");
				} else {
					if (userPo.getBirthday() != null
							&& !userPo.getBirthday().equals(
									userAPIDTO.getBirthday())) {
						user.setBirthdayUpdateCount(1);
					} else {
						user.setBirthdayUpdateCount(0);
					}
				}
			}
			try {
				if ("".equals(user.getQq())) {
					user.setQq(null);
				}
				int i = userDAOWrite.updateByPrimaryKeySelective(user);
				if (i > 0) {// 维护修改用户信息动作日志
					userActionLogDAOWrite.insertUserActionLog(user,
							UserActionLogEnum.completeUser.getType());
				}

			} catch (SQLException e) {
				logger.error("更新用户信息失败,userId=" + userAPIDTO.getId(), e);
				throw new OuserMangeException(ErrorCode.sql_error, "更新用户信息失败");
			}
		} else if (null != userIds) {// 这个只针对批量删除
			if (null != userAPIDTO.getIsDeleted()
					&& 1 == userAPIDTO.getIsDeleted()) {
				UserDto userDto = new UserDto();
				userDto.setUserIds(userIds);
				if (null != userAPIDTO.getCompanyId()) {
					userDto.setCompanyId(userAPIDTO.getCompanyId());
				}
				try {
					userDAOWrite.batchDeleteUser(userDto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error("批量删除用户失败,userIds=" + userIds, e);
					throw new OuserMangeException(ErrorCode.sql_error,
							"批量更新用户失败");
				}
			}
		}

		return 0l;
	}

	/**
	 * 根据用户信息获取用户所有信息列表
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public List<UserAPIDTO> getUserInfoListByUserAPI(UserAPIDTO userAPIDTO)
			throws OuserMangeException {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		if (userAPIDTO.getUsernames() != null) {
			criteria.andUsernameIn(userAPIDTO.getUsernames());
		}
		if (userAPIDTO.getIds() != null) {
			criteria.andIdIn(userAPIDTO.getIds());
		}
		if (userAPIDTO.getMobiles() != null) {
			criteria.andMobileIn(userAPIDTO.getMobiles());
		}
		if (userAPIDTO.getOriginalIds() != null) {
			criteria.andOriginalIdIn(userAPIDTO.getOriginalIds());
		}
		try {
			List list = userDAOWrite.selectByExample(example, false);
			if (list != null && list.size() > 0) {
				List<UserAPIDTO> userAPIDTOs = new ArrayList<UserAPIDTO>();
				for (Object o : list) {
					UserAPIDTO userAPIDTO1 = new UserAPIDTO();
					BeanUtils.copyProperties(o, userAPIDTO1);
					userAPIDTOs.add(userAPIDTO1);
				}
				return userAPIDTOs;
			} else {
				throw new OuserMangeException(ErrorCode.bussiness_error,
						"根据信息未查询到用户列表");
			}
		} catch (SQLException e) {
			logger.error("获取用户信息失败", e);
			throw new OuserMangeException(ErrorCode.system__error, "获取用户信息列表失败");
		}
	}
}
