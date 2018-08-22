package com.people2000.common.base.mybatis.plugin;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;

@Intercepts({ @org.apache.ibatis.plugin.Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class,
		org.apache.ibatis.session.RowBounds.class,
		org.apache.ibatis.session.ResultHandler.class }) })
public class SelectInterceptor extends AbstractInterceptor {
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROW_BOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

	static String COMPANY_ID_CONDITION = "company_id =";

	public Object intercept(Invocation invocation) throws Throwable {
		if (!this.enabled) {
			return invocation.proceed();
		}
		Executor executor = (Executor) invocation.getTarget();
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[MAPPED_STATEMENT_INDEX];
		Object parameter = args[PARAMETER_INDEX];
		String id = ms.getId();
		if ((this.ignoreSet != null) && (this.ignoreSet.contains(id))) {
			return invocation.proceed();
		}
		BoundSql boundSql = ms.getBoundSql(parameter);
		String sql = boundSql.getSql();
		try {
			Statement statement = CCJSqlParserUtil.parse(sql);
			boolean hasCompanyId = false;
			if ((statement instanceof Select)) {
				Select select = (Select) statement;
				PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
				Expression where = plainSelect.getWhere();
				if (where != null) {
					String string = where.toString();
					if (string.contains(COMPANY_ID_CONDITION)) {
						hasCompanyId = true;
					}
				}
			}
			if (!hasCompanyId)
				this.logger.error("statement :" + id
						+ " has't company_id in where clause");
		} catch (Throwable ignored) {
		}
		return invocation.proceed();
	}
}
