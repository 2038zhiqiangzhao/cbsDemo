/**
 * 
 */
package com.people2000.user.web.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.SessionContainer;
import com.people2000.user.business.write.manage.FunctionWriteManage;
import com.people2000.user.business.write.manage.RoleFunctionWriteManage;
import com.people2000.user.model.dto.FunctionDto;
import com.people2000.user.model.po.ibatis.Function;
import com.people2000.user.model.vo.FunctionVO;
import com.people2000.user.model.vo.RoleFunctionVO;
import com.people2000.user.web.BaseAction;
import com.people2000.user.web.vo.FunctionVo;

/**
 * @author crc
 * @time 2015-5-14 下午6:23:23
 * @description <pre>
 * 
 * </pre>
 *
 */
@Controller
@RequestMapping(value = "/function")
public class functionAction extends BaseAction {

	@Resource(name = "functionWriteManage")
	private FunctionWriteManage functionWriteManage;

	@Resource(name = "roleFunctionWriteManage")
	private RoleFunctionWriteManage roleFunctionWriteManage;

	private static final String VALIDATIONKEY_PATH = "/ouser/ouser-common/validationKey.properties";

	/**
	 * 获取权限
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/function")
	@ResponseBody
	public Object getFunction() throws Exception {
		return success(SessionContainer.getfunctionCodes());
	}

	/**
	 * 
	 * @description <pre>
	 * 获取所有权限
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllFunction", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllFunction(@RequestParam Integer type, Long id)
			throws Exception {
		LogUtils.getLogger(functionAction.class).debug(
				"获取所有角色方法被调用,type=" + type + " id=" + id);
		List<Function> list = functionWriteManage.getAllFunction(type);
		Set<Long> set = roleFunctionWriteManage.getFunctionIdsByRoleId(id);
		List<FunctionVo> listVo = new ArrayList<FunctionVo>();

		for (Function function : list) {
			FunctionVo functionVo = new FunctionVo(function, set);
			listVo.add(functionVo);
		}
		return success(listVo);
	}

	/**
	 * 
	 * @description <pre>
	 * 根据平台获取所有权限
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllFunctionByPlatform", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllFunctionByPlatform(@RequestBody Function function)
			throws Exception {
		Long platformId = function.getPlatformId();
		LogUtils.getLogger(functionAction.class).debug(
				"根据平台获取所有权限被调用,platformId=" + platformId);
		if (platformId == null || "".equals(platformId)) {
			return success(null);
		}
		List<Function> list = functionWriteManage
				.getAllFunctionByPlatformId(platformId);
		List<FunctionVo> listVo = new ArrayList<FunctionVo>();
		for (Function function1 : list) {
			FunctionVo functionVo = new FunctionVo(function1);
			listVo.add(functionVo);
		}
		return success(listVo);
	}

	/**
	 * 
	 * @description <pre>
	 * 根据用户当前平台获取用户所有权限
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllUserFunctionByPlatform", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllUserFunctionByPlatform(@RequestBody FunctionVO function)
			throws Exception {
		List<Function> list = functionWriteManage
				.getAllUserFunctionByPlatformId(function);
		List<FunctionVo> listVo = new ArrayList<FunctionVo>();
		for (Function function1 : list) {
			FunctionVo functionVo = new FunctionVo(function1);
			listVo.add(functionVo);
		}
		return success(listVo);
	}

	/**
	 * 
	 * @description <pre>
	 * 根据角色获取用户所有权限
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllRoleFunctionByPlatform", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllRoleFunctionByPlatform(@RequestBody FunctionVO function)
			throws Exception {
		List<Function> list = new ArrayList<Function>();
		if (function.getPlatformId() != null) {
			list = functionWriteManage.getAllFunctionByPlatformId(function
					.getPlatformId());
		}
		Set<Long> set = new HashSet<Long>();
		if (function.getRoleId() != null) {
			set = roleFunctionWriteManage.getFunctionIdsByRoleId(function
					.getRoleId());
		}
		List<FunctionVo> listVo = new ArrayList<FunctionVo>();
		for (Function function1 : list) {
			if (function1.getType() != null && function1.getType() == 1) {
				function1.setName(StringUtils.isEmpty(function1.getName()) ? ""
						: "（菜单)_" + function1.getName());
			}
			if (function1.getType() != null && function1.getType() == 2) {
				function1.setName(StringUtils.isEmpty(function1.getName()) ? ""
						: "（按钮)_" + function1.getName());
			}
			if (function1.getType() != null && function1.getType() == 3) {
				function1.setName(StringUtils.isEmpty(function1.getName()) ? ""
						: "（资源)_" + function1.getName());
			}
			FunctionVo functionVo = new FunctionVo(function1, set);
			listVo.add(functionVo);
		}
		return success(listVo);
	}

	/**
	 * 
	 * @description <pre>
	 * 根据id查询对应权限
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getFunctionById", method = RequestMethod.POST)
	@ResponseBody
	public Object getFunctionById(@RequestBody Function function)
			throws Exception {
		LogUtils.getLogger(functionAction.class).debug(
				"根据id查询对应权限被调用,id=" + function.getId());
		Function result = functionWriteManage.getFunctionById(function.getId());
		return success(result);
	}

	/**
	 * 
	 * @description <pre>
	 * 保存角色与权限的关联关系
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveRoleFunction", method = RequestMethod.POST)
	@ResponseBody
	public Object saveRoleFunction(@RequestBody RoleFunctionVO roleFunctionVO)
			throws Exception {
		LogUtils.getLogger(functionAction.class).debug(
				"修改角色与权限关联关系action被调用,roleId=" + roleFunctionVO.getRoleId()
						+ " functionIds=" + roleFunctionVO.getFunctionIds()
						+ "platformId=" + roleFunctionVO.getPlatformId());
		roleFunctionWriteManage.saveRoleFunctionWithTx(
				roleFunctionVO.getRoleId(), roleFunctionVO.getFunctionIds(),
				roleFunctionVO.getPlatformId());
		return success("");
	}

	/**
	 * 
	 * @description <pre>
	 * 根据查询条件查询权限
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getFunctionByQuery", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object getFunctionByQuery(@RequestBody Function function)
			throws Exception {
		LogUtils.getLogger(functionAction.class).debug("权限管理模块，查询权限action被调用");
		List<Function> list = functionWriteManage.getFunctionByQuery(function);
		return success(list);
	}

	/**
	 *
	 * @description <pre>
	 * 根据查询条件查询权限
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getFunctionPageByQuery", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object getFunctionPageByQuery(@RequestBody FunctionDto functionDto)
			throws Exception {
		List<Function> list = null;
		try {
			Map<String, Object> map = functionWriteManage
					.getFunctionPageByQuery(functionDto);
			return success(map.get("list"),
					Integer.parseInt(map.get("total").toString()));
		} catch (Exception e) {
			LogUtils.getLogger(functionAction.class).debug(e.getMessage(), e);
			fail(e.getMessage());
		}
		return success(list);
	}

	/**
	 * 
	 * @description <pre>
	 * 新增权限
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveFunction", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object saveFunction(@RequestBody FunctionVO function)
			throws Exception {
		LogUtils.getLogger(functionAction.class).debug("保存权限");
		Function cf = new Function();
		Function qf = new Function();
		qf.setCode(function.getCode());
		qf.setPlatformId(function.getPlatformId());
		BeanUtils.copyProperties(function, cf);
		List<Function> list = functionWriteManage.getFunctionByExactQuery(qf);
		if (list != null && list.size() > 0) {
			return fail("功能代码不能重复");
		} else {
			if (StringUtils.isEmpty(cf.getParentCode())) {
				cf.setParentCode("30");
			}
			// cf.setCompanyId(DomainCache.getCompanyId());
			functionWriteManage.saveWithTx(cf);
			return success("");
		}
	}

	/**
	 * 
	 * @description <pre>
	 * 根据查询条件查询权限
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateFunction", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object updateFunction(@RequestBody FunctionVO function)
			throws Exception {
		LogUtils.getLogger(functionAction.class).debug("修改权限");
		Function cf = new Function();
		Function qf = new Function();
		qf.setCode(function.getCode());
		qf.setId(function.getId());
		BeanUtils.copyProperties(function, cf);
		List<Function> list = functionWriteManage.getFunctionByExactQuery(qf);
		if (list != null && list.size() > 0) {
			return fail("功能代码不能重复");
		} else {
			// cf.setCompanyId(DomainCache.getCompanyId());
			functionWriteManage.updateFunctionWithTx(cf);
			return success("");
		}
	}

}
