package com.people2000.user.web.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.user.business.utils.CommonUtils;
import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.business.write.manage.EncryptInfoWriteManage;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.web.BaseAction;

/**
 * 
 * @author xuc
 * @time 2016年5月24日 下午2:30:00
 * @description 加密算法信息action
 */
@Controller
@RequestMapping(value = "/encryptInfo")
public class EncryptAction extends BaseAction {

	private Logger logger = Logger.getLogger(EncryptAction.class);

	@Resource(name = "encryptInfoWriteManage")
	private EncryptInfoWriteManage encryptInfoWriteManage;

	/**
	 * 获取加密算法信息,nologin
	 * 
	 * @param user
	 * @see @link MobileUserVO
	 * @return Object
	 */
	@RequestMapping(value = "/fEncryptInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object fEncryptInfo(@RequestBody MobileUserVO user,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (null != user) {
			// logger.info(user.getUsername() + " 开始获取前端加密算法信息");
			// 查询加密算法,查询条件带入密码
			user.setPassword(null);
		}

		EncryptEntity encryptEntity = new EncryptEntity();
		try {
			if (StringUtils.isEmpty(user.getUsername())) {// 没有传入用户名,查询系统当前的加密算法信息或者根据当前userid查询
				encryptEntity = encryptInfoWriteManage
						.getEncryptInfoByUser(null);
			} else {
				encryptEntity = encryptInfoWriteManage
						.getEncryptInfoByUser(user);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			logger.info("查询加密算法信息数据库错误");
			map.put("code", -1);
			map.put("data", null);
			return map;
		}

		// 隐藏后端加密信息
		encryptEntity.setNewBSalt(null);
		encryptEntity.setNewBVersion(null);
		encryptEntity.setOldBSalt(null);
		encryptEntity.setOldBVersion(null);

		String fEncryptFlag = getFEncryptFlag();
		encryptEntity.setfEncryptFlag(fEncryptFlag);

		map.put("code", 0);
		map.put("data", encryptEntity);

		return map;

	}

	/**
	 * 登录后根据操作人id查询加密算法信息
	 * 
	 * @param user
	 * @see @link MobileUserVO
	 * @return Object
	 * 
	 */
	@RequestMapping(value = "/fEncryptInfoById", method = RequestMethod.POST)
	@ResponseBody
	public Object fEncryptInfoById(@RequestBody MobileUserVO user,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Long userId = CommonUtils.getCurretnOperateId();

		EncryptEntity encryptEntity = new EncryptEntity();
		try {
			if (null != userId) {
				MobileUserVO user2 = new MobileUserVO();
				user2.setId(userId);
				encryptEntity = encryptInfoWriteManage
						.getEncryptInfoByUser(user2);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			logger.info("查询加密算法信息数据库错误");
			map.put("code", -1);
			map.put("data", null);
			return map;
		}

		// 隐藏后端加密信息
		encryptEntity.setNewBSalt(null);
		encryptEntity.setNewBVersion(null);
		encryptEntity.setOldBSalt(null);
		encryptEntity.setOldBVersion(null);

		String fEncryptFlag = getFEncryptFlag();
		encryptEntity.setfEncryptFlag(fEncryptFlag);

		map.put("code", 0);
		map.put("data", encryptEntity);

		return map;

	}

	private String getFEncryptFlag() {
		String fEncryptFlag = null;
		InputStream is = null;
		try {
			String path = System.getProperty("global.config.path")
					+ "/ouser/ouser-web/encrypt.properties";
			is = new BufferedInputStream(new FileInputStream(path));
			Properties properties = new Properties();
			properties.load(is);
			fEncryptFlag = properties.getProperty("fEncryptFlag");
		} catch (Exception e) {
			logger.info("查询前端加密算法开关");
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return fEncryptFlag;
	}

}
