package com.people2000.user.web.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.manage.OrganizationWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.po.ibatis.OrganizationQuery;
import com.people2000.user.model.po.ibatis.UOrganization;
import com.people2000.user.model.po.ibatis.UOrganizationPO;
import com.people2000.user.model.po.ibatis.UPosition;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.vo.UserOrgVo;
import com.people2000.user.web.BaseAction;
import com.people2000.user.web.vo.OrgVo;

/**
 * 
 * @author xuc
 * @time 2016年6月28日 上午10:30:30
 * @description 部门 部门人员
 */
@Controller
@RequestMapping(value = "/org")
public class OrganizationAction extends BaseAction {

	private static final Logger logger = LogUtils
			.getLogger(OrganizationAction.class);

	@Resource(name = "organizationWriteManage")
	private OrganizationWriteManage organizationWriteManage;

	/**
	 * 
	 * @author xuc
	 * @time 下午4:43:16
	 * @todo 查询当前企业的部门
	 */
	@RequestMapping(value = "/getCurrentOrg", method = RequestMethod.POST)
	@ResponseBody
	public Object getCurrentOrg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UOrganization uOrganization = new UOrganization();

		List<UOrganizationPO> list = organizationWriteManage
				.getOrgInfo(uOrganization);
		List<OrgVo> listVo = new ArrayList<OrgVo>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (UOrganizationPO po : list) {
				OrgVo vo = new OrgVo(po);
				listVo.add(vo);
			}
		}
		return success(listVo);
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午6:13:52
	 * @todo 根据部门id查询部门结构
	 */
	@RequestMapping(value = "/getOrgById", method = RequestMethod.POST)
	@ResponseBody
	public Object getOrgById(@RequestBody UOrganization uOrganization,
			HttpServletRequest request, HttpServletResponse response) {

		logger.debug("开始根据查询条件获取部门信息,Id:" + uOrganization.getId());

		UOrganization ret = new UOrganization();
		try {
			List<UOrganizationPO> list = organizationWriteManage
					.getOrgInfo(uOrganization);
			if (CollectionUtils.isNotEmpty(list)) {
				BeanUtils.copyProperties(list.get(0), ret);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return fail("查询部门信息失败!");
		}

		return success(ret);

	}

	/**
	 * 
	 * @author xuc
	 * @time 下午6:14:36
	 * @todo 新增一个部门
	 */
	@RequestMapping(value = "/addOneOrg", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object addOneOrg(@RequestBody UOrganization uOrganization,
			HttpServletRequest request, HttpServletResponse response) {

		UOrganization uOrganization2 = new UOrganization();
		UOrganization uOrganization3 = new UOrganization();

		uOrganization3.setCode(null);// 判断部门名称是否重复
		uOrganization3.setOrgName(uOrganization.getOrgName());
		uOrganization3.setCompanyId(uOrganization.getCompanyId());

		uOrganization2.setOrgName(null);// 判断部门代码是否重复
		uOrganization2.setCode(uOrganization.getCode());
		uOrganization2.setCompanyId(uOrganization.getCompanyId());

		List<UOrganizationPO> list = new ArrayList<UOrganizationPO>();
		try {
			list = organizationWriteManage.getOrgInfo(uOrganization2);
		} catch (SQLException e) {
			return fail("查询部门信息失败!");
		}
		if (CollectionUtils.isNotEmpty(list)) {
			return fail("部门代码重复!");
		}
		try {
			list = organizationWriteManage.getOrgInfo(uOrganization3);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return fail("查询部门信息失败!");
		}
		if (CollectionUtils.isNotEmpty(list)) {
			for (UOrganizationPO po : list) {
				if (StringUtils.isNotEmpty(po.getParentCode())
						&& po.getParentCode().equals(
								uOrganization.getParentCode())) {
					return fail("同一上级部门下的部门名称不能重复!");
				}
			}

		}
		try {
			Long l = organizationWriteManage.addOneOrgWithTx(uOrganization);
			return success(l);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return fail("新增部门信息失败!");
		}
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午7:30:42
	 * @todo 修改部门
	 */
	@RequestMapping(value = "/editOneOrg", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object editOneOrg(@RequestBody UOrganization uOrganization,
			HttpServletRequest request, HttpServletResponse response) {

		logger.debug("修改部门Id:" + uOrganization.getId());

		UOrganization uOrganization2 = new UOrganization();
		UOrganization uOrganization3 = new UOrganization();

		uOrganization3.setCode(null);// 判断部门名称是否重复
		uOrganization3.setOrgName(uOrganization.getOrgName());
		uOrganization3.setCompanyId(uOrganization.getCompanyId());

		uOrganization2.setOrgName(null);// 判断部门代码是否重复
		uOrganization2.setCode(uOrganization.getCode());
		uOrganization2.setCompanyId(uOrganization.getCompanyId());

		List<UOrganizationPO> list = new ArrayList<UOrganizationPO>();
		try {
			list = organizationWriteManage.getOrgInfo(uOrganization2);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return fail("查询部门信息失败!");
		}
		if (CollectionUtils.isNotEmpty(list)) {
			for (UOrganizationPO po : list) {
				if (!po.getId().equals(uOrganization.getId())) {
					return fail("部门代码重复!");
				}
			}
		}
		try {
			list = organizationWriteManage.getOrgInfo(uOrganization3);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return fail("查询部门信息失败!");
		}
		if (CollectionUtils.isNotEmpty(list)) {
			for (UOrganizationPO po : list) {
				if (!po.getId().equals(uOrganization.getId())) {
					if (StringUtils.isNotEmpty(po.getParentCode())
							&& po.getParentCode().equals(
									uOrganization.getParentCode())) {
						return fail("同一上级部门下的部门名称不能重复!");
					}
					/* return fail("部门名称重复!"); */
				}
			}
		}
		try {
			UOrganization org2 = new UOrganization();
			org2.setId(uOrganization.getId());
			List<UOrganizationPO> listPo = organizationWriteManage
					.getOrgInfo(org2);
			if (!listPo.isEmpty()) {
				UOrganizationPO po = listPo.get(0);
				int cnt = organizationWriteManage
						.updOneOrgWithTx(uOrganization);
				if (1 == cnt) {
					// code变动,需要修改下级的parentCode
					Long cnt2 = 0l;
					if (StringUtils.isNotEmpty(uOrganization.getCode())
							&& !uOrganization.getCode().equals(po.getCode())) {
						UOrganization org = new UOrganization();
						org.setCode(po.getCode());
						org.setParentCode(uOrganization.getCode());
						org.setCompanyId(uOrganization.getCompanyId());
						cnt2 = organizationWriteManage
								.updateOrgParentCodeWithTx(org);
					}

					return success(cnt2);
				} else {
					return fail("更新部门信息错误,没有记录或者多条!");
				}
			} else {
				return success(null);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			return fail("更新部门信息失败!");
		}

	}

	/**
	 * 
	 * @author xuc
	 * @time 下午7:42:20
	 * @todo 删除部门
	 */
	@RequestMapping(value = "/deleteOneOrg", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteOneOrg(@RequestBody UOrganization uOrganization,
			HttpServletRequest request, HttpServletResponse response) {

		logger.debug("修改部门Id:" + uOrganization.getId());
		OrganizationQuery query = new OrganizationQuery();
		query.setOrgId(uOrganization.getId());
		query.setCompanyId(uOrganization.getCompanyId());

		try {
			Long l = organizationWriteManage.countOrgUser(query);
			if (l > 0) {
				return fail("部门下存在员工,不能删除");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage());
			return fail("查询部门下员工失败!");
		}

		try {
			Long l = organizationWriteManage
					.updateOrgParentCodeAndDelWithTx(uOrganization);
			return success(l);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage());
			return fail("删除部门信息失败!");
		}

	}

	/**
	 * 
	 * @author xuc
	 * @throws Exception
	 * @time 下午4:58:33
	 * @todo 查询部门内的人员
	 */
	@RequestMapping(value = "/queryOrgUser", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryOrgUser(@RequestBody OrganizationQuery query,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = organizationWriteManage.queryOrgUser(query, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return fail("查询部门人员失败!");
		}
		List<User> list = (List<User>) map.get("list");
		return success(list, Integer.parseInt(map.get("total").toString()));
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午4:58:55
	 * @todo 查询可加入部门的人员
	 */
	@RequestMapping(value = "/queryAddUser", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryAddUser(@RequestBody OrganizationQuery query,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = organizationWriteManage.queryOrgUser(query, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return fail("查询部门人员失败!");
		}
		List<User> list = (List<User>) map.get("list");
		return success(list, Integer.parseInt(map.get("total").toString()));
	}

	/**
	 * 
	 * @author xuc
	 * @time 上午9:18:04
	 * @todo 删除部门人员
	 */
	@RequestMapping(value = "/deleteOrgUser", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteOrgUser(@RequestBody OrganizationQuery query,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			int cnt = organizationWriteManage.deleteOrgUserWithTx(query);
			if (cnt > 0) {
				return success(cnt);
			} else
				return fail("删除部门人员错误,没有删除成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return fail("删除部门人员失败!");
		}

	}

	/**
	 * 
	 * @author xuc
	 * @time 上午9:18:25
	 * @todo 新增部门人员
	 */
	@RequestMapping(value = "/putOrgUser", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object putOrgUser(@RequestBody OrganizationQuery query,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			organizationWriteManage.putOrgUserWithTx(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return fail("新增部门人员失败!");
		}
		return success("新增部门人员成功");
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午4:48:33
	 * @todo 获取当前企业的所有部门信息
	 */
	@RequestMapping(value = "/getAllOrgInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllOrgInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		List<UOrganization> list = organizationWriteManage.getAllOrgInfo(map);
		/*
		 * List<OrgVo> listVo = new ArrayList<OrgVo>();
		 * if(CollectionUtils.isNotEmpty(list)){ for(UOrganizationPO po:list){
		 * OrgVo vo=new OrgVo(po); listVo.add(vo); } }
		 */
		return success(list);
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午4:48:33
	 * @todo 获取当前企业的所有岗位信息
	 */
	@RequestMapping(value = "/getAllPostitionInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllPostitionInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		List<UPosition> list = organizationWriteManage.getAllPostitionInfo(map);
		return success(list);
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午4:46:54
	 * @todo 查询部门-用户信息
	 */
	@RequestMapping(value = "/queryUserByOrgInfo", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryUserByOrgInfo(@RequestBody OrganizationQuery query) {

		// 员工是后台人员
		query.setType(ConstantUser.user_type.backend);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = organizationWriteManage.queryUserByOrgInfo(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return fail("查询部门-员工失败!");
		}
		List<UserOrgVo> list = (List<UserOrgVo>) map.get("list");
		return success(list, Integer.parseInt(map.get("total").toString()));

	}

	@RequestMapping(value = "/userOrgChange", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object userOrgChange(@RequestBody OrganizationQuery query) {

		try {
			organizationWriteManage.userOrgChangeWithTx(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return fail("用户移动部门失败");
		}
		return success(null);
	}

	@RequestMapping(value = "/addYonghu", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object addYonghu(@RequestBody UserOrgVo userOrgVo) {

		try {
			Long l = organizationWriteManage.addYonghuWithTx(userOrgVo);
			return success(l);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return fail("保存员工失败");
		}

	}

}
