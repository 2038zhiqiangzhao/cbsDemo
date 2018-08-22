package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.base.page.PageResult;
import com.people2000.common.base.page.Pagination;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.read.dao.UPlatformGroupRelationDAORead;
import com.people2000.user.business.utils.CommonUtils;
import com.people2000.user.business.utils.PageUtils;
import com.people2000.user.business.utils.PasswordUtil;
import com.people2000.user.business.utils.UserActionLogEnum;
import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.business.write.dao.UserAccountDAOWrite;
import com.people2000.user.business.write.dao.UserGradeDAOWrite;
import com.people2000.user.business.write.dao.UserIdentityCardDAOWrite;
import com.people2000.user.business.write.dao.UserPositionDAOWrite;
import com.people2000.user.business.write.dao.ext.UOrganizationUserDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserActionLogDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserExtDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserRoleDAOWrite2;
import com.people2000.user.business.write.manage.MobileLoginWriteManage;
import com.people2000.user.business.write.manage.MobileUserWriteManage;
import com.people2000.user.business.write.manage.PasswordEncryptInfoWriteManage;
import com.people2000.user.business.write.manage.UserRoleWriteManage;
import com.people2000.user.business.write.manage.UserWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.constant.ErrorCode;
import com.people2000.user.model.dto.DefaultUserDTO;
import com.people2000.user.model.dto.User2;
import com.people2000.user.model.dto.User3;
import com.people2000.user.model.dto.UserAccountDTO;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.dto.UserDto;
import com.people2000.user.model.dto.UserExtInDTO;
import com.people2000.user.model.dto.UserExtOutDTO;
import com.people2000.user.model.dto.UserGradeDTO;
import com.people2000.user.model.dto.UserInDTO;
import com.people2000.user.model.dto.UserInputDTO;
import com.people2000.user.model.dto.UserNameOutputDTO;
import com.people2000.user.model.dto.UserOutDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.po.ibatis.UOrganizationUserPO;
import com.people2000.user.model.po.ibatis.UOrganizationUserPOExample;
import com.people2000.user.model.po.ibatis.UPlatformGroupRelationPO;
import com.people2000.user.model.po.ibatis.UPlatformGroupRelationPOExample;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserAccountPO;
import com.people2000.user.model.po.ibatis.UserAccountPOExample;
import com.people2000.user.model.po.ibatis.UserExample;
import com.people2000.user.model.po.ibatis.UserExample.Criteria;
import com.people2000.user.model.po.ibatis.UserExtPO;
import com.people2000.user.model.po.ibatis.UserGradePO;
import com.people2000.user.model.po.ibatis.UserIdentityCardExample;
import com.people2000.user.model.po.ibatis.UserPositionPO;
import com.people2000.user.model.po.ibatis.UserPositionPOExample;
import com.people2000.user.model.po.ibatis.UserRole;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.model.vo.UserExtEnum;
import com.people2000.user.model.vo.UserVO;

/**
 * @author crc
 * @time 2015-4-28 下午3:10:23
 * @description <pre>
 * <p/>
 * </pre>
 */
@Service("userWriteManage")
public class UserWriteManageImpl implements UserWriteManage {

	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;
	@Resource(name = "userIdentityCardDAOWrite")
	private UserIdentityCardDAOWrite userIdentityCardDAOWrite;

	@Resource(name = "userRoleWriteManage")
	private UserRoleWriteManage userRoleWriteManage;

	@Resource(name = "userRoleDAOWrite")
	private UserRoleDAOWrite2 userRoleDAOWrite;

	@Resource(name = "userPositionDAOWrite")
	private UserPositionDAOWrite userPositionDAOWrite;

	@Resource(name = "uOrganizationUserDAOWrite")
	private UOrganizationUserDAOWrite2 uOrganizationUserDAOWrite;

	@Resource(name = "mobileUserWriteManage")
	private MobileUserWriteManage mobileUserWriteManage;

	@Resource(name = "passwordEncryptInfoWriteManage")
	private PasswordEncryptInfoWriteManage passwordEncryptInfoWriteManage;

	@Resource(name = "mobileLoginWriteManageImpl")
	private MobileLoginWriteManage mobileLoginWriteManageImpl;

	@Resource(name = "uPlatformGroupRelationDAORead")
	private UPlatformGroupRelationDAORead uPlatformGroupRelationDAORead;
	@Resource(name = "userActionLogDAOWrite")
	private UserActionLogDAOWrite2 userActionLogDAOWrite;
	@Resource(name = "userExtDAOWrite")
	private UserExtDAOWrite2 userExtDAOWrite;

	@Resource(name = "userAccountDAOWrite")
	private UserAccountDAOWrite userAccountDAOWrite;
	@Resource(name = "userGradeDAOWrite")
	private UserGradeDAOWrite userGradeDAOWrite;

	private Logger logger = LogUtils.getLogger(UserWriteManageImpl.class);

	private static final Logger log = LoggerFactory
			.getLogger(UserWriteManageImpl.class);

	private static final String SHOP_TYPE_NAME = "承诺店";

	private static final String SUCCESS_CODE = "0";

	public static final String SHOP_ACTION = "A";// 店铺行动字段

	private Calendar cal = Calendar.getInstance();

	@Override
	public User getUserById(Long userId) throws Exception {
		return userDAOWrite.selectByPrimaryKey(userId, false);
	}

	@Override
	public User3 getUserDetailById(Long userId) throws Exception {
		return userDAOWrite.selectDetailByUserId2(userId);
	}

	@Override
	public Integer updateUserDetailWithTx(UserDto userDto)
			throws OuserMangeException {
		int result = 0;
		if (userDto.getId() == null) {
			return null;
		}
		User user = new User();
		user.setId(userDto.getId());
		user.setIdentityCardName(userDto.getIdentityCardName());
		user.setMobile(userDto.getMobile());
		user.setEmail(userDto.getEmail());
		user.setIsAvailable(userDto.getIsAvailable());
		user.setHeadPicUrl(userDto.getHeadPicUrl());
		user.setSex(userDto.getSex());
		user.setBirthday(userDto.getBirthday());
		user.setUserProvince(userDto.getUserProvince());
		user.setUserCity(userDto.getUserCity());
		user.setUserRegion(userDto.getUserRegion());
		user.setAddress(userDto.getAddress());
		user.setIndustry(userDto.getIndustry());
		user.setNameCardUrl(userDto.getNameCardUrl());
		user.setCompanyId(userDto.getCompanyId());
		user.setCompanyName(userDto.getCompanyName());
		user.setGradeId(userDto.getGradeId());
		user.setEmployeNum(userDto.getEmployeNum());
		user.setProfession(userDto.getProfession());
		user.setContactPerson(userDto.getContactPerson());
		user.setIdentityType(userDto.getIdentityType());
		user.setUsername(userDto.getUsername());
		user.setOpenId(userDto.getOpenId());

		try {
			result = userDAOWrite.updateByPrimaryKeySelective(user);

			// 如果修改了物业类型 START
			/*UserRole ur = new UserRole();
			ur.setUserId(userDto.getId());
			if (userDto.getIdentityType() != null
					&& "1".equals(userDto.getIdentityType().toString())) {
				ur.setRoleId(3L);
			} else if (userDto.getIdentityType() != null
					&& "2".equals(userDto.getIdentityType().toString())) {
				ur.setRoleId(4L);
			}
			userRoleWriteManage.updateOneUserRoleWithTx(ur);*/
			// 如果修改了物业类型 END
			
			if (result > 0) {// 维护修改用户信息动作日志
				userActionLogDAOWrite.insertUserActionLog(user,
						UserActionLogEnum.completeUser.getType());
			}
			if (userDto.getPositionId() != null) {
				UserPositionPOExample example = new UserPositionPOExample();
				UserPositionPOExample.Criteria criteria = example
						.createCriteria();
				criteria.andUserIdEqualTo(userDto.getId());
				UserPositionPO record = new UserPositionPO();
				record.setIsDeleted(1);
				userPositionDAOWrite.updateByExampleSelective(record, example);
				UserPositionPO addRecord = new UserPositionPO();
				addRecord.setUserId(userDto.getId());
				addRecord.setPositionId(userDto.getPositionId());
				addRecord.setCompanyId(userDto.getCompanyId());
				userPositionDAOWrite.insert(addRecord);
			}
			if (userDto.getOrgId() != null) {
				UOrganizationUserPOExample example = new UOrganizationUserPOExample();
				UOrganizationUserPOExample.Criteria criteria = example
						.createCriteria();
				criteria.andUserIdEqualTo(userDto.getId());
				UOrganizationUserPO record = new UOrganizationUserPO();
				record.setIsDeleted(1);
				uOrganizationUserDAOWrite.updateByExampleSelective(record,
						example);
				UOrganizationUserPO addRecord = new UOrganizationUserPO();
				addRecord.setUserId(userDto.getId());
				addRecord.setOrganizationId(userDto.getOrgId());
				addRecord.setCompanyId(userDto.getCompanyId());
				uOrganizationUserDAOWrite.insert(addRecord);
			}

			// 新增商户时候的编辑功能
			if (StringUtils.isNotEmpty(userDto.getPassword1())
					&& StringUtils.isNotEmpty(userDto.getPassword2())) {
				MobileUserVO muv = new MobileUserVO();
				muv.setId(userDto.getId());
				muv.setType(5);// 商户
				muv.setMobile(userDto.getMobile());
				muv.setPassword(userDto.getPassword());
				muv.setPassword1(userDto.getPassword1());
				muv.setPassword2(userDto.getPassword2());
				// 用户密码也一起修改
				mobileUserWriteManage.updateBussinessPassWord(muv);
			}

		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			throw new OuserMangeException(ErrorCode.sql_error,
					"updatePassWordWithTx");
		}
		return result;
	}

	@Override
	public void resetPasswordByBackendWithTx(UserDto userDto) throws Exception {
		/*
		 * String chars = "abcdefghijklmnopqrstuvwxyz0123456789";//字母和数字组合
		 * StringBuilder password=new StringBuilder(); for(int
		 * i=0;i<10;i++){//生成10位随机密码
		 * password.append(chars.charAt((int)(Math.random() * 36))); }
		 */
		StringBuilder password = new StringBuilder();
		password.append("123456");// 重置密码默认123456

		User encryptUser = null;// 1 用于查询密码加密信息
		Date now = new Date();
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity;
		try {
			encryptEntity = passwordEncryptInfoWriteManage
					.getEncryptInfoByUser(encryptUser);
			userDto.setPassword(PasswordUtil.getSecretPassWord(
					password.toString(), encryptEntity.getNewBSalt(),
					encryptEntity.getNewBVersion()));
			// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
			userDto.setbSalt(encryptEntity.getNewBSalt());
			if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
				userDto.setbSaltUpdateTime(now);
			}
			userDto.setbAlgorithmVersion(encryptEntity.getNewBVersion());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			LogUtils.getLogger(getClass()).error(
					"获取系统当前加密信息失败" + e1.getMessage());
			throw new OuserMangeException(ErrorCode.sql_error, "获取系统当前加密信息失败"
					+ e1.getMessage());
		}

		try {
			updateUserWithTx(userDto);
			/*
			 * if(StringUtils.isNotEmpty(userDto.getMobile())){
			 * OmcOpenConfigFacade.sendMSG(userDto.getMobile(),
			 * "您的密码已经重置,重置后密码为 " + password.toString(),
			 * userDto.getCompanyId()); }
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LogUtils.getLogger(getClass()).error("重置用户密码失败" + e.getMessage());
			throw new OuserMangeException(ErrorCode.sql_error, "重置用户密码失败"
					+ e.getMessage());
		}

	}

	@Override
	public void saveUserWithTx(User uuser, Long userId) throws Exception {
		if (userId.intValue() != uuser.getId().intValue()) {
			throw new Exception("商家只能修改自己的信息");
		}
		User updateUser = new User();
		updateUser.setId(userId);
		updateUser.setContactPerson(uuser.getContactPerson());// 联系人
		updateUser.setMobile(uuser.getMobile());// 联系电话
		userDAOWrite.updateByPrimaryKeySelective(updateUser);
	}

	@Override
	public void submitUserWithTx(User uuser, Long userId) throws Exception {
		if (userId.intValue() != uuser.getId().intValue()) {
			throw new Exception("商家只能修改自己的信息");
		}
		User updateUser = new User();
		updateUser.setId(userId);
		updateUser.setContactPerson(uuser.getContactPerson());// 联系人
		updateUser.setMobile(uuser.getMobile());// 联系电话
		updateUser.setStatus(ConstantUser.user_status.wait);
		userDAOWrite.updateByPrimaryKeySelective(updateUser);
	}

	@Override
	public List<User> getUserChildAccountByUserId(Long userId) throws Exception {
		UserExample example = new UserExample();
		example.createCriteria().andParentIdEqualTo(userId);
		return userDAOWrite.selectByExample(example, false);
	}

	@Override
	public Long saveUserChildAccountWithTx(UserDto userDto) throws Exception {

		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		String password = user.getPassword();

		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = passwordEncryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);
		password = PasswordUtil.getSecretPassWord(userDto.getPassword(),
				encryptEntity.getNewBSalt(), encryptEntity.getNewBVersion());
		// ----------------------user.setPassword(PassWordUtils.getSecretPassWord(password));
		user.setPassword(password);

		user.setType(ConstantUser.user_type.child);
		user.setStatus(ConstantUser.user_status.success);

		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		user.setbSaltUpdateTime(new Date());
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());

		Long childId = userDAOWrite.insert(user);
		// 保存角色和用户的关联关系
		Long roleId = userDto.getRoleId();
		// TODO 校验角色id是是否为子账户的角色
		Long userId = user.getId();
		if (roleId == null) {
			return childId;
		}
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		userRoleDAOWrite.insert(userRole);
		return childId;
		// 保存用户管理的店铺
		// userShopManage.saveUserManageShopWithTx(userDto.getShopIds(),
		// userId);
	}

	@Override
	public List<User2> getUserChildAccountByQuery(UserDto userDto)
			throws Exception {
		UserExample example = new UserExample();
		Long userId = userDto.getId();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(userId);
		String userName = userDto.getUsername();
		if (userName != null) {
			criteria.andUsernameLike(userName + "%");
		}
		// TODO 其他条件

		return userDAOWrite.selectByExample2(example);
	}

	/**
	 * @param id
	 *            需要删除的用户id
	 * @param userId
	 *            操作人的用户id
	 * @description <pre>
	 * 根据id删除子账户
	 * </pre>
	 */
	@Override
	public void deleteChildAccountWithTx(Long id, Long userId) throws Exception {

		// 判断 需要删除的子账户是否是 该操作用户下的
		User user = userDAOWrite.selectByPrimaryKey(id, false);
		if (user.getParentId().intValue() != 0
				&& user.getParentId().intValue() != userId.intValue()) {
			throw new Exception("只能删除自己的子账户");
		}
		User userDelete = new User();
		userDelete.setId(id);
		userDelete.setIsDeleted(1);
		userDAOWrite.updateByPrimaryKeySelective(userDelete);
	}

	@Override
	public int updatePassWordWithTx(UserDto userDto) throws Exception {

		String password1 = userDto.getPassword1();
		String password2 = userDto.getPassword2();

		if (password1 == null || password2 == null || "".equals(password1)
				|| "".equals(password2)) {
			return 3;// 新密码不能为空
		}

		if (!password1.equals(password2)) {
			return 1;
		}

		if (userDto.getPassword() != null && !"".equals(userDto.getPassword())) {
			User encryptUser = new User();// 1 用于查询用户密码加密信息
			encryptUser.setId(userDto.getId());

			String password = "";

			// 2 查询用户的密码加密信息
			EncryptEntity encryptEntity = passwordEncryptInfoWriteManage
					.getEncryptInfoByUser(encryptUser);
			if (StringUtils.isEmpty(encryptEntity.getOldBSalt())) {// 没有old盐值,是修改之前的用户,原密码md5加密一次
				password = PasswordUtil.getSecretPassWord(
						userDto.getPassword(), "", 1);
			} else {// 有old盐值,就用old盐值跟old算法加密
				if (null != encryptEntity.getOldBVersion()) {
					password = PasswordUtil.getSecretPassWord(
							userDto.getPassword(), encryptEntity.getOldBSalt(),
							encryptEntity.getOldBVersion());
				}
			}

			User user = userDAOWrite.selectByPrimaryKey(userDto.getId(), false);
			if (!user.getPassword().equals(password)) {
				return 2;// 原密码不正确
			}
			User user2 = new User();
			user2.setId(userDto.getId());

			Date now = new Date();

			// 3 加密新密码
			if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())
					|| null != encryptEntity.getNewBVersion()) {
				String salt = StringUtils.isEmpty(encryptEntity.getNewBSalt()) ? encryptEntity
						.getOldBSalt() : encryptEntity.getNewBSalt();
				Integer version = (null == encryptEntity.getNewBVersion()) ? encryptEntity
						.getOldBVersion() : encryptEntity.getNewBVersion();

				password = PasswordUtil.getSecretPassWord(password1, salt,
						version);

				if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
					user2.setbSalt(encryptEntity.getNewBSalt());
					user2.setbSaltUpdateTime(now);
				}
				if (null != encryptEntity.getNewBVersion()) {
					user2.setbAlgorithmVersion(encryptEntity.getNewBVersion());
				}
			} else {// 没有返回新的盐值和算法,肯定返回了旧的算法和盐值
				String salt = encryptEntity.getOldBSalt();
				Integer version = encryptEntity.getOldBVersion();
				password = PasswordUtil.getSecretPassWord(password1, salt,
						version);
			}

			user2.setPassword(password);

			// 更新前端加密算法信息
			if (StringUtils.isNotEmpty(userDto.getNewFSalt())) {
				user2.setfSalt(userDto.getNewFSalt());
				user2.setfSaltUpdateTime(now);
			}
			if (null != userDto.getNewFVersion()) {
				user2.setfAlgorithmVersion(userDto.getNewFVersion());
			}

			userDAOWrite.updateByPrimaryKeySelective(user2);
			try {
				userActionLogDAOWrite.insertUserActionLog(user,
						UserActionLogEnum.changePassword.getType());
			} catch (Exception e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			}
		} else {
			return 4;// 原密码不能为空
		}
		return 0;
	}

	@Override
	public int registerWithTx(UserDto userDto) throws Exception {
		String temp;
		{
			temp = userDto.getUsername();
			if (StringUtils.isBlank(temp)) {
				LogUtils.getLogger(this.getClass()).warn("注册时用户名称不能为空!");
				return 1;
			}
			temp = temp.trim();
			temp = temp.toLowerCase();
			temp = temp.replaceAll(" ", "");
			temp = temp.replaceAll("\t", "");
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userDto.setUsername(temp);
			if (temp.length() < 6 || temp.length() > 20) {
				LogUtils.getLogger(this.getClass()).warn(
						"注册时用户名称(" + temp + ")长度必须是6 至 20位长度!");
				return 2;
			}
		}

		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = passwordEncryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);

		{
			temp = userDto.getPassword();
			if (StringUtils.isBlank(temp)) {
				LogUtils.getLogger(this.getClass()).warn("注册时密码不能为空!");
				return 3;
			}
			temp = temp.trim();
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userDto.setPassword(temp);
			if (temp.length() < 6 || temp.length() > 32) {
				// LogUtils.warn(this, "注册时密码长度为6 - 20位!");
				return 4;
			}
			// 密码进行MD5加密
			// ---------------------userDto.setPassword(PassWordUtils.getSecretPassWord(temp));
			userDto.setPassword(PasswordUtil.getSecretPassWord(
					userDto.getPassword(), encryptEntity.getNewBSalt(),
					encryptEntity.getNewBVersion()));

		}
		{
			temp = userDto.getCompanyName();
			if (StringUtils.isBlank(temp)) {
				return 5;
			}
			temp = temp.trim();
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userDto.setCompanyName(temp);
			if (temp.length() < 4 || temp.length() > 40) {
				return 6;
			}
		}
		{
			temp = userDto.getContactPerson();
			if (StringUtils.isBlank(temp)) {
				return 7;
			}
			temp = temp.trim();
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userDto.setContactPerson(temp);
			if (temp.length() < 2 || temp.length() > 20) {
				return 8;
			}
		}
		{
			temp = userDto.getMobile();
			if (StringUtils.isBlank(temp)) {
				return 9;
			}
			temp = temp.trim();
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userDto.setMobile(temp);
			if (temp.length() != 11) {
				return 10;
			}
		}
		{
			temp = userDto.getEmail();
			if (StringUtils.isBlank(temp)) {
				return 11;
			}
			temp = temp.trim();
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\n", "");
			userDto.setEmail(temp);
			if (temp.length() > 100) {
				return 12;
			}
			if (!CommonUtils.verifyEmail(temp)) {
				return 13;
			}
		}
		MobileUserVO userVO = new MobileUserVO();
		userVO.setMobile(userDto.getMobile());
		userVO.setCaptchas(userDto.getSmsCode());
		HashMap<String, Object> resultMap = mobileUserWriteManage
				.checkCodeWithTx(userVO);
		if ((Integer) resultMap.get("code") != 0) {
			return 18;
		}
		if (userDto.getPlatformId() == null) {
			userDto.setPlatformId(Long.valueOf(ConstantUser.user_type.main));
		}
		Long platformId = userDto.getPlatformId();
		try {
			userDto.setParentId(0L);
			userDto.setStatus(ConstantUser.user_status.wait);
			userDto.setIsDeleted(ConstantUser.isDelete.normal);
			userDto.setType(ConstantUser.user_type.main);// 页面注册的都是商家主账号

			Date now = new Date();

			// 前端盐
			userDto.setfAlgorithmVersion(userDto.getNewFVersion());
			userDto.setfSalt(userDto.getNewFSalt());
			if (StringUtils.isNotEmpty(userDto.getNewFSalt())) {
				userDto.setfSaltUpdateTime(now);
			}

			User user = new User();
			BeanUtils.copyProperties(userDto, user);

			// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
			user.setbSalt(encryptEntity.getNewBSalt());
			if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
				user.setbSaltUpdateTime(now);
			}
			user.setbAlgorithmVersion(encryptEntity.getNewBVersion());

			userDAOWrite.insert(user);
			Long userId = user.getId();
			// TODO 创建账户 开发测试阶段先注释掉
			/*
			 * AccountServiceClient accountServiceClient =
			 * AccountServiceClient.getInstanst(); if
			 * (!accountServiceClient.createAccount(userId)) { return 1; }
			 */
			// 给用户赋默认角色
			String[] defaultRole = ConstantUser.default_role.split(",");
			for (String roleId : defaultRole) {
				UserRole userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(Long.valueOf(roleId));// TODO 修改为配置文件
				userRole.setIsDeleted(ConstantUser.isDelete.normal);
				userRoleDAOWrite.insert(userRole);
			}

			try {
				userActionLogDAOWrite.insertUserActionLog(user,
						UserActionLogEnum.register.getType());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LogUtils.getLogger(getClass()).error(
						"注册日志插入错误." + e.getMessage(), e);
			}

			return 0;
		} catch (SQLException e) {
			LogUtils.getLogger(this.getClass()).error(e.getMessage(), e);
			return 1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getManageUserByQuery(UserDto userDto) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(ConstantUser.user_type.backend);
		String userName = userDto.getUsername();
		if (userName != null) {
			criteria.andUsernameLike(userName + "%");
		}
		return userDAOWrite.selectByExample(example, false);
	}

	@Override
	public Map<String, Object> getUserByQueryPage(UserDto userDto)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		String userName = userDto.getUsername();
		if (userName != null) {
			criteria.andUsernameLike(userName + "%");
		}
		if (userDto.getPlatformId() != null
				&& !"".equals(userDto.getPlatformId())) {
			criteria.andPlatformIdEqualTo(userDto.getPlatformId());
		}
		if (userDto.getMobile() != null && !"".equals(userDto.getMobile())) {
			criteria.andMobileLike(userDto.getMobile() + "%");
		}
		if (userDto.getNotEqualsType() != null) {
			criteria.andTypeNotEqualTo(userDto.getNotEqualsType());
		}
		int total = userDAOWrite.countByExample(example, false);
		List<User> list = new ArrayList<User>();
		if (total != 0) {
			// 分页
			Integer currentPage = userDto.getCurrentPage();
			Integer itemsPerPage = userDto.getItemsPerPage();
			if (currentPage != null && itemsPerPage != null) {
				example.setOrderByClause(" id limit "
						+ PageUtils.getStartItem(currentPage, itemsPerPage)
						+ ", " + itemsPerPage);
			}
			list = userDAOWrite.selectByExample(example, false);
		}
		map.put("total", total);
		map.put("list", list);
		return map;
	}

	@Override
	public int saveManageUserWithTx(UserDto userDto) throws Exception {
		String password = userDto.getPassword();
		String password1 = userDto.getPassword1();
		if (password == null || password1 == null || "".equals(password)
				|| "".equals(password1)) {
			return 1;// 密码不能为空
		}
		if (!password.equals(password1)) {
			return 2;// 两次密码不一致
		}
		String userName = userDto.getUsername();
		if (userName == null || "".equals(userName)) {
			return 3;// 用户名称不能为空
		}
		// 校验用户名是否已存在
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		if (userDto.getCompanyId() != null)
			criteria.andCompanyIdEqualTo(userDto.getCompanyId());
		int count = userDAOWrite.countByExample(example, false);
		if (count != 0) {
			return 4;// 用户名已存在
		}

		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = passwordEncryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);

		// 保存用户
		User user = new User();
		user.setParentId(0l);
		user.setUsername(userName);
		user.setPassword(PasswordUtil.getSecretPassWord(password,
				encryptEntity.getNewBSalt(), encryptEntity.getNewBVersion()));
		user.setRemarks(userDto.getRemarks());
		user.setType(ConstantUser.user_type.backend);
		user.setCompanyId(userDto.getCompanyId());
		Long platformId = userDto.getUserPlatformId();
		user.setPlatformId(platformId);

		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		user.setbSaltUpdateTime(new Date());
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());

		userDAOWrite.insert(user);
		return 0;
	}

	@Override
	public List<User> getUserMainList(User user) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(ConstantUser.user_type.main);

		String userName = user.getUsername();
		if (userName != null && !"".equals(userName)) {
			criteria.andUsernameLike(userName + "%");
		}
		return userDAOWrite.selectByExample(example, false);
	}

	@Override
	public void checkUserWithTx(Long id, Integer status) throws Exception {
		User user = new User();
		user.setId(id);
		user.setStatus(status);
		userDAOWrite.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> getUsers() throws Exception {
		return userDAOWrite.selectByExample(new UserExample(), false);
	}

	/**
	 * 获取子账户信息
	 */
	@Override
	public UserVO getChildUser(Long userId) throws Exception {

		UserVO userVO = new UserVO();
		User user = userDAOWrite.selectByPrimaryKey(userId, false);
		BeanUtils.copyProperties(user, userVO);
		// userVO.setShopList(shopList);
		List<Long> roleList = userRoleWriteManage.getUserRoleList(userId);
		userVO.setRoleList(roleList);
		return userVO;
	}

	/**
	 * @param userDto
	 * @throws Exception
	 * @description <pre>
	 * 修改 子账户信息
	 * </pre>
	 */
	@Override
	public void UpdateUserChildAccountWithTx(UserDto userDto, Long userId)
			throws Exception {
		User user = userDAOWrite.selectByPrimaryKey(userDto.getId(), false);
		if (user == null) {
			throw new Exception("此账号不存在");
		}
		if (user.getParentId().intValue() != userId.intValue()) {
			throw new Exception("无权限修改此子账户");
		}
		User record = new User();
		// record.setUsername(userDto.getUsername());
		record.setContactPerson(userDto.getContactPerson());
		record.setMobile(userDto.getMobile());
		record.setEmail(userDto.getEmail());
		record.setId(userDto.getId());
		userDAOWrite.updateByPrimaryKeySelective(record);

		// 更新角色

	}

	@Override
	public MobileUserVO getUserMessageByUserId(Long userId) {
		MobileUserVO mobileUserVO = new MobileUserVO();
		try {
			User user = userDAOWrite.selectByPrimaryKey(userId, false);
			mobileUserVO.setUsername(user.getUsername());
			mobileUserVO.setMobile(user.getMobile());
			UserIdentityCardExample example = new UserIdentityCardExample();
			UserIdentityCardExample.Criteria criteria = example
					.createCriteria();
			criteria.andUserIdEqualTo(userId);
			List list = userIdentityCardDAOWrite
					.selectByExample(example, false);
			if (list != null && list.size() > 0) {
				mobileUserVO.setIsCertified(true);
			}

		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
		}
		return mobileUserVO;
	}

	@Override
	public boolean isRepeatName(UserDto userDto) throws SQLException {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userDto.getUsername()).andCompanyIdEqualTo(
				userDto.getCompanyId());
		if (userDto.getId() != null) {
			criteria.andIdNotEqualTo(userDto.getId());
		}
		List list = userDAOWrite.selectByExample(example, false);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserInfoByUserName(UserDto userDto) throws SQLException {
		UserExample example = setCriteria4QueryPg(userDto);
		List list = userDAOWrite.selectByExample(example, false);
		if (list != null && list.size() > 0) {
			return (User) list.get(0);
		} else {
			return null;
		}
	}

	// 根据需求设置 UserExample
	private UserExample setCriteria4QueryPg(UserDto user) {
		UserExample example = new UserExample();
		UserExample.Criteria c = example.createCriteria();
		if (user.getId() != null)
			c.andIdEqualTo(user.getId());
		if (user.getStatus() != null)
			c.andStatusEqualTo(user.getStatus());
		if (StringUtils.isNotBlank(user.getUsername()))
			c.andUsernameLike(user.getUsername() + "%");
		if (StringUtils.isNotBlank(user.getNickname()))
			c.andNicknameLike("%" + user.getNickname() + "%");
		Integer isAvailable = user.getIsAvailable();
		if (isAvailable != null)
			c.andIsAvailableEqualTo(isAvailable);
		Long companyId = user.getCompanyId();
		if (companyId != null)
			c.andCompanyIdEqualTo(companyId);

		/**
		 * 扩展 所属的其他的一些
		 */
		if (user.isPgFlg() != null && user.isPgFlg())
			example.setOrderByClause("id "
					+ PageUtils.getStartItem(user.getCurrentPage(),
							user.getItemsPerPage()) + ", "
					+ user.getItemsPerPage());
		return example;
	}

	@Override
	public Map<String, Object> getUsersByExamplePG(UserDto user)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		UserExample example = setCriteria4QueryPg(user);

		int count = userDAOWrite.countByExample(example, false);
		result.put("total", count);
		if (count > 0) {
			List<Role> roles = userDAOWrite.selectByExample(example, false);
			result.put("list", roles);
		}
		return result;
	}

	@Override
	public Map<String, Object> getOtherUsers(UserDto user,
			List<String> userNameList) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		UserExample example = new UserExample();
		UserExample.Criteria c = example.createCriteria();
		c.andIsAvailableEqualTo(ConstantUser.isAvailable.isAvailable);
		if (StringUtils.isNotBlank(user.getUsername()))
			c.andUsernameLike("%" + user.getUsername() + "%");
		if (StringUtils.isNotBlank(user.getNickname()))
			c.andNicknameLike("%" + user.getNickname() + "%");
		if (user.getIsAvailable() != null)
			c.andIsAvailableEqualTo(user.getIsAvailable());
		if (user.getCompanyId() != null)
			c.andCompanyIdEqualTo(user.getCompanyId());
		if (userNameList.size() > 0)
			c.andUsernameNotIn(userNameList);
		example.setOrderByClause("id "
				+ PageUtils.getStartItem(user.getCurrentPage(),
						user.getItemsPerPage()) + ", " + user.getItemsPerPage());

		int count = userDAOWrite.countByExample(example, false);
		result.put("total", count);
		if (count > 0) {
			List<Role> roles = userDAOWrite.selectByExample(example, false);
			result.put("list", roles);
		}
		return result;
	}

	/**
	 * 根据用户id获取用户姓名
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public UserNameOutputDTO getUserNameById(Long id)
			throws OuserMangeException {
		try {
			User user = userDAOWrite.selectByPrimaryKey(id, false);
			if (user == null) {
				throw new OuserMangeException(ErrorCode.output_null,
						"output_null");
			}
			UserNameOutputDTO userNameOutputDTO = new UserNameOutputDTO();
			BeanUtils.copyProperties(user, userNameOutputDTO);

			return userNameOutputDTO;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.system__error, "根据id查询用户失败");
		}
	}

	/**
	 * 添加默认商家用户并返回id
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public Long addDefaultMerchantUserWithTx(UserDto userDTO)
			throws OuserMangeException {
		if (userDTO.getPassword() == null) {
			throw new OuserMangeException(ErrorCode.input_null, "密码不能为空");
		}
		final User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		user.setType(ConstantUser.user_type.main);
		user.setParentId(0l);

		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = new EncryptEntity();
		try {
			encryptEntity = passwordEncryptInfoWriteManage
					.getEncryptInfoByUser(encryptUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			logger.info("查询加密算法信息失败", e);
			throw new OuserMangeException(ErrorCode.system__error, "查询密码加密信息失败");
		}

		user.setPassword(PasswordUtil.getSecretPassWord(user.getPassword(),
				encryptEntity.getNewBSalt(), encryptEntity.getNewBVersion()));
		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		user.setbSaltUpdateTime(new Date());
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());
		boolean b = false;
		try {
			b = isRepeatMobile(userDTO);
		} catch (Exception e1) {
			LogUtils.getLogger(getClass()).error(e1.getMessage(), e1);
		}
		if (b) {
			throw new OuserMangeException(ErrorCode.bussiness_error,
					"手机号码已经被注册");
		}
		try {
			final Long uid = userDAOWrite.insert(user);
			return uid;
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			throw new OuserMangeException(ErrorCode.system__error, "插入用户失败");
		}
	}

	/**
	 * 根据分页条件查询用户信息
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public List<UserNameOutputDTO> getUserBatch(UserInputDTO data)
			throws OuserMangeException {
		final UserExample example = new UserExample();
		final Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(data.getMobile())) {
			criteria.andMobileLike(data.getMobile());
		}
		if (!StringUtils.isEmpty(data.getUsername())) {
			criteria.andUsernameLike(data.getUsername());
		}
		if (data.getType() != null) {
			criteria.andTypeEqualTo(data.getType());
		}
		if (data.getStatus() != null) {
			criteria.andStatusEqualTo(data.getStatus());
		}
		example.setLimitClauseStart((long) data.getStartItem());
		example.setLimitClauseCount((long) data.getItemsPerPage());
		try {
			final List<User> list = userDAOWrite
					.selectByExample(example, false);
			if (CollectionUtils.isEmpty(list)) {
				throw new OuserMangeException(ErrorCode.output_null,
						"output null");
			}
			final List<UserNameOutputDTO> result = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				final UserNameOutputDTO userNameOutputDTO = new UserNameOutputDTO();
				BeanUtils.copyProperties(list.get(i), userNameOutputDTO);
				result.add(userNameOutputDTO);
			}
			return result;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}

	/**
	 * 根据分页条件查询总条数
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public int getTotal(UserInputDTO data) throws OuserMangeException {
		final UserExample example = new UserExample();
		final Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(data.getMobile())) {
			criteria.andMobileLike(data.getMobile());
		}
		if (!StringUtils.isEmpty(data.getUsername())) {
			criteria.andUsernameLike(data.getUsername());
		}
		if (data.getType() != null) {
			criteria.andTypeEqualTo(data.getType());
		}
		if (data.getStatus() != null) {
			criteria.andStatusEqualTo(data.getStatus());
		}
		example.setLimitClauseStart((long) data.getStartItem());
		example.setLimitClauseCount((long) data.getItemsPerPage());
		try {
			final int i = userDAOWrite.countByExample(example, false);
			return i;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}

	@Override
	public List<UserNameOutputDTO> getUserBatchNew(UserInputDTO data)
			throws OuserMangeException {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		if (StringUtils.isNotEmpty(data.getMobile())) {
			params.put("mobile", data.getMobile());
		}
		if (StringUtils.isNotEmpty(data.getUsername())) {
			params.put("username", data.getUsername() + "%");
		}
		if (StringUtils.isNotEmpty(data.getIdentityCardName())) {
			params.put("identityCardName", data.getIdentityCardName() + "%");
		}
		if (null != data.getType()) {
			params.put("type", data.getType());
		}
		if (null != data.getIsAvailable()) {
			params.put("isAvailable", data.getIsAvailable());
		}
		if (null != data.getCompanyId()) {
			params.put("companyId", data.getCompanyId());
		}
		if (null != data.getStartTime()) {
			// cal.setTime(data.getStartTime());
			// cal.add(Calendar.HOUR, -8);//控件返回的好像是+8小时
			// params.put("startTime", cal.getTime());
			params.put("startTime", data.getStartTime());
		}

		if (null != data.getEndTime()) {
			// cal.setTime(data.getEndTime());
			// cal.add(Calendar.HOUR, 16);
			// params.put("endTime",cal.getTime());
			params.put("endTime", data.getEndTime());
		}
		if (data.getStartItem() >= 0) {
			params.put("startItem", data.getStartItem());
		}
		if (data.getItemsPerPage() > 0) {
			params.put("itemsPerPage", data.getItemsPerPage());
		} else {
			params.put("itemsPerPage", 0);
		}
		if (null != data.getCreateUserid()) {
			params.put("createUserId", data.getCreateUserid());
		}
		try {
			final List<User> list = userDAOWrite
					.getUserListByConditionWithPage(params);
			if (CollectionUtils.isEmpty(list)) {
				// throw new OuserMangeException(ErrorCode.output_null,
				// "output null");
				return null;
			}
			final List<UserNameOutputDTO> result = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				final UserNameOutputDTO userNameOutputDTO = new UserNameOutputDTO();
				BeanUtils.copyProperties(list.get(i), userNameOutputDTO);
				result.add(userNameOutputDTO);
			}
			return result;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}

	@Override
	public int getTotalNew(UserInputDTO data) throws OuserMangeException {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		if (StringUtils.isNotEmpty(data.getMobile())) {
			params.put("mobile", data.getMobile());
		}
		if (StringUtils.isNotEmpty(data.getUsername())) {
			params.put("username", data.getUsername() + "%");
		}
		if (StringUtils.isNotEmpty(data.getIdentityCardName())) {
			params.put("identityCardName", data.getIdentityCardName() + "%");
		}
		if (null != data.getType()) {
			params.put("type", data.getType());
		}
		if (null != data.getIsAvailable()) {
			params.put("isAvailable", data.getIsAvailable());
		}
		if (null != data.getCompanyId()) {
			params.put("companyId", data.getCompanyId());
		}
		if (null != data.getStartTime()) {
			// cal.setTime(data.getStartTime());
			// cal.add(Calendar.HOUR, -8);
			// params.put("startTime", cal.getTime());
			params.put("startTime", data.getStartTime());
		}
		if (null != data.getEndTime()) {
			// cal.setTime(data.getEndTime());
			// cal.add(Calendar.HOUR, 16);
			// params.put("endTime",cal.getTime());
			params.put("endTime", data.getEndTime());
		}
		if (null != data.getCreateUserid()) {
			params.put("createUserId", data.getCreateUserid());
		}
		try {
			final int i = userDAOWrite.getUserCntByConditionWithPage(params);
			return i;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
		// return 0;
	}

	/**
	 * 修改用户信息
	 *
	 * @param userDto
	 */
	@Override
	public void updateUserWithTx(UserDto userDto) throws SQLException {
		if (userDto.getId() == null) {
			return;
		}
		final User user = new User();
		BeanUtils.copyProperties(userDto, user);
		if (StringUtils.isNotEmpty(user.getPassword())) {
			userActionLogDAOWrite.insertUserActionLog(user,
					UserActionLogEnum.changePassword.getType());
		}
		int i = userDAOWrite.updateByPrimaryKeySelective(user);

		if (i > 0) {// 维护修改用户信息动作日志
			userActionLogDAOWrite.insertUserActionLog(user,
					UserActionLogEnum.completeUser.getType());
		}
		if (userDto.getPositionId() != null) {
			UserPositionPOExample example = new UserPositionPOExample();
			UserPositionPOExample.Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userDto.getId());
			List<UserPositionPO> posList = userPositionDAOWrite
					.selectByExample(example, false);
			if (posList != null && !posList.isEmpty()) {
				UserPositionPO record = new UserPositionPO();
				record.setPositionId(userDto.getPositionId());
				userPositionDAOWrite.updateByExampleSelective(record, example);
			} else {
				UserPositionPO addRecord = new UserPositionPO();
				addRecord.setUserId(userDto.getId());
				addRecord.setPositionId(userDto.getPositionId());
				addRecord.setCompanyId(userDto.getCompanyId());
				userPositionDAOWrite.insert(addRecord);
			}

		}
	}

	/**
	 * 新增默认用户
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public Long addDefaultUserWithTx(DefaultUserDTO defaultUserDTO)
			throws OuserMangeException {
		final User user = new User();
		BeanUtils.copyProperties(defaultUserDTO, user);

		// 密码进行MD5加密
		// userDto.setPassword(SecurityUtils.getMD5Str(userDto.getPassword()));

		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = new EncryptEntity();
		try {
			encryptEntity = passwordEncryptInfoWriteManage
					.getEncryptInfoByUser(encryptUser);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("查询加密算法信息失败", e1);
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
		user.setPassword(PasswordUtil.getSecretPassWord(
				defaultUserDTO.getPassword(), encryptEntity.getNewBSalt(),
				encryptEntity.getNewBVersion()));

		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		user.setbSaltUpdateTime(new Date());
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());

		if (user.getParentId() == null) {
			user.setParentId(0l);
		}
		try {
			final Long insert = userDAOWrite.insert(user);
			return insert;
		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}

	/**
	 * 根据条件查询用户列表
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public List<UserOutDTO> getUserByConditions(UserInDTO data)
			throws OuserMangeException {
		final String originalId = data.getOriginalId();
		final UserExample example = new UserExample();
		final Criteria criteria = example.createCriteria();
		final List<Long> userIdList = data.getUserIdList();
		final Date createTimeBegin = data.getCreateTimeBegin();
		final Date createTimeEnd = data.getCreateTimeEnd();
		final List<Integer> typeList = data.getTypeList();

		if (originalId != null) {
			criteria.andOriginalIdEqualTo(originalId);
		}
		if (null != data.getType()) {
			criteria.andTypeEqualTo(data.getType());
		}
		if (null != typeList && CollectionUtils.isNotEmpty(typeList)) {// 用户类型列表
			criteria.andTypeIn(typeList);
		}
		if (data.getId() != null) {
			criteria.andIdEqualTo(data.getId());
		}
		if (userIdList != null && userIdList.size() > 0) {
			criteria.andIdIn(userIdList);
		}
		if (data.getUsername() != null) {
			criteria.andUsernameLike(data.getUsername() + "%");
		}
		if (data.getIdentityCardName() != null) {
			criteria.andIdentityCardNameLike(data.getIdentityCardName() + "%");
		}
		if (data.getNickName() != null) {
			criteria.andNicknameLike(data.getNickName() + "%");
		}
		if (data.getMobile() != null) {
			criteria.andMobileLike(data.getMobile() + "%");
		}
		if (data.getEmail() != null) {
			criteria.andEmailEqualTo(data.getEmail());
		}
		if (data.getTelephone() != null) {
			criteria.andTelephoneEqualTo(data.getTelephone());
		}
		if (null != createTimeBegin) {
			criteria.andCreateTimeGreaterThanOrEqualTo(createTimeBegin);
		}
		if (null != createTimeEnd) {
			criteria.andCreateTimeLessThanOrEqualTo(createTimeEnd);
		}
		if (null != data.getCreateUserid()) {
			criteria.andCreateUseridEqualTo(data.getCreateUserid());
		}
		if (StringUtils.isNotEmpty(data.getCreateUsername())) {
			criteria.andCreateUsernameEqualTo(data.getCreateUsername());
		}
		if (null != data.getEntityId()) {
			criteria.andEntityIdEqualTo(data.getEntityId());
		}
		if (null != data.getEntityType()) {
			criteria.andEntityTypeEqualTo(data.getEntityType());
		}
		example.setLimitClauseStart(0l);
		example.setLimitClauseCount(1l);

		try {
			final List<User> list = userDAOWrite
					.selectByExample(example, false);
			final List<UserOutDTO> result = new ArrayList();
			if (CollectionUtils.isEmpty(list)) {
				return result;
			}
			for (int i = 0; i < list.size(); i++) {
				final UserOutDTO userOutDTO = new UserOutDTO();
				BeanUtils.copyProperties(list.get(i), userOutDTO);
				result.add(userOutDTO);

			}

			List<Map<String, Object>> accounts = userDAOWrite
					.getUnionAccounts(result);

			for (UserOutDTO uod : result) {
				Long userId = uod.getId();
				if (userId == null) {
					continue;
				}
				if (uod.getUnionAccount() == null) {
					uod.setUnionAccount(new HashMap<String, Map<String, Object>>());
				}
				for (Map<String, Object> account : accounts) {
					if (userId.equals(account.get("userId"))) {
						Object source = account.get("source");
						if (source.equals(1)) {
							uod.getUnionAccount().put("QQ", account);
						} else if (source.equals(2)) {
							uod.getUnionAccount().put("WECHAT", account);
						}
					}
				}
			}
			return result;

		} catch (SQLException e) {
			LogUtils.getLogger(this.getClass()).debug("查询用户信息SQL异常", e);
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}

	/**
	 * 根据条件查询用户列表
	 *
	 * @param commonInputDTO
	 * @return
	 */
	@Override
	public PageResult<UserOutDTO> getUserByConditionsWithPage(UserInDTO data)
			throws OuserMangeException {
		final String originalId = data.getOriginalId();
		final List<Long> userIdList = data.getUserIdList();
		final Date createTimeBegin = data.getCreateTimeBegin();
		final Date createTimeEnd = data.getCreateTimeEnd();
		final List<String> excludeUserNameList = data.getExcludeUserNameList();
		final Long departId = data.getDepartId();

		Map<String, Object> queryMap = new HashMap<String, Object>();

		// 分页
		int itemsPerPage = data.getItemsPerPage();
		int start = Pagination
				.getStartItem(data.getCurrentPage(), itemsPerPage);
		queryMap.put("startItem", Long.valueOf(start));
		queryMap.put("itemsPerPage", Long.valueOf(itemsPerPage));

		if (originalId != null) {
			queryMap.put("originalId", originalId);
		}
		if (data.getId() != null) {
			queryMap.put("id", data.getId());
		}
		if (userIdList != null && userIdList.size() > 0) {
			queryMap.put("userIdList", userIdList);
		}
		if (excludeUserNameList != null && excludeUserNameList.size() > 0) {
			queryMap.put("excludeUserNameList", excludeUserNameList);
		}
		if (data.getUsername() != null) {
			queryMap.put("username", data.getUsername() + "%");
		}
		if (data.getIdentityCardName() != null) {
			queryMap.put("identityCardName", data.getIdentityCardName() + "%");
		}
		if (data.getNickName() != null) {
			queryMap.put("nickName", data.getNickName() + "%");
		}
		if (data.getMobile() != null) {
			queryMap.put("mobile", data.getMobile());
		}
		if (data.getEmail() != null) {
			queryMap.put("email", data.getEmail());
		}
		if (data.getTelephone() != null) {
			queryMap.put("telephone", data.getTelephone());
		}
		if (null != createTimeBegin) {
			queryMap.put("startTime", createTimeBegin);
		}
		if (null != createTimeEnd) {
			queryMap.put("endTime", createTimeEnd);
		}
		if (null != departId) {
			queryMap.put("departId", departId);
		}
		if (null != data.getType()) {
			queryMap.put("type", data.getType());
		}
		if (null != data.getIsAvailable()) {
			queryMap.put("isAvailable", data.getIsAvailable());
		}

		try {

			final PageResult<UserOutDTO> result = new PageResult<UserOutDTO>();
			int count = userDAOWrite.getUserCntByConditionWithPage(queryMap);
			result.setTotal(count);

			final List<User> list = userDAOWrite
					.getUserListByConditionWithPage(queryMap);

			final List<UserOutDTO> pageList = new ArrayList<UserOutDTO>();
			if (CollectionUtils.isEmpty(list)) {
				result.setTotal(0);
				result.setListObj(Collections.<UserOutDTO> emptyList());
				return result;
			}
			for (int i = 0; i < list.size(); i++) {
				final UserOutDTO userOutDTO = new UserOutDTO();
				BeanUtils.copyProperties(list.get(i), userOutDTO);
				pageList.add(userOutDTO);
			}

			result.setListObj(pageList);
			return result;

		} catch (SQLException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			throw new OuserMangeException(ErrorCode.sql_error, "sql_error");
		}
	}

	@Override
	public UserCache getUserByOrginalId(String orginalId) {
		final UserExample example = new UserExample();
		final Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(orginalId);
		final UserCache orginalUser = new UserCache();
		orginalUser.setOriginalId(orginalId);
		UserExample exampleOr = new UserExample();
		Criteria criteriaOr = exampleOr.createCriteria();
		criteriaOr.andEmailEqualTo(orginalId);
		example.or(criteriaOr);
		try {
			User getCacheUser = new User();
			final List<User> list = userDAOWrite
					.selectByExample(example, false);
			// 如果查询到到
			if (!CollectionUtils.isEmpty(list)) {
				getCacheUser = list.get(0);
			}

			UserCache userCache = mobileLoginWriteManageImpl
					.getCacheUser(getCacheUser);
			return userCache;
		} catch (Exception e) {
			LogUtils.getLogger(this.getClass()).error(
					"查询用户信息异常，orginalId=" + orginalId, e);
		}
		return orginalUser;
	}

	public boolean isRepeatMobile(UserDto userDTO) throws Exception {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andMobileEqualTo(userDTO.getMobile());
		if (userDTO.getCompanyId() != null) {
			criteria.andCompanyIdEqualTo(userDTO.getCompanyId());
		}
		if (userDTO.getId() != null) {
			criteria.andIdNotEqualTo(userDTO.getId());
		}
		List list = userDAOWrite.selectByExample(userExample, false);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserByIdAndCompanyId(Long userId, Long companyId)
			throws Exception {
		UserExample example = new UserExample();
		example.createCriteria().andIdEqualTo(userId)
				.andCompanyIdEqualTo(companyId);
		List<User> userList = userDAOWrite.selectByExample(example, false);
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public Long addOneUserWithTx(UserDto userDto) throws SQLException {
		// TODO Auto-generated method stub

		// 校验用户名是否存在
		if (StringUtils.isNotEmpty(userDto.getUsername())) {
			UserDto userDto2 = new UserDto();
			userDto2.setCompanyId(userDto.getCompanyId());
			userDto2.setUsername(userDto.getUsername());
			int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
			if (cnt > 0)
				return 2l;
		}
		// 检验姓名是否存在
		if (StringUtils.isNotEmpty(userDto.getIdentityCardName())) {
			if (null != userDto.getCompanyId() && 2l == userDto.getCompanyId()) {// 卓仕才校验姓名
				UserDto userDto2 = new UserDto();
				userDto2.setCompanyId(userDto.getCompanyId());
				userDto2.setIdentityCardName(userDto.getIdentityCardName());
				int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
				if (cnt > 0)
					return 3l;
			}

		}

		// 检验企业名称是否存在
		if (StringUtils.isNotEmpty(userDto.getCompanyName())) {
			UserDto userDto2 = new UserDto();
			userDto2.setCompanyId(userDto.getCompanyId());
			userDto2.setCompanyName(userDto.getCompanyName());
			int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
			if (cnt > 0)
				return 4l;
		}

		// 校验手机号是否存在
		if (StringUtils.isNotEmpty(userDto.getMobile())) {

			UserDto userDto2 = new UserDto();
			userDto2.setMobile(userDto.getMobile());
			userDto2.setCompanyId(userDto.getCompanyId());
			int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
			if (cnt > 0)
				return 1l;
		}

		Long platformId = userDto.getUserPlatformId();
		UPlatformGroupRelationPOExample example = new UPlatformGroupRelationPOExample();
		UPlatformGroupRelationPOExample.Criteria criteria = example
				.createCriteria();
		criteria.andPlatformIdEqualTo(platformId);
		List<UPlatformGroupRelationPO> list = uPlatformGroupRelationDAORead
				.selectByExample(example, false);
		if (list != null && list.size() > 0) {
			userDto.setPlatformGroupId(list.get(0).getPlatformGroupId());
		}
		User user = new User();
		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = passwordEncryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);
		userDto.setPassword(PasswordUtil.getSecretPassWord(
				userDto.getPassword(), encryptEntity.getNewBSalt(),
				encryptEntity.getNewBVersion()));

		// -----------------userDto.setPassword(PassWordUtils.getSecretPassWord(userDto.getPassword()));
		// 前端算法盐值信息
		Date now = new Date();
		userDto.setfAlgorithmVersion(userDto.getNewFVersion());
		userDto.setfSalt(userDto.getNewFSalt());
		if (StringUtils.isNotEmpty(userDto.getNewFSalt())) {
			userDto.setfSaltUpdateTime(now);
		}
		BeanUtils.copyProperties(userDto, user);
		user.setParentId(0L);
		user.setIsDeleted(ConstantUser.isDelete.normal);
		// 如果类型为4会员，无需审核
		if (user.getType() == ConstantUser.user_type.member) {
			user.setStatus(ConstantUser.user_status.success);
		} else {
			user.setStatus(ConstantUser.user_status.wait);
		}

		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
			user.setbSaltUpdateTime(now);
		}
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());
		if (StringUtils.isEmpty(user.getUsername())) {
			user.setUsername("");
		}
		userDAOWrite.insert(user);
		return 0l;
	}

	@Override
	public Long addOneUserAndReturnIdWithTx(UserDto userDto)
			throws SQLException {
		// TODO Auto-generated method stub
		// 校验用户名是否存在
		if (StringUtils.isNotEmpty(userDto.getUsername())) {
			UserDto userDto2 = new UserDto();
			userDto2.setCompanyId(userDto.getCompanyId());
			userDto2.setUsername(userDto.getUsername());
			int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
			if (cnt > 0)
				return -2l;
		}
		// 检验姓名是否存在
		/*
		 * if(StringUtils.isNotEmpty(userDto.getIdentityCardName())){ UserDto
		 * userDto2=new UserDto();
		 * userDto2.setCompanyId(userDto.getCompanyId());
		 * userDto2.setIdentityCardName(userDto.getIdentityCardName()); int
		 * cnt=userDAOWrite.validateRepeatOrNotByParams(userDto2); if(cnt>0)
		 * return -3l; }
		 */

		// 检验企业名称是否存在
		if (StringUtils.isNotEmpty(userDto.getCompanyName())) {
			UserDto userDto2 = new UserDto();
			userDto2.setCompanyId(userDto.getCompanyId());
			userDto2.setCompanyName(userDto.getCompanyName());
			int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
			if (cnt > 0)
				return -4l;
		}

		// 校验手机号是否存在
		if (StringUtils.isNotEmpty(userDto.getMobile())) {

			UserDto userDto2 = new UserDto();
			userDto2.setMobile(userDto.getMobile());
			userDto2.setCompanyId(userDto.getCompanyId());
			int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
			if (cnt > 0)
				return -1l;
		}

		// 校验邮箱是否存在
		if (StringUtils.isNotEmpty(userDto.getEmail())) {

			UserDto userDto2 = new UserDto();
			userDto2.setEmail(userDto.getEmail());
			int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
			if (cnt > 0)
				return -5l;
		}

		Long platformId = userDto.getUserPlatformId();
		// 赋值平台组
		UPlatformGroupRelationPOExample example = new UPlatformGroupRelationPOExample();
		UPlatformGroupRelationPOExample.Criteria criteria = example
				.createCriteria();
		criteria.andPlatformIdEqualTo(platformId);
		List<UPlatformGroupRelationPO> list = uPlatformGroupRelationDAORead
				.selectByExample(example, false);
		if (list != null && list.size() > 0) {
			userDto.setPlatformGroupId(list.get(0).getPlatformGroupId());
		}
		User user = new User();
		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = passwordEncryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);
		userDto.setPassword(PasswordUtil.getSecretPassWord(
				userDto.getPassword(), encryptEntity.getNewBSalt(),
				encryptEntity.getNewBVersion()));

		// -----------------userDto.setPassword(PassWordUtils.getSecretPassWord(userDto.getPassword()));
		// 前端算法盐值信息
		Date now = new Date();
		userDto.setfAlgorithmVersion(userDto.getNewFVersion());
		userDto.setfSalt(userDto.getNewFSalt());
		if (StringUtils.isNotEmpty(userDto.getNewFSalt())) {
			userDto.setfSaltUpdateTime(now);
		}
		BeanUtils.copyProperties(userDto, user);
		user.setParentId(0L);
		user.setIsDeleted(ConstantUser.isDelete.normal);
		// 如果类型为4会员，无需审核
		if (user.getType() == ConstantUser.user_type.member) {
			user.setStatus(ConstantUser.user_status.success);
		} else {
			user.setStatus(ConstantUser.user_status.wait);
		}

		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
			user.setbSaltUpdateTime(now);
		}
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());
		if (StringUtils.isEmpty(user.getUsername())) {
			user.setUsername("");
		}
		userDAOWrite.insert(user);
		return user.getId();
	}

	@Override
	public Long activeOrInactiveUserWithTx(UserDto userDto) throws SQLException {
		// TODO Auto-generated method stub
		return (long) userDAOWrite.activeOrInactiveUser(userDto);
	}

	@Override
	public UserExtOutDTO getUserExtByConditions(UserExtInDTO userExtInDTO)
			throws OuserMangeException, SQLException {
		UserExtOutDTO userExtOutDTO = new UserExtOutDTO();
		// 获取用户ID
		if (null == userExtInDTO.getUserId()) {
			throw new OuserMangeException("2", "传入的userId为空");
		}
		// 验证key非空
		if (CollectionUtils.isEmpty(userExtInDTO.getUserExtKeys())) {
			throw new OuserMangeException("3", "传入的key值为空");
		}
		// 验证key在枚举中是否存在
		if (userExtInDTO.getUserExtKeys().size() > 1) {
			for (String key : userExtInDTO.getUserExtKeys()) {
				if (!UserExtEnum.keyExists(key)) {
					throw new OuserMangeException("4", "key值不存在:key=" + key);
				}
			}
		}
		// 查询数据库
		// UserExtPOExample example = new UserExtPOExample();
		// example.createCriteria().andUserIdEqualTo(userExtInDTO.getUserId()).andExtKeyIn(userExtInDTO.getUserExtKeys());
		try {
			// List<UserExtPO> userExtPOList =
			// userExtDAOWrite.selectByExample(example,false);
			List<UserExtPO> userExtPOList = userExtDAOWrite
					.selectUserExtList(userExtInDTO);
			// 数据转换
			if (!CollectionUtils.isEmpty(userExtPOList)) {
				Map<String, String> userExtMap = new HashMap<>();
				for (UserExtPO thisObj : userExtPOList) {
					userExtMap.put(thisObj.getExtKey(), thisObj.getExtValue());
				}
				userExtOutDTO.setUserExtMap(userExtMap);
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return userExtOutDTO;
	}

	@Override
	public void updateUserExtByConditionsWithTx(UserExtInDTO userExtInDTO)
			throws OuserMangeException, SQLException {
		// 验证userId
		if (null == userExtInDTO.getUserId()) {
			throw new OuserMangeException("2", "传入的userId为空");
		}
		// 验证map非空
		Map<String, String> userExtMap = userExtInDTO.getUserExtMap();
		if (null == userExtMap) {
			throw new OuserMangeException("3", "传入的数据为空");
		}
		// 验证key在枚举中否存在,存在则验证value是否匹配正则
		Set<String> keys = userExtInDTO.getUserExtMap().keySet();
		for (String thisKey : keys) {
			if (!UserExtEnum.keyExists(thisKey)) {
				throw new OuserMangeException("4", "key值不存在：key=" + thisKey);
				// 存在则验证value是否匹配正则 规则
			} else {
				if (!UserExtEnum.getRegExp(thisKey, userExtMap.get(thisKey))) {
					throw new OuserMangeException("5", "value和规则不匹配：key="
							+ thisKey);
				}
			}
		}
		// 查询数据库
		// UserExtPOExample example = new UserExtPOExample();
		List<UserExtPO> updateList = new ArrayList<>();
		List<UserExtPO> insertList = new ArrayList<>();
		for (String key : keys) {
			// example.clear();
			// example.createCriteria().andUserIdEqualTo(userExtInDTO.getUserId()).andExtKeyEqualTo(key);
			// List<UserExtPO> userExtList =
			// userExtDAOWrite.selectByExample(example, false);
			List<String> userExtKeys = new ArrayList<String>();
			userExtKeys.add(key);
			userExtInDTO.setUserExtKeys(userExtKeys);
			List<UserExtPO> userExtList = userExtDAOWrite
					.selectUserExtList(userExtInDTO);
			// 只有一条
			if (!CollectionUtils.isEmpty(userExtList)
					&& userExtList.size() == 1) {
				userExtList.get(0).setExtValue(userExtMap.get(key));
				updateList.add(userExtList.get(0));
				// 没有值
			} else if (CollectionUtils.isEmpty(userExtList)) {
				UserExtPO userExtPO = new UserExtPO();
				userExtPO.setUserId(userExtInDTO.getUserId());
				userExtPO.setExtKey(key);
				userExtPO.setExtValue(userExtMap.get(key));
				userExtPO.setShowName(UserExtEnum.getShowName(key));
				insertList.add(userExtPO);
				// 大于一条报错
			} else if (userExtList.size() > 1) {
				throw new OuserMangeException("8", "数据异常");
			}
		}
		// 执行批量新增和批量更新
		if (!CollectionUtils.isEmpty(updateList)) {
			userExtDAOWrite.batchUpdate(updateList);
		}
		if (!CollectionUtils.isEmpty(insertList)) {
			userExtDAOWrite.batchInsert(insertList);
		}
	}

	@Override
	public Long addMerchantUserWithTx(UserDto userDto) throws SQLException {
		// TODO Auto-generated method stub
		// 校验手机号是否存在
		if (userDto.getMobile() != null) {
			int count = userDAOWrite.selectCountByMobileAndCompanyId(
					userDto.getMobile(), userDto.getCompanyId());
			if (count > 0) {
				return 1l;
			}
		}
		// 校验用户名是否存在
		if (userDto.getUsername() != null) {
			// 校验用户名与用户手机号字段是否重复
			int count = userDAOWrite.selectCountByUserNameAndCompanyId(
					userDto.getUsername(), userDto.getCompanyId());
			if (count > 0) {
				return 2l;
			}
		}
		Long platformId = userDto.getUserPlatformId();
		// 赋值平台组
		UPlatformGroupRelationPOExample example = new UPlatformGroupRelationPOExample();
		UPlatformGroupRelationPOExample.Criteria criteria = example
				.createCriteria();
		criteria.andPlatformIdEqualTo(platformId);
		List<UPlatformGroupRelationPO> list = uPlatformGroupRelationDAORead
				.selectByExample(example, false);
		if (list != null && list.size() > 0) {
			userDto.setPlatformGroupId(list.get(0).getPlatformGroupId());
		}
		User user = new User();
		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = passwordEncryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);
		userDto.setPassword(PasswordUtil.getSecretPassWord(
				userDto.getPassword(), encryptEntity.getNewBSalt(),
				encryptEntity.getNewBVersion()));

		// -----------------userDto.setPassword(PassWordUtils.getSecretPassWord(userDto.getPassword()));
		// 前端算法盐值信息
		Date now = new Date();
		userDto.setfAlgorithmVersion(userDto.getNewFVersion());
		userDto.setfSalt(userDto.getNewFSalt());
		if (StringUtils.isNotEmpty(userDto.getNewFSalt())) {
			userDto.setfSaltUpdateTime(now);
		}
		BeanUtils.copyProperties(userDto, user);
		user.setParentId(0L);
		user.setIsDeleted(ConstantUser.isDelete.normal);
		// 如果类型为4会员，无需审核
		if (user.getType() == ConstantUser.user_type.member) {
			user.setStatus(ConstantUser.user_status.success);
		} else {
			user.setStatus(ConstantUser.user_status.wait);
		}

		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
			user.setbSaltUpdateTime(now);
		}
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());
		if (StringUtils.isEmpty(user.getUsername())) {
			user.setUsername("");
		}
		Long l = userDAOWrite.insert(user);
		return 0l;
	}

	@Override
	public Long addMerchantUserAndReturnIdWithTx(UserDto userDto)
			throws SQLException {
		// TODO Auto-generated method stub
		// 校验用户名是否存在
		if (StringUtils.isNotEmpty(userDto.getUsername())) {
			UserDto userDto2 = new UserDto();
			userDto2.setCompanyId(userDto.getCompanyId());
			userDto2.setUsername(userDto.getUsername());
			int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
			if (cnt > 0)
				return -2l;
		}

		// 校验手机号是否存在
		if (StringUtils.isNotEmpty(userDto.getMobile())) {

			UserDto userDto2 = new UserDto();
			userDto2.setMobile(userDto.getMobile());
			userDto2.setCompanyId(userDto.getCompanyId());
			int cnt = userDAOWrite.validateRepeatOrNotByParams(userDto2);
			if (cnt > 0)
				return -1l;
		}

		Long platformId = userDto.getUserPlatformId();
		UPlatformGroupRelationPOExample example = new UPlatformGroupRelationPOExample();
		UPlatformGroupRelationPOExample.Criteria criteria = example
				.createCriteria();
		criteria.andPlatformIdEqualTo(platformId);
		List<UPlatformGroupRelationPO> list = uPlatformGroupRelationDAORead
				.selectByExample(example, false);
		if (list != null && list.size() > 0) {
			userDto.setPlatformGroupId(list.get(0).getPlatformGroupId());
		}

		User user = new User();
		User encryptUser = null;// 1 用于查询密码加密信息
		// 2 查询系统当前的密码加密信息
		EncryptEntity encryptEntity = passwordEncryptInfoWriteManage
				.getEncryptInfoByUser(encryptUser);
		userDto.setPassword(PasswordUtil.getSecretPassWord(
				userDto.getPassword(), encryptEntity.getNewBSalt(),
				encryptEntity.getNewBVersion()));

		// -----------------userDto.setPassword(PassWordUtils.getSecretPassWord(userDto.getPassword()));
		// 前端算法盐值信息
		Date now = new Date();
		userDto.setfAlgorithmVersion(userDto.getNewFVersion());
		userDto.setfSalt(userDto.getNewFSalt());
		if (StringUtils.isNotEmpty(userDto.getNewFSalt())) {
			userDto.setfSaltUpdateTime(now);
		}
		BeanUtils.copyProperties(userDto, user);
		user.setParentId(0L);
		user.setIsDeleted(ConstantUser.isDelete.normal);
		// 如果类型为4会员，无需审核
		if (user.getType() == ConstantUser.user_type.member) {
			user.setStatus(ConstantUser.user_status.success);
		} else {
			user.setStatus(ConstantUser.user_status.wait);
		}

		// 3 保存加密信息到数据库 盐、加密算法version、盐更新时间
		user.setbSalt(encryptEntity.getNewBSalt());
		if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
			user.setbSaltUpdateTime(now);
		}
		user.setbAlgorithmVersion(encryptEntity.getNewBVersion());
		if (StringUtils.isEmpty(user.getUsername())) {
			user.setUsername("");
		}
		userDAOWrite.insert(user);
		return user.getId();
	}

	/**
	 * 根据邮箱查询用户记录
	 */
	@Override
	public List<User> getUserByEmail(User user) throws SQLException {
		List<User> list;
		try {
			list = userDAOWrite.selectListUserByuser(user);
			return list;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public UserAccountDTO getUserAccount(Long userId) throws Exception {
		UserAccountPOExample example = new UserAccountPOExample();
		UserAccountPOExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		UserAccountPO accountPO = userAccountDAOWrite.selectOneByExample(
				example, false);
		UserAccountDTO accountDTO = new UserAccountDTO();
		if (accountPO != null) {
			BeanUtils.copyProperties(accountPO, accountDTO);
		}

		return accountDTO;
	}

	@Override
	public UserGradeDTO getUserGrade(Long id) throws Exception {
		UserGradePO accountPO = userGradeDAOWrite.selectByPrimaryKey(id, false);
		UserGradeDTO accountDTO = new UserGradeDTO();
		BeanUtils.copyProperties(accountPO, accountDTO);
		return accountDTO;
	}

}
