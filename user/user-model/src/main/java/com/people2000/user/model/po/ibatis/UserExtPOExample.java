package com.people2000.user.model.po.ibatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserExtPOExample {
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	protected String orderByClause;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	protected Long limitClauseStart;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	protected Long limitClauseCount;

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to
	 * the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public UserExtPOExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	protected UserExtPOExample(UserExtPOExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.limitClauseStart = example.limitClauseStart;
		this.limitClauseCount = example.limitClauseCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public void setLimitClauseStart(Long limitClauseStart) {
		this.limitClauseStart = limitClauseStart;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public Long getLimitClauseStart() {
		return limitClauseStart;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public void setLimitClauseCount(Long limitClauseCount) {
		this.limitClauseCount = limitClauseCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public Long getLimitClauseCount() {
		return limitClauseCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
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
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
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
	 * the database table u_user_ext
	 *
	 * @abatorgenerated Wed Oct 19 17:32:36 CST 2016
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

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return this;
		}

		public Criteria andUserIdEqualTo(Long value) {
			addCriterion("user_id =", value, "userId");
			return this;
		}

		public Criteria andUserIdNotEqualTo(Long value) {
			addCriterion("user_id <>", value, "userId");
			return this;
		}

		public Criteria andUserIdGreaterThan(Long value) {
			addCriterion("user_id >", value, "userId");
			return this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("user_id >=", value, "userId");
			return this;
		}

		public Criteria andUserIdLessThan(Long value) {
			addCriterion("user_id <", value, "userId");
			return this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Long value) {
			addCriterion("user_id <=", value, "userId");
			return this;
		}

		public Criteria andUserIdIn(List values) {
			addCriterion("user_id in", values, "userId");
			return this;
		}

		public Criteria andUserIdNotIn(List values) {
			addCriterion("user_id not in", values, "userId");
			return this;
		}

		public Criteria andUserIdBetween(Long value1, Long value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return this;
		}

		public Criteria andUserIdNotBetween(Long value1, Long value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return this;
		}

		public Criteria andExtKeyIsNull() {
			addCriterion("ext_key is null");
			return this;
		}

		public Criteria andExtKeyIsNotNull() {
			addCriterion("ext_key is not null");
			return this;
		}

		public Criteria andExtKeyEqualTo(String value) {
			addCriterion("ext_key =", value, "extKey");
			return this;
		}

		public Criteria andExtKeyNotEqualTo(String value) {
			addCriterion("ext_key <>", value, "extKey");
			return this;
		}

		public Criteria andExtKeyGreaterThan(String value) {
			addCriterion("ext_key >", value, "extKey");
			return this;
		}

		public Criteria andExtKeyGreaterThanOrEqualTo(String value) {
			addCriterion("ext_key >=", value, "extKey");
			return this;
		}

		public Criteria andExtKeyLessThan(String value) {
			addCriterion("ext_key <", value, "extKey");
			return this;
		}

		public Criteria andExtKeyLessThanOrEqualTo(String value) {
			addCriterion("ext_key <=", value, "extKey");
			return this;
		}

		public Criteria andExtKeyLike(String value) {
			addCriterion("ext_key like", value, "extKey");
			return this;
		}

		public Criteria andExtKeyNotLike(String value) {
			addCriterion("ext_key not like", value, "extKey");
			return this;
		}

		public Criteria andExtKeyIn(List values) {
			addCriterion("ext_key in", values, "extKey");
			return this;
		}

		public Criteria andExtKeyNotIn(List values) {
			addCriterion("ext_key not in", values, "extKey");
			return this;
		}

		public Criteria andExtKeyBetween(String value1, String value2) {
			addCriterion("ext_key between", value1, value2, "extKey");
			return this;
		}

		public Criteria andExtKeyNotBetween(String value1, String value2) {
			addCriterion("ext_key not between", value1, value2, "extKey");
			return this;
		}

		public Criteria andExtValueIsNull() {
			addCriterion("ext_value is null");
			return this;
		}

		public Criteria andExtValueIsNotNull() {
			addCriterion("ext_value is not null");
			return this;
		}

		public Criteria andExtValueEqualTo(String value) {
			addCriterion("ext_value =", value, "extValue");
			return this;
		}

		public Criteria andExtValueNotEqualTo(String value) {
			addCriterion("ext_value <>", value, "extValue");
			return this;
		}

		public Criteria andExtValueGreaterThan(String value) {
			addCriterion("ext_value >", value, "extValue");
			return this;
		}

		public Criteria andExtValueGreaterThanOrEqualTo(String value) {
			addCriterion("ext_value >=", value, "extValue");
			return this;
		}

		public Criteria andExtValueLessThan(String value) {
			addCriterion("ext_value <", value, "extValue");
			return this;
		}

		public Criteria andExtValueLessThanOrEqualTo(String value) {
			addCriterion("ext_value <=", value, "extValue");
			return this;
		}

		public Criteria andExtValueLike(String value) {
			addCriterion("ext_value like", value, "extValue");
			return this;
		}

		public Criteria andExtValueNotLike(String value) {
			addCriterion("ext_value not like", value, "extValue");
			return this;
		}

		public Criteria andExtValueIn(List values) {
			addCriterion("ext_value in", values, "extValue");
			return this;
		}

		public Criteria andExtValueNotIn(List values) {
			addCriterion("ext_value not in", values, "extValue");
			return this;
		}

		public Criteria andExtValueBetween(String value1, String value2) {
			addCriterion("ext_value between", value1, value2, "extValue");
			return this;
		}

		public Criteria andExtValueNotBetween(String value1, String value2) {
			addCriterion("ext_value not between", value1, value2, "extValue");
			return this;
		}

		public Criteria andShowNameIsNull() {
			addCriterion("show_name is null");
			return this;
		}

		public Criteria andShowNameIsNotNull() {
			addCriterion("show_name is not null");
			return this;
		}

		public Criteria andShowNameEqualTo(String value) {
			addCriterion("show_name =", value, "showName");
			return this;
		}

		public Criteria andShowNameNotEqualTo(String value) {
			addCriterion("show_name <>", value, "showName");
			return this;
		}

		public Criteria andShowNameGreaterThan(String value) {
			addCriterion("show_name >", value, "showName");
			return this;
		}

		public Criteria andShowNameGreaterThanOrEqualTo(String value) {
			addCriterion("show_name >=", value, "showName");
			return this;
		}

		public Criteria andShowNameLessThan(String value) {
			addCriterion("show_name <", value, "showName");
			return this;
		}

		public Criteria andShowNameLessThanOrEqualTo(String value) {
			addCriterion("show_name <=", value, "showName");
			return this;
		}

		public Criteria andShowNameLike(String value) {
			addCriterion("show_name like", value, "showName");
			return this;
		}

		public Criteria andShowNameNotLike(String value) {
			addCriterion("show_name not like", value, "showName");
			return this;
		}

		public Criteria andShowNameIn(List values) {
			addCriterion("show_name in", values, "showName");
			return this;
		}

		public Criteria andShowNameNotIn(List values) {
			addCriterion("show_name not in", values, "showName");
			return this;
		}

		public Criteria andShowNameBetween(String value1, String value2) {
			addCriterion("show_name between", value1, value2, "showName");
			return this;
		}

		public Criteria andShowNameNotBetween(String value1, String value2) {
			addCriterion("show_name not between", value1, value2, "showName");
			return this;
		}
	}
}