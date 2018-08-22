package com.people2000.user.model.po.ibatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UUnionConfigPOExample {
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	protected String orderByClause;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	protected Long limitClauseStart;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	protected Long limitClauseCount;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public UUnionConfigPOExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	protected UUnionConfigPOExample(UUnionConfigPOExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.limitClauseStart = example.limitClauseStart;
		this.limitClauseCount = example.limitClauseCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public void setLimitClauseStart(Long limitClauseStart) {
		this.limitClauseStart = limitClauseStart;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public Long getLimitClauseStart() {
		return limitClauseStart;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public void setLimitClauseCount(Long limitClauseCount) {
		this.limitClauseCount = limitClauseCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public Long getLimitClauseCount() {
		return limitClauseCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public String allValue2String() {
		StringBuffer allValue = new StringBuffer();
		for (Object o : oredCriteria) {
			Criteria cr = (Criteria) o;
			if (cr.criteriaWithoutValue.size() > 0) {
				for (Object o2 : cr.criteriaWithoutValue) {
					allValue.append(o2);
				}
			}
			if (cr.criteriaWithSingleValue.size() > 0) {
				for (Object o2 : cr.criteriaWithSingleValue) {
					allValue.append(o2);
				}
			}
			if (cr.criteriaWithBetweenValue.size() > 0) {
				for (Object o2 : cr.criteriaWithBetweenValue) {
					Map map = (Map) o2;
					String map_key = map.get("condition") + "";
					allValue.append(map_key + "".trim());
					for (Object o3 : (List) map.get("values")) {
						allValue.append(o3 + "".trim());
					}
				}
			}
			if (cr.criteriaWithListValue.size() > 0) {
				for (Object o2 : cr.criteriaWithListValue) {
					Map map = (Map) o2;
					String map_key = map.get("condition") + "";
					allValue.append(map_key + "".trim());
					for (Object o3 : (List) map.get("values")) {
						allValue.append(o3 + "".trim());
					}
				}
			}
		}
		return allValue.toString();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to
	 * the database table u_union_config
	 *
	 * @abatorgenerated Mon Aug 29 12:51:51 CST 2016
	 */
	public static class Criteria {
		protected List criteriaWithoutValue;

		protected List criteriaWithSingleValue;

		protected List criteriaWithListValue;

		protected List criteriaWithBetweenValue;

		protected Criteria() {
			super();
			criteriaWithoutValue = new ArrayList();
			criteriaWithSingleValue = new ArrayList();
			criteriaWithListValue = new ArrayList();
			criteriaWithBetweenValue = new ArrayList();
		}

		public boolean isValid() {
			return criteriaWithoutValue.size() > 0
					|| criteriaWithSingleValue.size() > 0
					|| criteriaWithListValue.size() > 0
					|| criteriaWithBetweenValue.size() > 0;
		}

		public List getCriteriaWithoutValue() {
			return criteriaWithoutValue;
		}

		public List getCriteriaWithSingleValue() {
			return criteriaWithSingleValue;
		}

		public List getCriteriaWithListValue() {
			return criteriaWithListValue;
		}

		public List getCriteriaWithBetweenValue() {
			return criteriaWithBetweenValue;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteriaWithoutValue.add(condition);
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			Map map = new LinkedHashMap();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition, List values,
				String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			Map map = new LinkedHashMap();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			List list = new ArrayList();
			list.add(value1);
			list.add(value2);
			Map map = new LinkedHashMap();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return this;
		}

		public Criteria andIdIn(List values) {
			addCriterion("id in", values, "id");
			return this;
		}

		public Criteria andIdNotIn(List values) {
			addCriterion("id not in", values, "id");
			return this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return this;
		}

		public Criteria andCompanyIdIsNull() {
			addCriterion("company_id is null");
			return this;
		}

		public Criteria andCompanyIdIsNotNull() {
			addCriterion("company_id is not null");
			return this;
		}

		public Criteria andCompanyIdEqualTo(Long value) {
			addCriterion("company_id =", value, "companyId");
			return this;
		}

		public Criteria andCompanyIdNotEqualTo(Long value) {
			addCriterion("company_id <>", value, "companyId");
			return this;
		}

		public Criteria andCompanyIdGreaterThan(Long value) {
			addCriterion("company_id >", value, "companyId");
			return this;
		}

		public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
			addCriterion("company_id >=", value, "companyId");
			return this;
		}

		public Criteria andCompanyIdLessThan(Long value) {
			addCriterion("company_id <", value, "companyId");
			return this;
		}

		public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
			addCriterion("company_id <=", value, "companyId");
			return this;
		}

		public Criteria andCompanyIdIn(List values) {
			addCriterion("company_id in", values, "companyId");
			return this;
		}

		public Criteria andCompanyIdNotIn(List values) {
			addCriterion("company_id not in", values, "companyId");
			return this;
		}

		public Criteria andCompanyIdBetween(Long value1, Long value2) {
			addCriterion("company_id between", value1, value2, "companyId");
			return this;
		}

		public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
			addCriterion("company_id not between", value1, value2, "companyId");
			return this;
		}

		public Criteria andGatewayIsNull() {
			addCriterion("gateway is null");
			return this;
		}

		public Criteria andGatewayIsNotNull() {
			addCriterion("gateway is not null");
			return this;
		}

		public Criteria andGatewayEqualTo(Integer value) {
			addCriterion("gateway =", value, "gateway");
			return this;
		}

		public Criteria andGatewayNotEqualTo(Integer value) {
			addCriterion("gateway <>", value, "gateway");
			return this;
		}

		public Criteria andGatewayGreaterThan(Integer value) {
			addCriterion("gateway >", value, "gateway");
			return this;
		}

		public Criteria andGatewayGreaterThanOrEqualTo(Integer value) {
			addCriterion("gateway >=", value, "gateway");
			return this;
		}

		public Criteria andGatewayLessThan(Integer value) {
			addCriterion("gateway <", value, "gateway");
			return this;
		}

		public Criteria andGatewayLessThanOrEqualTo(Integer value) {
			addCriterion("gateway <=", value, "gateway");
			return this;
		}

		public Criteria andGatewayIn(List values) {
			addCriterion("gateway in", values, "gateway");
			return this;
		}

		public Criteria andGatewayNotIn(List values) {
			addCriterion("gateway not in", values, "gateway");
			return this;
		}

		public Criteria andGatewayBetween(Integer value1, Integer value2) {
			addCriterion("gateway between", value1, value2, "gateway");
			return this;
		}

		public Criteria andGatewayNotBetween(Integer value1, Integer value2) {
			addCriterion("gateway not between", value1, value2, "gateway");
			return this;
		}

		public Criteria andPlatformIsNull() {
			addCriterion("platform is null");
			return this;
		}

		public Criteria andPlatformIsNotNull() {
			addCriterion("platform is not null");
			return this;
		}

		public Criteria andPlatformEqualTo(Integer value) {
			addCriterion("platform =", value, "platform");
			return this;
		}

		public Criteria andPlatformNotEqualTo(Integer value) {
			addCriterion("platform <>", value, "platform");
			return this;
		}

		public Criteria andPlatformGreaterThan(Integer value) {
			addCriterion("platform >", value, "platform");
			return this;
		}

		public Criteria andPlatformGreaterThanOrEqualTo(Integer value) {
			addCriterion("platform >=", value, "platform");
			return this;
		}

		public Criteria andPlatformLessThan(Integer value) {
			addCriterion("platform <", value, "platform");
			return this;
		}

		public Criteria andPlatformLessThanOrEqualTo(Integer value) {
			addCriterion("platform <=", value, "platform");
			return this;
		}

		public Criteria andPlatformIn(List values) {
			addCriterion("platform in", values, "platform");
			return this;
		}

		public Criteria andPlatformNotIn(List values) {
			addCriterion("platform not in", values, "platform");
			return this;
		}

		public Criteria andPlatformBetween(Integer value1, Integer value2) {
			addCriterion("platform between", value1, value2, "platform");
			return this;
		}

		public Criteria andPlatformNotBetween(Integer value1, Integer value2) {
			addCriterion("platform not between", value1, value2, "platform");
			return this;
		}

		public Criteria andAppIdIsNull() {
			addCriterion("app_id is null");
			return this;
		}

		public Criteria andAppIdIsNotNull() {
			addCriterion("app_id is not null");
			return this;
		}

		public Criteria andAppIdEqualTo(String value) {
			addCriterion("app_id =", value, "appId");
			return this;
		}

		public Criteria andAppIdNotEqualTo(String value) {
			addCriterion("app_id <>", value, "appId");
			return this;
		}

		public Criteria andAppIdGreaterThan(String value) {
			addCriterion("app_id >", value, "appId");
			return this;
		}

		public Criteria andAppIdGreaterThanOrEqualTo(String value) {
			addCriterion("app_id >=", value, "appId");
			return this;
		}

		public Criteria andAppIdLessThan(String value) {
			addCriterion("app_id <", value, "appId");
			return this;
		}

		public Criteria andAppIdLessThanOrEqualTo(String value) {
			addCriterion("app_id <=", value, "appId");
			return this;
		}

		public Criteria andAppIdLike(String value) {
			addCriterion("app_id like", value, "appId");
			return this;
		}

		public Criteria andAppIdNotLike(String value) {
			addCriterion("app_id not like", value, "appId");
			return this;
		}

		public Criteria andAppIdIn(List values) {
			addCriterion("app_id in", values, "appId");
			return this;
		}

		public Criteria andAppIdNotIn(List values) {
			addCriterion("app_id not in", values, "appId");
			return this;
		}

		public Criteria andAppIdBetween(String value1, String value2) {
			addCriterion("app_id between", value1, value2, "appId");
			return this;
		}

		public Criteria andAppIdNotBetween(String value1, String value2) {
			addCriterion("app_id not between", value1, value2, "appId");
			return this;
		}

		public Criteria andAppSecretIsNull() {
			addCriterion("app_secret is null");
			return this;
		}

		public Criteria andAppSecretIsNotNull() {
			addCriterion("app_secret is not null");
			return this;
		}

		public Criteria andAppSecretEqualTo(String value) {
			addCriterion("app_secret =", value, "appSecret");
			return this;
		}

		public Criteria andAppSecretNotEqualTo(String value) {
			addCriterion("app_secret <>", value, "appSecret");
			return this;
		}

		public Criteria andAppSecretGreaterThan(String value) {
			addCriterion("app_secret >", value, "appSecret");
			return this;
		}

		public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
			addCriterion("app_secret >=", value, "appSecret");
			return this;
		}

		public Criteria andAppSecretLessThan(String value) {
			addCriterion("app_secret <", value, "appSecret");
			return this;
		}

		public Criteria andAppSecretLessThanOrEqualTo(String value) {
			addCriterion("app_secret <=", value, "appSecret");
			return this;
		}

		public Criteria andAppSecretLike(String value) {
			addCriterion("app_secret like", value, "appSecret");
			return this;
		}

		public Criteria andAppSecretNotLike(String value) {
			addCriterion("app_secret not like", value, "appSecret");
			return this;
		}

		public Criteria andAppSecretIn(List values) {
			addCriterion("app_secret in", values, "appSecret");
			return this;
		}

		public Criteria andAppSecretNotIn(List values) {
			addCriterion("app_secret not in", values, "appSecret");
			return this;
		}

		public Criteria andAppSecretBetween(String value1, String value2) {
			addCriterion("app_secret between", value1, value2, "appSecret");
			return this;
		}

		public Criteria andAppSecretNotBetween(String value1, String value2) {
			addCriterion("app_secret not between", value1, value2, "appSecret");
			return this;
		}

		public Criteria andAppDomainIsNull() {
			addCriterion("app_domain is null");
			return this;
		}

		public Criteria andAppDomainIsNotNull() {
			addCriterion("app_domain is not null");
			return this;
		}

		public Criteria andAppDomainEqualTo(String value) {
			addCriterion("app_domain =", value, "appDomain");
			return this;
		}

		public Criteria andAppDomainNotEqualTo(String value) {
			addCriterion("app_domain <>", value, "appDomain");
			return this;
		}

		public Criteria andAppDomainGreaterThan(String value) {
			addCriterion("app_domain >", value, "appDomain");
			return this;
		}

		public Criteria andAppDomainGreaterThanOrEqualTo(String value) {
			addCriterion("app_domain >=", value, "appDomain");
			return this;
		}

		public Criteria andAppDomainLessThan(String value) {
			addCriterion("app_domain <", value, "appDomain");
			return this;
		}

		public Criteria andAppDomainLessThanOrEqualTo(String value) {
			addCriterion("app_domain <=", value, "appDomain");
			return this;
		}

		public Criteria andAppDomainLike(String value) {
			addCriterion("app_domain like", value, "appDomain");
			return this;
		}

		public Criteria andAppDomainNotLike(String value) {
			addCriterion("app_domain not like", value, "appDomain");
			return this;
		}

		public Criteria andAppDomainIn(List values) {
			addCriterion("app_domain in", values, "appDomain");
			return this;
		}

		public Criteria andAppDomainNotIn(List values) {
			addCriterion("app_domain not in", values, "appDomain");
			return this;
		}

		public Criteria andAppDomainBetween(String value1, String value2) {
			addCriterion("app_domain between", value1, value2, "appDomain");
			return this;
		}

		public Criteria andAppDomainNotBetween(String value1, String value2) {
			addCriterion("app_domain not between", value1, value2, "appDomain");
			return this;
		}

		public Criteria andRedirectUriIsNull() {
			addCriterion("redirect_uri is null");
			return this;
		}

		public Criteria andRedirectUriIsNotNull() {
			addCriterion("redirect_uri is not null");
			return this;
		}

		public Criteria andRedirectUriEqualTo(String value) {
			addCriterion("redirect_uri =", value, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriNotEqualTo(String value) {
			addCriterion("redirect_uri <>", value, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriGreaterThan(String value) {
			addCriterion("redirect_uri >", value, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriGreaterThanOrEqualTo(String value) {
			addCriterion("redirect_uri >=", value, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriLessThan(String value) {
			addCriterion("redirect_uri <", value, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriLessThanOrEqualTo(String value) {
			addCriterion("redirect_uri <=", value, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriLike(String value) {
			addCriterion("redirect_uri like", value, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriNotLike(String value) {
			addCriterion("redirect_uri not like", value, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriIn(List values) {
			addCriterion("redirect_uri in", values, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriNotIn(List values) {
			addCriterion("redirect_uri not in", values, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriBetween(String value1, String value2) {
			addCriterion("redirect_uri between", value1, value2, "redirectUri");
			return this;
		}

		public Criteria andRedirectUriNotBetween(String value1, String value2) {
			addCriterion("redirect_uri not between", value1, value2,
					"redirectUri");
			return this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("status =", value, "status");
			return this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("status <>", value, "status");
			return this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("status >", value, "status");
			return this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("status >=", value, "status");
			return this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("status <", value, "status");
			return this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("status <=", value, "status");
			return this;
		}

		public Criteria andStatusIn(List values) {
			addCriterion("status in", values, "status");
			return this;
		}

		public Criteria andStatusNotIn(List values) {
			addCriterion("status not in", values, "status");
			return this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("status between", value1, value2, "status");
			return this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("status not between", value1, value2, "status");
			return this;
		}

		public Criteria andUpdateTimeIsNull() {
			addCriterion("update_time is null");
			return this;
		}

		public Criteria andUpdateTimeIsNotNull() {
			addCriterion("update_time is not null");
			return this;
		}

		public Criteria andUpdateTimeEqualTo(Date value) {
			addCriterion("update_time =", value, "updateTime");
			return this;
		}

		public Criteria andUpdateTimeNotEqualTo(Date value) {
			addCriterion("update_time <>", value, "updateTime");
			return this;
		}

		public Criteria andUpdateTimeGreaterThan(Date value) {
			addCriterion("update_time >", value, "updateTime");
			return this;
		}

		public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("update_time >=", value, "updateTime");
			return this;
		}

		public Criteria andUpdateTimeLessThan(Date value) {
			addCriterion("update_time <", value, "updateTime");
			return this;
		}

		public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
			addCriterion("update_time <=", value, "updateTime");
			return this;
		}

		public Criteria andUpdateTimeIn(List values) {
			addCriterion("update_time in", values, "updateTime");
			return this;
		}

		public Criteria andUpdateTimeNotIn(List values) {
			addCriterion("update_time not in", values, "updateTime");
			return this;
		}

		public Criteria andUpdateTimeBetween(Date value1, Date value2) {
			addCriterion("update_time between", value1, value2, "updateTime");
			return this;
		}

		public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
			addCriterion("update_time not between", value1, value2,
					"updateTime");
			return this;
		}

		public Criteria andDeleteTimeIsNull() {
			addCriterion("delete_time is null");
			return this;
		}

		public Criteria andDeleteTimeIsNotNull() {
			addCriterion("delete_time is not null");
			return this;
		}

		public Criteria andDeleteTimeEqualTo(Date value) {
			addCriterion("delete_time =", value, "deleteTime");
			return this;
		}

		public Criteria andDeleteTimeNotEqualTo(Date value) {
			addCriterion("delete_time <>", value, "deleteTime");
			return this;
		}

		public Criteria andDeleteTimeGreaterThan(Date value) {
			addCriterion("delete_time >", value, "deleteTime");
			return this;
		}

		public Criteria andDeleteTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("delete_time >=", value, "deleteTime");
			return this;
		}

		public Criteria andDeleteTimeLessThan(Date value) {
			addCriterion("delete_time <", value, "deleteTime");
			return this;
		}

		public Criteria andDeleteTimeLessThanOrEqualTo(Date value) {
			addCriterion("delete_time <=", value, "deleteTime");
			return this;
		}

		public Criteria andDeleteTimeIn(List values) {
			addCriterion("delete_time in", values, "deleteTime");
			return this;
		}

		public Criteria andDeleteTimeNotIn(List values) {
			addCriterion("delete_time not in", values, "deleteTime");
			return this;
		}

		public Criteria andDeleteTimeBetween(Date value1, Date value2) {
			addCriterion("delete_time between", value1, value2, "deleteTime");
			return this;
		}

		public Criteria andDeleteTimeNotBetween(Date value1, Date value2) {
			addCriterion("delete_time not between", value1, value2,
					"deleteTime");
			return this;
		}

		public Criteria andCreateUserIdIsNull() {
			addCriterion("create_user_id is null");
			return this;
		}

		public Criteria andCreateUserIdIsNotNull() {
			addCriterion("create_user_id is not null");
			return this;
		}

		public Criteria andCreateUserIdEqualTo(Long value) {
			addCriterion("create_user_id =", value, "createUserId");
			return this;
		}

		public Criteria andCreateUserIdNotEqualTo(Long value) {
			addCriterion("create_user_id <>", value, "createUserId");
			return this;
		}

		public Criteria andCreateUserIdGreaterThan(Long value) {
			addCriterion("create_user_id >", value, "createUserId");
			return this;
		}

		public Criteria andCreateUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("create_user_id >=", value, "createUserId");
			return this;
		}

		public Criteria andCreateUserIdLessThan(Long value) {
			addCriterion("create_user_id <", value, "createUserId");
			return this;
		}

		public Criteria andCreateUserIdLessThanOrEqualTo(Long value) {
			addCriterion("create_user_id <=", value, "createUserId");
			return this;
		}

		public Criteria andCreateUserIdIn(List values) {
			addCriterion("create_user_id in", values, "createUserId");
			return this;
		}

		public Criteria andCreateUserIdNotIn(List values) {
			addCriterion("create_user_id not in", values, "createUserId");
			return this;
		}

		public Criteria andCreateUserIdBetween(Long value1, Long value2) {
			addCriterion("create_user_id between", value1, value2,
					"createUserId");
			return this;
		}

		public Criteria andCreateUserIdNotBetween(Long value1, Long value2) {
			addCriterion("create_user_id not between", value1, value2,
					"createUserId");
			return this;
		}

		public Criteria andUpdateUserIdIsNull() {
			addCriterion("update_user_id is null");
			return this;
		}

		public Criteria andUpdateUserIdIsNotNull() {
			addCriterion("update_user_id is not null");
			return this;
		}

		public Criteria andUpdateUserIdEqualTo(Long value) {
			addCriterion("update_user_id =", value, "updateUserId");
			return this;
		}

		public Criteria andUpdateUserIdNotEqualTo(Long value) {
			addCriterion("update_user_id <>", value, "updateUserId");
			return this;
		}

		public Criteria andUpdateUserIdGreaterThan(Long value) {
			addCriterion("update_user_id >", value, "updateUserId");
			return this;
		}

		public Criteria andUpdateUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("update_user_id >=", value, "updateUserId");
			return this;
		}

		public Criteria andUpdateUserIdLessThan(Long value) {
			addCriterion("update_user_id <", value, "updateUserId");
			return this;
		}

		public Criteria andUpdateUserIdLessThanOrEqualTo(Long value) {
			addCriterion("update_user_id <=", value, "updateUserId");
			return this;
		}

		public Criteria andUpdateUserIdIn(List values) {
			addCriterion("update_user_id in", values, "updateUserId");
			return this;
		}

		public Criteria andUpdateUserIdNotIn(List values) {
			addCriterion("update_user_id not in", values, "updateUserId");
			return this;
		}

		public Criteria andUpdateUserIdBetween(Long value1, Long value2) {
			addCriterion("update_user_id between", value1, value2,
					"updateUserId");
			return this;
		}

		public Criteria andUpdateUserIdNotBetween(Long value1, Long value2) {
			addCriterion("update_user_id not between", value1, value2,
					"updateUserId");
			return this;
		}

		public Criteria andDeleteUserIdIsNull() {
			addCriterion("delete_user_id is null");
			return this;
		}

		public Criteria andDeleteUserIdIsNotNull() {
			addCriterion("delete_user_id is not null");
			return this;
		}

		public Criteria andDeleteUserIdEqualTo(Long value) {
			addCriterion("delete_user_id =", value, "deleteUserId");
			return this;
		}

		public Criteria andDeleteUserIdNotEqualTo(Long value) {
			addCriterion("delete_user_id <>", value, "deleteUserId");
			return this;
		}

		public Criteria andDeleteUserIdGreaterThan(Long value) {
			addCriterion("delete_user_id >", value, "deleteUserId");
			return this;
		}

		public Criteria andDeleteUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("delete_user_id >=", value, "deleteUserId");
			return this;
		}

		public Criteria andDeleteUserIdLessThan(Long value) {
			addCriterion("delete_user_id <", value, "deleteUserId");
			return this;
		}

		public Criteria andDeleteUserIdLessThanOrEqualTo(Long value) {
			addCriterion("delete_user_id <=", value, "deleteUserId");
			return this;
		}

		public Criteria andDeleteUserIdIn(List values) {
			addCriterion("delete_user_id in", values, "deleteUserId");
			return this;
		}

		public Criteria andDeleteUserIdNotIn(List values) {
			addCriterion("delete_user_id not in", values, "deleteUserId");
			return this;
		}

		public Criteria andDeleteUserIdBetween(Long value1, Long value2) {
			addCriterion("delete_user_id between", value1, value2,
					"deleteUserId");
			return this;
		}

		public Criteria andDeleteUserIdNotBetween(Long value1, Long value2) {
			addCriterion("delete_user_id not between", value1, value2,
					"deleteUserId");
			return this;
		}

		public Criteria andIsDeletedIsNull() {
			addCriterion("is_deleted is null");
			return this;
		}

		public Criteria andIsDeletedIsNotNull() {
			addCriterion("is_deleted is not null");
			return this;
		}

		public Criteria andIsDeletedEqualTo(Integer value) {
			addCriterion("is_deleted =", value, "isDeleted");
			return this;
		}

		public Criteria andIsDeletedNotEqualTo(Integer value) {
			addCriterion("is_deleted <>", value, "isDeleted");
			return this;
		}

		public Criteria andIsDeletedGreaterThan(Integer value) {
			addCriterion("is_deleted >", value, "isDeleted");
			return this;
		}

		public Criteria andIsDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_deleted >=", value, "isDeleted");
			return this;
		}

		public Criteria andIsDeletedLessThan(Integer value) {
			addCriterion("is_deleted <", value, "isDeleted");
			return this;
		}

		public Criteria andIsDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("is_deleted <=", value, "isDeleted");
			return this;
		}

		public Criteria andIsDeletedIn(List values) {
			addCriterion("is_deleted in", values, "isDeleted");
			return this;
		}

		public Criteria andIsDeletedNotIn(List values) {
			addCriterion("is_deleted not in", values, "isDeleted");
			return this;
		}

		public Criteria andIsDeletedBetween(Integer value1, Integer value2) {
			addCriterion("is_deleted between", value1, value2, "isDeleted");
			return this;
		}

		public Criteria andIsDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("is_deleted not between", value1, value2, "isDeleted");
			return this;
		}
	}
}