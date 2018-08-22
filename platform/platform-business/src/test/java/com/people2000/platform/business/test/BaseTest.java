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
package com.people2000.platform.business.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 鍗曞厓娴嬭瘯鍩虹被
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:dusai,date:2017骞�3鏈�28鏃� 涓嬪崍3:14:36,content:
 * </p>
 * 
 * @author dusai
 * @date 2017骞�3鏈�28鏃� 涓嬪崍3:14:36
 * @since
 * @version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:platform-business/spring-service.xml" })
public class BaseTest extends AbstractJUnit4SpringContextTests {

	/**
	 * 璇烽厤缃綘鐨勯厤缃幆澧�
	 */
	static {
		System.setProperty("global.config.path","D:\\new_workespace\\env\\dev");
	}

	@Test
	public void test() {
		System.out.println("test begin... ");
	}

}