package com.people2000.user.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.read.manage.PlatformGroupReadManage;
import com.people2000.user.business.utils.CacheCommUtils;
import com.people2000.user.business.write.manage.PlatformGroupWriteManage;
import com.people2000.user.model.dto.PlatformGroupDTO;
import com.people2000.user.model.dto.PlatformGroupRelationDTO;
import com.people2000.user.model.vo.PlatformGroupRelationVO;
import com.people2000.user.model.vo.PlatformGroupVO;
import com.people2000.user.model.vo.PlatformVO;
import com.people2000.user.web.BaseAction;

@Controller
@RequestMapping(value = "/platform")
public class PlatformAction extends BaseAction {

	@Resource(name = "platformGroupReadManage")
	private PlatformGroupReadManage platformGroupReadManage;

	@Resource(name = "platformGroupWriteManage")
	private PlatformGroupWriteManage platformGroupWriteManage;

	/**
	 * 平台管理
	 * 
	 * @param platformVO
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryPlatformList", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPlatformList(@RequestBody PlatformVO platformVO)
			throws Exception {
		LogUtils.getLogger(PlatformAction.class).debug("平台管理action被调用");
		List<JSONObject> dataList = new ArrayList<JSONObject>();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("platformName", "运营后台");
		jsonObject.put("id", "1");
		dataList.add(jsonObject);

		return success(dataList);
	}

	/**
	 * 新增平台
	 * 
	 * @param platformVO
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPlatform", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object addPlatform(@RequestBody PlatformVO platformVO)
			throws Exception {
		LogUtils.getLogger(PlatformAction.class).debug("新增平台action被调用");
		return success(null);
	}

	/**
	 * 修改平台
	 * 
	 * @param platformVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyPlatform", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyPlatform(@RequestBody PlatformVO platformVO)
			throws Exception {
		LogUtils.getLogger(PlatformAction.class).debug("修改平台action被调用");
		return success(null);
	}

	/**
	 * 平台组查询
	 * 
	 * @param platformGroupRelationVO
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryPlatformGroupList", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPlatformGroupList(
			@RequestBody PlatformGroupRelationVO platformGroupRelationVO)
			throws Exception {
		LogUtils.getLogger(PlatformAction.class).debug("平台组查询action被调用");
		PlatformGroupRelationDTO dto = new PlatformGroupRelationDTO();
		dto.setPlatformGroupName(platformGroupRelationVO.getPlatformGroupName());
		List<PlatformGroupRelationDTO> list = platformGroupReadManage
				.queryPlatformGroupList(dto);
		return success(list);
	}

	/**
	 * 新增平台组
	 * 
	 * @param platformGroupVO
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPlatformGroup", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object addPlatformGroup(@RequestBody PlatformGroupVO platformGroupVO)
			throws Exception {
		LogUtils.getLogger(PlatformAction.class).debug("新增平台组action被调用");
		PlatformGroupDTO dto = new PlatformGroupDTO();
		dto.setPlatformGroupName(platformGroupVO.getPlatformGroupName());
		List<Long> list = new ArrayList<Long>();
		if (platformGroupVO.getPlatformIds() != null
				&& !"".equals(platformGroupVO.getPlatformIds())) {
			String[] platformId = platformGroupVO.getPlatformIds().split(",");
			for (String string : platformId) {
				list.add(Long.valueOf(string));
			}
		}
		dto.setPlatformIds(list);
		int code = platformGroupWriteManage.addPlatformGroupWithTx(dto);
		if (code == -1) {
			return fail("平台组名称已存在");
		}
		if (code == -2) {
			return fail("选中的平台已存在其他平台组");
		}
		return success(null);
	}

	/**
	 * 平台组查询
	 * 
	 * @param platformGroupRelationVO
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryGroupPlatformList", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryGroupPlatformList(
			@RequestBody PlatformGroupRelationVO platformGroupRelationVO)
			throws Exception {
		LogUtils.getLogger(PlatformAction.class).debug("平台组查询action被调用");
		Map<Long, String> platformGroupRelationMap = new HashMap<Long, String>();
		PlatformGroupRelationDTO dto = new PlatformGroupRelationDTO();
		dto.setPlatformGroupId(platformGroupRelationVO.getPlatformGroupId());
		List<PlatformGroupRelationDTO> list = platformGroupReadManage
				.queryPlatformGroupListOnly(dto);
		if (list != null && list.size() > 0) {
			for (PlatformGroupRelationDTO platformGroupRelationDTO : list) {
				platformGroupRelationMap.put(
						platformGroupRelationDTO.getPlatformId(),
						platformGroupRelationDTO.getPlatformName());
			}
		}
		return success(null);
	}

	/**
	 * 修改平台组
	 * 
	 * @param platformGroupVO
	 * @see {@link PlatformGroupVO}
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyPlatformGroup", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyPlatformGroup(
			@RequestBody PlatformGroupVO platformGroupVO) throws Exception {
		LogUtils.getLogger(PlatformAction.class).debug("修改平台组action被调用");
		PlatformGroupDTO dto = new PlatformGroupDTO();
		dto.setPlatformGroupId(platformGroupVO.getPlatformGroupId());
		dto.setPlatformGroupName(platformGroupVO.getPlatformGroupName());
		List<Long> list = new ArrayList<Long>();
		if (platformGroupVO.getPlatformIds() != null
				&& !"".equals(platformGroupVO.getPlatformIds())) {
			String[] platformId = platformGroupVO.getPlatformIds().split(",");
			for (String string : platformId) {
				list.add(Long.valueOf(string));
			}
		}
		dto.setPlatformIds(list);
		int code = platformGroupWriteManage.modifyPlatformGroupWithTx(dto);
		if (code == -1) {
			return fail("平台组名称已存在");
		}
		if (code == -2) {
			return fail("选中的平台已存在其他平台组");
		}
		return success(null);
	}

	/**
	 * 根据公司查询所有平台
	 * 
	 * @param platformVO
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryPlatformByCurrentCompany", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPlatformListByCurrentCompany(
			@RequestBody PlatformVO platformVO) throws Exception {
		LogUtils.getLogger(PlatformAction.class).debug("平台管理action被调用");
		return success(null);
	}
}
