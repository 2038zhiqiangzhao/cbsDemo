package com.people2000.user.business.utils;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2015/4/4. 目的：自动对数据公用字段的设置 ，比如createTime createUserId等
 */
@Aspect
@Component
public class DBAspect {

	@Before("execution(* com.ibatis.sqlmap.client.SqlMapExecutor.insert*(..)))")
	public void insert(JoinPoint jp) {
		if (jp.getArgs() == null) {
			return;
		}
		String tableName = null;
		for (Object bean : jp.getArgs()) {
			try {
				if (bean instanceof String) {// 第一个参数是String,获取表名
					String tmp = (String) bean;
					if (tmp != null && tmp.indexOf(".") >= 0) {
						tableName = tmp.substring(0, tmp.indexOf("."));
					}
					continue;
				}
				if (bean instanceof List) {// 批量插入
					for (Object bean2 : (List) bean) {
						// 主键使用序列生成
						if (PropertyUtils.isReadable(bean2, "id")) {
							if (StringUtils.isNotEmpty(tableName)) {
								/*
								 * Long uuid= SEQUtil.getUUID(tableName);
								 * PropertyUtils.setProperty(bean2, "id",uuid);
								 */
							}
						}

						if (PropertyUtils.isReadable(bean2, "createTime")) {
							PropertyUtils.setProperty(bean2, "createTime",
									new Date());
						}
						if (PropertyUtils.isReadable(bean2, "createUserId")) {
							if (ConstantUser.isWebENV()) {

								Long createId = null;
								try {
									// createId =
									// CommonUtils.getCurretnOperateId();
								} catch (Exception e) {
									LogFactory.getLog(this.getClass()).error(
											e.getMessage(), e);
								}
								PropertyUtils.setProperty(bean2,
										"createUserId", createId);
							}
						}
						if (PropertyUtils.isReadable(bean2, "isDeleted")
								&& PropertyUtils
										.getProperty(bean2, "isDeleted") == null) {
							PropertyUtils.setProperty(bean2, "isDeleted", 0);
						}
						// 添加companyId
						addCompanyId(bean2, tableName);

					}

					continue;
				}
				// 主键使用序列生成
				if (PropertyUtils.isReadable(bean, "id")) {
					if (StringUtils.isNotEmpty(tableName)) {
						/*
						 * Long uuid= SEQUtil.getUUID(tableName);
						 * PropertyUtils.setProperty(bean, "id",uuid);
						 */
					}
				}

				if (PropertyUtils.isReadable(bean, "createTime")) {
					PropertyUtils.setProperty(bean, "createTime", new Date());
				}
				if (PropertyUtils.isReadable(bean, "createUserId")) {
					if (ConstantUser.isWebENV()) {

						Long createId = null;
						try {
							// createId = CommonUtils.getCurretnOperateId();
						} catch (Exception e) {
							LogFactory.getLog(this.getClass()).error(
									e.getMessage(), e);
							LogFactory.getLog(getClass()).error(e.getMessage(),
									e);
							;
						}
						PropertyUtils.setProperty(bean, "createUserId",
								createId);
					}
				}
				if (PropertyUtils.isReadable(bean, "isDeleted")
						&& PropertyUtils.getProperty(bean, "isDeleted") == null) {
					PropertyUtils.setProperty(bean, "isDeleted", 0);
				}
				// 添加companyId
				addCompanyId(bean, tableName);

			} catch (Exception e) {
				LogFactory.getLog(getClass()).error(e.getMessage(), e);
			}
		}
	}

	@Before("execution(* com.ibatis.sqlmap.client.SqlMapExecutor.update*(..)))")
	public void update(JoinPoint jp) {
		if (jp.getArgs() == null) {
			return;
		}
		String tableName = null;
		String firstArgs = null;
		for (Object bean : jp.getArgs()) {
			try {

				if (bean instanceof String) {// 第一个参数是String,获取表名
					String tmp = (String) bean;
					firstArgs = tmp;
					if (tmp != null && tmp.indexOf(".") >= 0) {
						tableName = tmp.substring(0, tmp.indexOf("."));
					}
					if (null != tableName && tableName.indexOf("2") >= 0) {// u_user2这种情况
						tableName = tableName.substring(0,
								tableName.indexOf("2"));
					}
					continue;
				}
				// updateByExampleSelective这种类型
				if (StringUtils.isNotEmpty(firstArgs)) {
					if (firstArgs.indexOf("updateByExampleSelective") >= 0) {
						List oredCriterias = (List) PropertyUtils.getProperty(
								bean, "oredCriteria");
						if (oredCriterias != null && oredCriterias.size() > 0) {
							Object oredCriteri = oredCriterias.get(0);
							boolean excludeFlag = false;
							for (ExcludeTableList e : ExcludeTableList.values()) {
								if (tableName.equals(e.toString())) {
									excludeFlag = true;
									break;
								}
							}

						}
					}
				}

				if (PropertyUtils.isReadable(bean, "updateTime")) {
					PropertyUtils.setProperty(bean, "updateTime", new Date());
				}
				if (PropertyUtils.isReadable(bean, "updateUserId")) {
					if (ConstantUser.isWebENV()) {

						PropertyUtils.setProperty(bean, "updateUserId",
								CommonUtils.getCurretnOperateId());
					}
				}

				// 添加companyid
				addCompanyId(bean, tableName);

			} catch (Exception e) {
				LogFactory.getLog(getClass()).error(e.getMessage(), e);
				;
			}
		}
	}

	@Before("execution(* com.ibatis.sqlmap.client.SqlMapExecutor.delete*(..)))")
	public void delete(JoinPoint jp) {
		if (jp.getArgs() == null) {
			return;
		}
		String tableName = null;
		for (Object bean : jp.getArgs()) {
			try {

				if (bean instanceof String) {// 第一个参数是String,获取表名
					String tmp = (String) bean;
					if (tmp != null && 0 <= tmp.indexOf(".")) {
						tableName = tmp.substring(0, tmp.indexOf("."));
					}

					if (null != tableName && 0 <= tableName.indexOf("2")) {// u_user2这种情况
						tableName = tableName.substring(0,
								tableName.indexOf("2"));
					}
					continue;
				}

				if (PropertyUtils.isReadable(bean, "updateTime")) {
					PropertyUtils.setProperty(bean, "updateTime", new Date());
				}
				if (PropertyUtils.isReadable(bean, "deleteUserId")) {
					if (ConstantUser.isWebENV()) {
						PropertyUtils.setProperty(bean, "deleteUserId",
								CommonUtils.getCurretnOperateId());

					}
				}
				if (PropertyUtils.isReadable(bean, "isDeleted")) {
					PropertyUtils.setProperty(bean, "isDeleted", 0);
				}

				// 添加companyid
				addCompanyId(bean, tableName);
			} catch (Exception e) {
				LogFactory.getLog(getClass()).error(e.getMessage(), e);
				;
			}
		}
	}

	/**
	 * 查询的时候自动增加上 delete_is = 0
	 *
	 * @param jp
	 */
	@Before("execution(* com.ibatis.sqlmap.client.SqlMapExecutor.query*(..)))")
	// @Before("execution(* com.odianyun.user.common.dao..*.select*(..)))")
	public void queryForisDeleted(JoinPoint jp) {
		if (jp.getArgs() == null) {
			return;
		}
		String tableName = null;
		for (Object bean : jp.getArgs()) {
			try {
				if (bean instanceof String) {// 第一个参数是String,获取表名
					String tmp = (String) bean;
					if (tmp != null && tmp.indexOf(".") >= 0) {
						tableName = tmp.substring(0, tmp.indexOf("."));
					}
					if (null != tableName && tableName.indexOf("2") >= 0) {// u_user2这种情况
						tableName = tableName.substring(0,
								tableName.indexOf("2"));
					}
					continue;
				}

				if (bean.getClass().getName().contains("Example")) {
					List oredCriterias = (List) PropertyUtils.getProperty(bean,
							"oredCriteria");
					if (oredCriterias != null && oredCriterias.size() > 0) {
						Object oredCriteri = oredCriterias.get(0);
						MethodUtils.invokeMethod(oredCriteri,
								"andIsDeletedEqualTo", 0);// 未删除 或者 是null
						boolean excludeFlag = false;
						for (ExcludeTableList e : ExcludeTableList.values()) {
							if (tableName.equals(e.toString())) {
								excludeFlag = true;
								break;
							}
						}
						if (excludeFlag == false) {
						}

					}

					continue;
				} else {
					// 添加companyid
					addCompanyId(bean, tableName);
				}

			} catch (Exception e) {
				LogFactory.getLog(getClass()).error(e.getMessage(), e);
				;
			}
		}
	}

	public void addCompanyId(Object bean, String tableName) {
		if (StringUtils.isNotEmpty(tableName)) {
			// companyId维护
			boolean excludeFlag = false;
			for (ExcludeTableList e : ExcludeTableList.values()) {
				if (tableName.equals(e.toString())) {
					excludeFlag = true;
					break;
				}
			}
			if (excludeFlag == false) {

			}
		}
	}

}