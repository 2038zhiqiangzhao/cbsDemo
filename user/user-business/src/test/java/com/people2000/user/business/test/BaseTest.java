/*
 * Copyright by Odianyun and the original author or authors.
 * 
 * This document only allow internal use ,Any of your behaviors using the file
 * not internal will pay legal responsibility.
 *
 * You may learn more information about Odianyun from
 *
 *      http://www.odianyun.com
 *
 */
package com.people2000.user.business.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 单元测试基类
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:dusai,date:2017年3月28日 下午3:14:36,content:
 * </p>
 * 
 * @author dusai
 * @date 2017年3月28日 下午3:14:36
 * @since
 * @version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:user-business/spring-service.xml" })
public class BaseTest extends AbstractJUnit4SpringContextTests {

	/**
	 * 请配置你的配置环境
	 */
	static {
		System.setProperty("global.config.path",
				"D:/env/dev");
	}
	@Test
	public void test() {
	}

}