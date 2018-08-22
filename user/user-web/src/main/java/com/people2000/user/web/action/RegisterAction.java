/**
 * 
 */
package com.people2000.user.web.action;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.user.business.utils.UserCheckUtils;
import com.people2000.user.business.write.manage.MobileUserWriteManage;
import com.people2000.user.business.write.manage.UserWriteManage;
import com.people2000.user.model.dto.UserDto;
import com.people2000.user.model.vo.CaptchasVO;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.model.vo.UserVO;
import com.people2000.user.web.BaseAction;

/**
 * @author crc
 * @time 2015-5-8 下午6:26:52
 * @description <pre>
 * 注册
 * </pre>
 * 
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterAction extends BaseAction {

	@Resource(name = "userWriteManage")
	private UserWriteManage userWriteManage;

	@Resource(name = "mobileUserWriteManage")
	private MobileUserWriteManage mobileUserWriteManage;
	
	/**
	 * 商户管理：商户注册
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/businessRegister", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object businessRegister(@RequestBody MobileUserVO userDto)
			throws Exception {
		// 逻辑处理
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> map = mobileUserWriteManage
					.registerWithTx(userDto);
			return map;
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}


	/**
	 * 获取数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUser(@RequestBody UserDto userDto) throws Exception {
		int code = userWriteManage.registerWithTx(userDto);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("code", code);
		return map;
	}

	/**
	 * 发送验证码
	 *
	 * @param captchas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendCaptchas", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object sendCode(@RequestBody CaptchasVO captchas) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int code = UserCheckUtils.checkUserValid(captchas.getUserPlatformId());
		/*
		 * if(code!=0){ resultMap.put("code", -1); resultMap.put("message",
		 * "参数不完整"); return resultMap; }
		 */
		if (code == 0) {
			if (UserCheckUtils.isMobileRepeat(captchas.getMobile(),
					captchas.getUserPlatformId())) {
				resultMap.put("code", -2);
				resultMap.put("message", "手机号已存在");
				return resultMap;
			}
		}
		MobileUserVO userVO = new MobileUserVO();
		userVO.setMobile(captchas.getMobile());
		resultMap = mobileUserWriteManage.sendCodeWithTx(userVO);
		return resultMap;
	}

	/**
	 * 校验手机号是否存在
	 *
	 * @param captchas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkMobile", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object checkMobile(@RequestBody UserVO user) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		/*
		 * int code=UserCheckUtils.checkUserValid(user.getUserPlatformId());
		 * if(code!=0){ resultMap.put("code", -1); resultMap.put("message",
		 * "参数不完整"); return resultMap; }
		 */
		int code = 0;
		if (code == 0) {
			if (UserCheckUtils.isMobileRepeat(user.getMobile())) {
				resultMap.put("code", -2);
				resultMap.put("message", "手机号已存在");
				return resultMap;
			}
		}
		resultMap.put("code", 0);
		resultMap.put("message", "手机号校验通过");
		return resultMap;
	}

	/**
	 * 校验用户名是否存在
	 *
	 * @param captchas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkUserName", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object checkUserName(@RequestBody UserVO user) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int code = UserCheckUtils.checkUserValid(user.getUserPlatformId());
		/*
		 * if(code!=0){ resultMap.put("code", -1); resultMap.put("message",
		 * "参数不完整"); return resultMap; }
		 */
		if (code == 0) {
			if (UserCheckUtils.isUserNameRepeat(user.getUsername(),
					user.getUserPlatformId())) {
				resultMap.put("code", -2);
				resultMap.put("message", "用户名已存在");
				return resultMap;
			}
		}
		resultMap.put("code", 0);
		resultMap.put("message", "用户名校验通过");
		return resultMap;
	}
}
