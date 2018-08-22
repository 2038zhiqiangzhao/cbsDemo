package com.people2000.common.base.mybatis.util;

import java.util.List;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;

public class MybatisStatementUtils {
	public static MappedStatement copyFromNewSql(MappedStatement ms,
			BoundSql boundSql, String sql,
			List<ParameterMapping> parameterMappings, Object parameter) {
		BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql,
				parameterMappings, parameter);
		return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
	}

	public static BoundSql copyFromBoundSql(MappedStatement ms,
			BoundSql boundSql, String sql,
			List<ParameterMapping> parameterMappings, Object parameter) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql,
				parameterMappings, parameter);
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
			String prop = mapping.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop,
						boundSql.getAdditionalParameter(prop));
			}
		}
		return newBoundSql;
	}

	public static MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(
				ms.getConfiguration(), ms.getId(), newSqlSource,
				ms.getSqlCommandType());

		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if ((ms.getKeyProperties() != null)
				&& (ms.getKeyProperties().length != 0)) {
			StringBuffer keyProperties = new StringBuffer();
			for (String keyProperty : ms.getKeyProperties()) {
				keyProperties.append(keyProperty).append(",");
			}
			keyProperties.delete(keyProperties.length() - 1,
					keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}

		builder.timeout(ms.getTimeout());

		builder.parameterMap(ms.getParameterMap());

		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());

		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());

		return builder.build();
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return this.boundSql;
		}
	}
}