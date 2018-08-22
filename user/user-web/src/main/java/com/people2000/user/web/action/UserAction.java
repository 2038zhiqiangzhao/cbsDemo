/**
 *
 */
package com.people2000.user.web.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.CommonUtils;
import com.people2000.user.business.utils.PicUrlUtils;
import com.people2000.user.business.utils.SessionContainer;
import com.people2000.user.business.write.manage.RoleWriteManage;
import com.people2000.user.business.write.manage.UserRoleWriteManage;
import com.people2000.user.business.write.manage.UserWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.User2;
import com.people2000.user.model.dto.User3;
import com.people2000.user.model.dto.UserAccountDTO;
import com.people2000.user.model.dto.UserDto;
import com.people2000.user.model.dto.UserGradeDTO;
import com.people2000.user.model.po.ibatis.Role;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.model.vo.UserAccountVO;
import com.people2000.user.model.vo.UserGradeVO;
import com.people2000.user.model.vo.UserVO;
import com.people2000.user.web.BaseAction;
import com.people2000.user.web.vo.UserVo;

/**
 * @author crc
 * @time 2015-4-28 下午2:30:56
 * @description <pre>
 * <p/>
 * </pre>
 */
@Controller
@RequestMapping(value = "/user")
public class UserAction extends BaseAction {

	@Resource(name = "userWriteManage")
	private UserWriteManage userWriteManage;

	@Resource(name = "userRoleWriteManage")
	private UserRoleWriteManage userRoleWriteManage;

	@Resource(name = "roleWriteManage")
	private RoleWriteManage roleWriteManage;

	private Logger logger = LogUtils.getLogger(UserAction.class);

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	@ResponseBody
	public Object getUser(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		User uuser = userWriteManage.getUserById(userId);
		// 头像url转换
		if (null != uuser) {
			PicUrlUtils picUrlUtils = PicUrlUtils.getInstant();
			uuser.setHeadPicUrl(picUrlUtils.getUrl(uuser.getHeadPicUrl()));
		}
		final UserVo userVo = new UserVo(uuser);
		return success(userVo);
	}

	@RequestMapping(value = "/getUserDetail", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserDetail(@RequestBody User user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 获取登陆id
		User3 uuser = userWriteManage.getUserDetailById(user.getId());
		// 头像url转换
		if (null != uuser) {
			PicUrlUtils picUrlUtils = PicUrlUtils.getInstant();
			uuser.setHeadPicUrl(picUrlUtils.getUrl(uuser.getHeadPicUrl()));
		}
		UserVo userVo = new UserVo(uuser);
		List<Role> ownRoleList = userRoleWriteManage.getUserRoleByUserId(user
				.getId());
		Role queryRole = new Role();
		List<Role> roleList = roleWriteManage.getListRoleData(queryRole);
		resultMap.put("userObj", userVo);
		resultMap.put("ownRoleList", ownRoleList);
		resultMap.put("roleList", roleList);
		return success(resultMap);
	}

	/**
	 * 
	 * <p>
	 * 获取用户账户余额
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月3日 下午1:28:53
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 * @see
	 */
	@RequestMapping(value = "/queryUserAccount", method = RequestMethod.POST)
	@ResponseBody
	public Object queryUserAccount(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		UserAccountDTO userAccountDTO = userWriteManage.getUserAccount(userId);
		UserAccountVO accountVO = new UserAccountVO();
		BeanUtils.copyProperties(userAccountDTO, accountVO);
		return success(accountVO);
	}

	/**
	 * 
	 * <p>
	 * 获取用户等级
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月3日 下午1:28:41
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 * @see
	 */
	@RequestMapping(value = "/queryUserGrade", method = RequestMethod.POST)
	@ResponseBody
	public Object queryUserGrade(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		UserGradeVO gradeVO = null;
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		User uuser = userWriteManage.getUserById(userId);
		if (uuser.getGradeId() != null) {
			UserGradeDTO gradeDTO = userWriteManage.getUserGrade(uuser
					.getGradeId());
			gradeVO = new UserGradeVO();
			BeanUtils.copyProperties(gradeDTO, gradeVO);
		}

		return success(gradeVO);
	}

	/**
	 * 根据邮箱查询用户记录(用于校验邮箱唯一)
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserByEmail", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryUserByEmail(@RequestBody User user) throws Exception {
		LogUtils.getLogger(UserAction.class).debug("根据邮箱查询用户记录action被调用");
		List<User> list = userWriteManage.getUserByEmail(user);
		int total = list.size();
		return success(list, total);
	}

	@RequestMapping(value = "/updateUserDetail", method = RequestMethod.POST)
	@ResponseBody
	public Object updateUserDetail(@RequestBody UserDto userDto) {
		try {
			if (userDto.getId() == null) {
				return fail("修改失败，参数异常");
			}
			boolean b = userWriteManage.isRepeatMobile(userDto);
			if (b) {
				return fail("修改失败，手机号已存在");
			}
			// 添加邮箱是否重复判断(德升不加邮箱唯一限制)
			if (StringUtils.isNotBlank(userDto.getEmail())) {
				User user = new User();
				user.setEmail(userDto.getEmail());
				List<User> list = userWriteManage.getUserByEmail(user);
				if (list.size() == 1) {
					User u = list.get(0);
					if (u.getId().longValue() != u.getId().longValue()) {
						return fail("修改失败，该邮箱已存在");
					}
				} else if (list.size() > 0) {
					return fail("修改失败，该邮箱已存在");
				}
			}

			userWriteManage.updateUserDetailWithTx(userDto);
			return success(null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/resetUserPassword", method = RequestMethod.POST)
	@ResponseBody
	public Object resetUserPassword(@RequestBody UserDto userDto) {
		try {
			if (userDto.getId() == null) {
				return fail("重置密码失败");
			}
			boolean b = userWriteManage.isRepeatMobile(userDto);
			if (b) {
				return fail("重置失败，手机号已存在");
			}
			userWriteManage.resetPasswordByBackendWithTx(userDto);
			return success(null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/getUserMainList", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserMainList(@RequestBody User user) throws Exception {
		List<User> list = userWriteManage.getUserMainList(user);
		List<UserVo> result = new ArrayList<UserVo>();
		if (list != null && list.size() > 0) {
			for (User user1 : list) {
				UserVo userVo = new UserVo(user1);
				result.add(userVo);
			}
		}
		return success(result);
	}

	/**
	 * 获取用户名和实名认证信息
	 *
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserForm", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserForm(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		// 获取登陆id
		Long userId = SessionContainer.getUserId();
		MobileUserVO user = userWriteManage.getUserMessageByUserId(userId);
		return success(user);

	}

	/**
	 * 保存用户基本信息
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveUser", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object saveUser(@RequestBody User uuser) throws Exception {
		Long userId = CommonUtils.getCurretnOperateId();
		uuser.setCompanyId(SessionContainer.getCompanyId());

		// 头像url转换
		PicUrlUtils picUrlUtils = PicUrlUtils.getInstant();
		uuser.setHeadPicUrl(picUrlUtils.setUrl(uuser.getHeadPicUrl()));

		userWriteManage.saveUserWithTx(uuser, userId);
		return success("");
	}

	/**
	 * 获取数据
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitUser", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUser(@RequestBody User uuser) throws Exception {
		Long userId = CommonUtils.getCurretnOperateId();
		userWriteManage.submitUserWithTx(uuser, userId);
		return success("");
	}

	@RequestMapping(value = "/getUserChildAccount", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserChildAccount(@RequestBody UserDto userDto)
			throws Exception {
		// 获取登陆id
		LogUtils.getLogger(UserAction.class).debug("获取子账户action被调用");
		Long userId = CommonUtils.getCurretnOperateId();
		userDto.setId(userId);
		List<User2> users = userWriteManage.getUserChildAccountByQuery(userDto);
		return success(users);
	}

	@RequestMapping(value = "/saveUserChildAccount", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object saveUserChildAccount(@RequestBody UserDto userDto)
			throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		userDto.setParentId(userId);
		userDto.setCompanyId(SessionContainer.getCompanyId());
		Long childId = userWriteManage.saveUserChildAccountWithTx(userDto);
		return success(childId);
	}

	@RequestMapping(value = "/deleteChildAccount")
	@ResponseBody
	public Object deleteChildAccount(@RequestParam Long id) throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		userWriteManage.deleteChildAccountWithTx(id, userId);
		return success("");
	}

	/**
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 修改密码
	 * </pre>
	 */
	@RequestMapping(value = "/updatePassWord", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePassWord(@RequestBody UserDto userDto) throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		userDto.setId(userId);
		int code = userWriteManage.updatePassWordWithTx(userDto);
		return success(code);
	}

	/**
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 运营管理员管理
	 * </pre>
	 */
	@RequestMapping(value = "/queryListManageUserData", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryListManageUserData(@RequestBody UserDto userDto)
			throws Exception {
		LogUtils.getLogger(UserAction.class).debug("运营管理员管理action被调用");
		List<User> list = userWriteManage.getManageUserByQuery(userDto);
		List<UserVo> resultList = new ArrayList<UserVo>();
		if (list != null && list.size() > 0) {
			for (User user : list) {
				UserVo userVo = new UserVo(user);
				resultList.add(userVo);
			}
		}

		return success(resultList);
	}

	/**
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 所有用户管理
	 * </pre>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAllUserDataPage", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryAllUserDataPage(@RequestBody UserDto userDto)
			throws Exception {
		LogUtils.getLogger(UserAction.class).debug("用户管理action被调用");
		Map<String, Object> map = userWriteManage.getUserByQueryPage(userDto);
		List<User> list = (List<User>) map.get("list");
		List<UserVo> resultList = new ArrayList<UserVo>();
		if (list != null && list.size() > 0) {
			for (User user : list) {
				UserVo userVo = new UserVo(user);
				resultList.add(userVo);
			}
		}
		return success(resultList,
				Integer.parseInt(map.get("total").toString()));
	}

	/**
	 * @param userDto
	 * @return
	 * @throws Exception
	 * @description <pre>
	 * 运营管理员管理
	 * </pre>
	 */
	@RequestMapping(value = "/saveManageUser", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object saveManageUser(@RequestBody UserDto userDto) throws Exception {
		// todo 添加公司id
		LogUtils.getLogger(UserAction.class).debug("运营管理员管理action被调用");
		userDto.setUserPlatformId(1L);
		int code = userWriteManage.saveManageUserWithTx(userDto);
		if (code == 0) {
			return success("");
		} else if (code == 1) {
			return fail("密码不能为空");
		} else if (code == 2) {
			return fail("两次密码不一致");
		} else if (code == 3) {
			return fail("用户名称不能为空");
		} else if (code == 4) {
			return fail("用户名已存在");
		} else {
			return fail("系统异常");
		}
	}

	@RequestMapping(value = "/getUserRole")
	@ResponseBody
	public Object getUserRole(@RequestParam Long id) throws Exception {
		List<HashMap<String, Object>> list = userRoleWriteManage
				.getUserRole(id);
		return success(list);
	}

	@RequestMapping(value = "/deleteUserRole")
	@ResponseBody
	public Object deleteUserRole(@RequestParam List<Long> ids, Long id)
			throws Exception {
		userRoleWriteManage.deleteUserRoleWithTx(ids);
		List<HashMap<String, Object>> list = userRoleWriteManage
				.getUserRole(id);
		return success(list);
	}

	@RequestMapping(value = "/addUserRole")
	@ResponseBody
	public Object addUserRole(@RequestParam Long roleId, Long userId)
			throws Exception {
		userRoleWriteManage.addUserRoleWithTx(roleId, userId);
		List<HashMap<String, Object>> list = userRoleWriteManage
				.getUserRole(userId);
		return success(list);
	}

	@RequestMapping(value = "/checkUserPass")
	@ResponseBody
	public Object checkUserPass(@RequestParam Long id) throws Exception {
		userWriteManage.checkUserWithTx(id, ConstantUser.user_status.success);
		return success("");
	}

	//
	// @RequestMapping(value = "/checkUserNoPass")
	// @ResponseBody
	// public Object checkUserNoPass(@RequestParam Long id) throws Exception {
	// userWriteManage.checkUserWithTx(id, ConstantUser.user_status.fail);
	// return success("");
	// }

	@RequestMapping(value = "/getChildUser")
	@ResponseBody
	public Object getChildUser(@RequestParam Long userId) throws Exception {
		UserVO userVO = userWriteManage.getChildUser(userId);
		return success(userVO);
	}

	@RequestMapping(value = "/updateUserChildAccount", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object updateUserChildAccount(@RequestBody UserDto userDto)
			throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		userWriteManage.UpdateUserChildAccountWithTx(userDto, userId);
		return success("");
	}

	@RequestMapping(value = "/updateUser", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object updateUser(@RequestBody UserDto userDto) {
		try {
			// 获取登陆id
			Long userId = CommonUtils.getCurretnOperateId();
			userDto.setId(userId);

			// 头像url转换
			PicUrlUtils picUrlUtils = PicUrlUtils.getInstant();
			userDto.setHeadPicUrl(picUrlUtils.setUrl(userDto.getHeadPicUrl()));

			userWriteManage.updateUserWithTx(userDto);
			return success(null);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return fail(e.getErrorCode());
		}
	}
}
