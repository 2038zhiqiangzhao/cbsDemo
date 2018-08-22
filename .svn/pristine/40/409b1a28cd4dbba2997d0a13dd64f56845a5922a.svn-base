/**
 *
 */
package com.people2000.user.client.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.people2000.common.log.LogUtils;
import com.people2000.user.model.constant.ConstantUser;

/**
 * @author crc
 * @time 2015-5-3 下午12:13:15
 * @description <pre>
 * <p/>
 * </pre>
 */
public class FilterUtil {
	static String noLogin;

	static String noFunction;

	static String url;

	// CLIENT配置文件路径（相对）
	private final static String CLIENT_FILE_PATH = "ouser/ouser-client/client.properties";

	static {
		// noLogin = getProperty(ConstantUser.filter.noLogin);
		// noFunction = getProperty(ConstantUser.filter.noFunction);
		// url=getProperty(ConstantUser.filter.url);
	}

	public static final String UTF_8 = "UTF-8";

	private static String noLoginpath = noLogin + "," + "/index.html,"
			+ "/home.html," + "/login.html," + "/login/login.do,"
			+ "/register/register.do,"
			+ "/mobileRegister/checkNameAndCaptchas.do,"
			+ "/mobileRegister/checkNameAndCaptchasForm.do,"
			+ "/mobileRegister/sendCaptchasForm.do,"
			+ "/mobileRegister/sendCaptchas.do,"
			+ "/mobileLogin/checkImage.do," + "/mobileLogin/checkImageForm.do,"
			+ "/mobileRegister/sendCaptchasCodeForm.do,"
			+ "/mobileRegister/checkCaptchasForm.do,"
			+ "/mobileRegister/checkCaptchas.do,"
			+ "/mobileRegister/registerForm.do,"
			+ "/mobileRegister/register.do," + "/mobileLogin/loginForm.do,"
			+ "/mobileLogin/login.do," + "/mobileLogin/exit.do,"
			+ "/mobileRegister/checkAndRegisterForm.do,"
			+ "/mobileRegister/checkAndRegister.do,"
			+ "/mobileRegister/modifyPasswordForm.do,"
			+ "/mobileRegister/modifyPassword.do," + "/getUserInfo.do,"
			+ "/backRegisterForm.do," + "/mainRegisterForm.do,"
			+ "/childRegisterForm.do," + "/memberRegisterForm.do,"
			+ "/memberRegisterAndCheckForm.do,"
			+ "/mobileRegister/isRepeatPhoneForm.do,"
			+ "/mobileRegister/forgetPassPhoneCheckForm.do,"
			+ "/mobileRegister/checkImageForm.do,"
			+ "/businessRegisterForm.do," + "/serviceRegisterForm.do,"
			+ "/mainRegisterNoMobileForm.do," + "/mainRegisterNoMobile.do,"
			+ "/mobileRegister/checkPic.do," + "/casLogin/login.do,"
			+ "/chk.html," + "/unionLogin/iconInvisible.do";
	// "/mobileRegister/checkPwdAndModifyMobileForm.do,";//TODO 写到配置文件中去

	private static String noFunctonpath = noFunction + ",/index.html,"
			+ "/home.html," + "/login.html," + "/login/login.do,"
			+ "/register/register.do,"
			+ "/mobileRegister/checkNameAndCaptchas.do,"
			+ "/mobileRegister/checkNameAndCaptchasForm.do,"
			+ "/mobileRegister/sendCaptchasForm.do,"
			+ "/mobileRegister/sendCaptchas.do,"
			+ "/mobileLogin/checkImage.do," + "/mobileLogin/checkImageForm.do,"
			+ "/mobileRegister/sendCaptchasCodeForm.do,"
			+ "/mobileRegister/checkCaptchasForm.do,"
			+ "/mobileRegister/checkCaptchas.do,"
			+ "/mobileRegister/registerForm.do,"
			+ "/mobileRegister/register.do," + "/mobileLogin/loginForm.do,"
			+ "/mobileLogin/login.do," + "/mobileLogin/exit.do,"
			+ "/mobileRegister/checkAndRegisterForm.do,"
			+ "/mobileRegister/checkAndRegister.do,"
			+ "/mobileRegister/modifyPasswordForm.do,"
			+ "/mobileRegister/modifyPassword.do," + "/getUserInfo.do,"
			+ "/backRegisterForm.do," + "/mainRegisterForm.do,"
			+ "/childRegisterForm.do," + "/memberRegisterForm.do,"
			+ "/memberRegisterAndCheckForm.do,"
			+ "/mobileRegister/isRepeatPhoneForm.do,"
			+ "/mobileRegister/forgetPassPhoneCheckForm.do,"
			+ "/mobileRegister/checkImageForm.do,"
			+ "/businessRegisterForm.do," + "/serviceRegisterForm.do,"
			+ "/mainRegisterNoMobileForm.do," + "/mainRegisterNoMobile.do,"
			+ "/mobileRegister/checkPic.do," + "/casLogin/login.do,"
			+ "/chk.html," + "/unionLogin/iconInvisible.do";

	public static boolean checkLoginPath(String path) {
		return noLoginpath.indexOf("," + path + ",") > -1;
	}

	public static boolean checkFunctionPath(String path) {

		if (noFunctonpath.indexOf("," + path + ",") > -1) {
			return true;
		}
		return false;
	}

	public static boolean checkUrl(String path) {

		if (url.indexOf("," + path + ",") > -1) {
			return true;
		}
		return false;
	}

	public static String getProperty(String key) {
		// String path = System.getProperty("global.config.path") +
		// "/ouser/ouser-client/client.properties";
		/*
		 * File clientFile = OccPropertiesLoaderUtils.getFile("ouser" ,
		 * CLIENT_FILE_PATH ); if (clientFile == null || !clientFile.exists()){
		 * clientFile = new File(System.getProperty("global.config.path") ,
		 * CLIENT_FILE_PATH); }
		 */
		File clientFile = new File(System.getProperty("global.config.path"),
				CLIENT_FILE_PATH);

		String value = "";
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(clientFile));
			Properties properties = new Properties();
			properties.load(is);
			value = properties.get(key).toString();
		} catch (Exception e) {
			LogUtils.getLogger(FilterUtil.class).error(e.getMessage(), e);
			;
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					LogUtils.getLogger(FilterUtil.class).error(e.getMessage(),
							e);
				}
			}
		}
		return value;
	}
}
