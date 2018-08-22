package com.people2000.platform.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.people2000.platform.model.vo.UserVo;
import com.people2000.platform.web.BaseAction;

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
public class MenuController extends BaseAction {

	@RequestMapping({ "/queryUserFunction" })
	@ResponseBody
	public Object queryUserFunction() {
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = "admin";
		String functionCodes = "[{\"functionCode\":\"01\", \"icon\":\"\",\"name\":\"用户管理\",\"url\":null, \"childs\":[{\"functionCode\":\"0101\", \"name\":\"权限管理\", \"url\":\"sss\"},{\"functionCode\":\"0102\", \"name\":\"角色管理\", \"url\":\"sss\"},{\"functionCode\":\"0103\", \"name\":\"用户管理\", \"url\":\"sss\"}]},{\"functionCode\":\"02\", \"icon\":\"\",\"name\":\"消息管理\",\"url\":null, \"childs\":[{\"functionCode\":\"0201\", \"name\":\"消息模板1\", \"url\":\"sss\"},{\"functionCode\":\"0202\", \"name\":\"消息模板2\", \"url\":\"sss\"},{\"functionCode\":\"0203\", \"name\":\"消息模板3\", \"url\":\"sss\"}]}]";
		UserVo user = new UserVo();
		user.setName(userName);

		result.put("user", user);
		result.put("functions", JSONObject.parse(functionCodes));
		return success(result);
	}

}
