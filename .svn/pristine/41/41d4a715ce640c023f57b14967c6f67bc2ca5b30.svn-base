package com.people2000.common.base.mybatis.plugin;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;

import com.people2000.common.base.mybatis.util.DataTypeUtils;
import com.people2000.common.session.SystemContext;

@Intercepts({ @org.apache.ibatis.plugin.Signature(type = Executor.class, method = "update", args = {
		MappedStatement.class, Object.class }) })
public class UpdateInterceptor extends AbstractInterceptor {
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;

	static String COMPANY_ID = "company_id";

	@SuppressWarnings({ "unused", "rawtypes" })
	public Object intercept(Invocation invocation) throws Throwable {
		if (!this.enabled) {
			return invocation.proceed();
		}
		Executor executor = (Executor) invocation.getTarget();
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[MAPPED_STATEMENT_INDEX];
		Object parameter = args[PARAMETER_INDEX];
		if ((this.ignoreSet != null) && (this.ignoreSet.contains(ms.getId()))) {
			return invocation.proceed();
		}
		BoundSql boundSql = ms.getBoundSql(parameter);
		String sql = boundSql.getSql();
		Statement statement = CCJSqlParserUtil.parse(sql);
		String tableName = null;
		boolean isInsert = false;
		if ((statement instanceof Insert)) {
			tableName = ((Insert) statement).getTable().getFullyQualifiedName();
			isInsert = true;
		}
		if (parameter != null) {
			if ((parameter instanceof Map)) {
				Map map = (Map) parameter;
				visitMap(isInsert, tableName, map);
			} else if ((parameter instanceof Object)) {
				_setCommonProps(isInsert, tableName, parameter);
			}

		}

		return invocation.proceed();
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private void visitMap(boolean isInsert, String tableName, Map map) {
		Collection values = map.values();
		for (Iterator i$ = values.iterator(); i$.hasNext();) {
			Object value = i$.next();
			int dataType = DataTypeUtils.getDataType(value);
			if (dataType == 30) {
				Object[] objects = (Object[]) (Object[]) value;
				for (Object object : objects)
					_setCommonProps(isInsert, tableName, object);
			} else if (dataType == 31) {
				List list = (List) value;
				for (i$ = list.iterator(); i$.hasNext();) {
					Object object = i$.next();
					_setCommonProps(isInsert, tableName, object);
				}
			}
		}
		Iterator i$;
	}

	private void _setCommonProps(boolean isInsert, String tableName,
			Object object) {
		try {
			if (isInsert) {
				if (PropertyUtils.isReadable(object, "createTime")) {
					PropertyUtils.setProperty(object, "createTime", new Date());
				}
				if (PropertyUtils.isReadable(object, "createUser")) {
					PropertyUtils.setProperty(object, "createUser",
							SystemContext.getUserId());
				}
				if (PropertyUtils.isReadable(object, "isDeleted")) {
					PropertyUtils.setProperty(object, "isDeleted", 0);
				}
				if (PropertyUtils.isReadable(object, "isAvailable")) {
					if (PropertyUtils.getProperty(object, "isAvailable") == null) {
						PropertyUtils.setProperty(object, "isAvailable", 0);
					}
				}
			} else {
				if (PropertyUtils.isReadable(object, "updateTime")) {
					PropertyUtils.setProperty(object, "updateTime", new Date());
				}
				if (PropertyUtils.isReadable(object, "updateUser")) {
					PropertyUtils.setProperty(object, "updateUser",
							SystemContext.getUserId());
				}
			}
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
	}

}
