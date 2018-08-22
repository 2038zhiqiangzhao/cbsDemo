package com.people2000.user.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.user.business.utils.SessionContainer;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.write.manage.FunctionWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.FunctionTreeDTO;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.web.BaseAction;

/**
 * 
 * 菜单读取
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:dusai,date:2017年3月29日 上午11:20:40,content:
 * </p>
 * 
 * @author dusai
 * @date 2017年3月29日 上午11:20:40
 * @since
 * @version
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseAction {

	@Autowired
	private FunctionWriteManage functionWriteManage;

	private List<Map<String, Object>> readMenuTreeAll() {
		String url = null;
		String pId = null;
		Map<String, Object> menuVo = null;

		List<FunctionTreeDTO> functionTrees = null;
		try {
			functionTrees = functionWriteManage
					.getFunctionTreeByProductId(ConstantUser.TREE_ROOT_ID);

			List<Map<String, Object>> menuReadVOs = new ArrayList<Map<String, Object>>();
			for (FunctionTreeDTO functionTreeDTO : functionTrees) {
				url = functionTreeDTO.getPath();
				pId = functionTreeDTO.getParentCode();
				if ("30".equals(pId)) {
					pId = "0";
				}

				menuVo = new HashMap<String, Object>();

				menuVo.put("id", functionTreeDTO.getCode());
				menuVo.put("pId", pId);
				menuVo.put("name", functionTreeDTO.getName());
				menuVo.put("icon", functionTreeDTO.getIcon());

				if (url != null && !"".equals(url.trim())) {
					if (functionTreeDTO.getDomain() != null) {
						url = functionTreeDTO.getDomain() + url;
					}
					menuVo.put("url", url);
					menuVo.put("target", "mainFrame");
				}

				menuReadVOs.add(menuVo);
			}
			return menuReadVOs;
		} catch (OuserMangeException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Map<String, Object>> readMenuTreeIncludes(
			String includeFunctionCodes) {
		List<Map<String, Object>> menuTree = readMenuTreeAll();

		List<Map<String, Object>> regroupMenuTreeList = new ArrayList<Map<String, Object>>();
		// 权限过滤
		for (Map<String, Object> map : menuTree) {
			String code = (String) map.get("id");
			String pId = (String) map.get("pId");
			if (includeFunctionCodes != null) {
				if (includeFunctionCodes.indexOf("," + code + ",") != -1) {
					map.put("childs", new ArrayList<Object>());
					// 如果是根节点
					if ("0".equals(pId)) {
						regroupMenuTreeList.add(map);
					} else {
						List<Map<String, Object>> childsList = findChildsMapRecursive(
								regroupMenuTreeList, map);
						if (childsList != null) {
							childsList.add(map);
						}
					}
				}
			}
		}
		return regroupMenuTreeList;
	}

	private List<Map<String, Object>> findChildsMapRecursive(
			List<Map<String, Object>> regroupMenuTreeList,
			Map<String, Object> menuTreeMap) {
		List<Map<String, Object>> childs = null;
		String pId = (String) menuTreeMap.get("pId");
		boolean isExist = false;
		for (Map<String, Object> map : regroupMenuTreeList) {
			if (map.get("id").equals(pId)) {
				isExist = true;
				return (List<Map<String, Object>>) map.get("childs");
			}
		}
		if (!isExist) {
			for (Map<String, Object> map : regroupMenuTreeList) {
				childs = findChildsMapRecursive(
						(List<Map<String, Object>>) map.get("childs"),
						menuTreeMap);
				if (childs != null) {
					return childs;
				}
			}
		}
		return childs;
	}

	@RequestMapping({ "/queryUserMenu" })
	@ResponseBody
	public Object queryUserFunction(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();

		String ut = SsoClientUtil.getUt(req);
		UserCache userCache = SsoClientUtil.getUser(ut);
		if (userCache == null) {
			return noLogin(null);
		}

		result.put("user", userCache);
		result.put("menu", readMenuTreeIncludes(userCache.getFunctionCodes()));
		result.put("functions", SessionContainer.getfunctionCodes());

		return success(result);
	}

}
