package com.jfast.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysRoleMenuExample {
    protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public SysRoleMenuExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andRoleIdIsNull() {
			addCriterion("role_id is null");
			return (Criteria) this;
		}

		public Criteria andRoleIdIsNotNull() {
			addCriterion("role_id is not null");
			return (Criteria) this;
		}

		public Criteria andRoleIdEqualTo(Integer value) {
			addCriterion("role_id =", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotEqualTo(Integer value) {
			addCriterion("role_id <>", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdGreaterThan(Integer value) {
			addCriterion("role_id >", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("role_id >=", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdLessThan(Integer value) {
			addCriterion("role_id <", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
			addCriterion("role_id <=", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdIn(List<Integer> values) {
			addCriterion("role_id in", values, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotIn(List<Integer> values) {
			addCriterion("role_id not in", values, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdBetween(Integer value1, Integer value2) {
			addCriterion("role_id between", value1, value2, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
			addCriterion("role_id not between", value1, value2, "roleId");
			return (Criteria) this;
		}

		public Criteria andMenuCodeIsNull() {
			addCriterion("menu_code is null");
			return (Criteria) this;
		}

		public Criteria andMenuCodeIsNotNull() {
			addCriterion("menu_code is not null");
			return (Criteria) this;
		}

		public Criteria andMenuCodeEqualTo(String value) {
			addCriterion("menu_code =", value, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeNotEqualTo(String value) {
			addCriterion("menu_code <>", value, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeGreaterThan(String value) {
			addCriterion("menu_code >", value, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeGreaterThanOrEqualTo(String value) {
			addCriterion("menu_code >=", value, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeLessThan(String value) {
			addCriterion("menu_code <", value, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeLessThanOrEqualTo(String value) {
			addCriterion("menu_code <=", value, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeLike(String value) {
			addCriterion("menu_code like", value, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeNotLike(String value) {
			addCriterion("menu_code not like", value, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeIn(List<String> values) {
			addCriterion("menu_code in", values, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeNotIn(List<String> values) {
			addCriterion("menu_code not in", values, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeBetween(String value1, String value2) {
			addCriterion("menu_code between", value1, value2, "menuCode");
			return (Criteria) this;
		}

		public Criteria andMenuCodeNotBetween(String value1, String value2) {
			addCriterion("menu_code not between", value1, value2, "menuCode");
			return (Criteria) this;
		}

		public Criteria andPermissionsIsNull() {
			addCriterion("permissions is null");
			return (Criteria) this;
		}

		public Criteria andPermissionsIsNotNull() {
			addCriterion("permissions is not null");
			return (Criteria) this;
		}

		public Criteria andPermissionsEqualTo(String value) {
			addCriterion("permissions =", value, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsNotEqualTo(String value) {
			addCriterion("permissions <>", value, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsGreaterThan(String value) {
			addCriterion("permissions >", value, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsGreaterThanOrEqualTo(String value) {
			addCriterion("permissions >=", value, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsLessThan(String value) {
			addCriterion("permissions <", value, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsLessThanOrEqualTo(String value) {
			addCriterion("permissions <=", value, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsLike(String value) {
			addCriterion("permissions like", value, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsNotLike(String value) {
			addCriterion("permissions not like", value, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsIn(List<String> values) {
			addCriterion("permissions in", values, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsNotIn(List<String> values) {
			addCriterion("permissions not in", values, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsBetween(String value1, String value2) {
			addCriterion("permissions between", value1, value2, "permissions");
			return (Criteria) this;
		}

		public Criteria andPermissionsNotBetween(String value1, String value2) {
			addCriterion("permissions not between", value1, value2, "permissions");
			return (Criteria) this;
		}

		public Criteria andOperatorIsNull() {
			addCriterion("operator is null");
			return (Criteria) this;
		}

		public Criteria andOperatorIsNotNull() {
			addCriterion("operator is not null");
			return (Criteria) this;
		}

		public Criteria andOperatorEqualTo(String value) {
			addCriterion("operator =", value, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorNotEqualTo(String value) {
			addCriterion("operator <>", value, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorGreaterThan(String value) {
			addCriterion("operator >", value, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorGreaterThanOrEqualTo(String value) {
			addCriterion("operator >=", value, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorLessThan(String value) {
			addCriterion("operator <", value, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorLessThanOrEqualTo(String value) {
			addCriterion("operator <=", value, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorLike(String value) {
			addCriterion("operator like", value, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorNotLike(String value) {
			addCriterion("operator not like", value, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorIn(List<String> values) {
			addCriterion("operator in", values, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorNotIn(List<String> values) {
			addCriterion("operator not in", values, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorBetween(String value1, String value2) {
			addCriterion("operator between", value1, value2, "operator");
			return (Criteria) this;
		}

		public Criteria andOperatorNotBetween(String value1, String value2) {
			addCriterion("operator not between", value1, value2, "operator");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("create_time is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("create_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(Date value) {
			addCriterion("create_time =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(Date value) {
			addCriterion("create_time <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(Date value) {
			addCriterion("create_time >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("create_time >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(Date value) {
			addCriterion("create_time <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
			addCriterion("create_time <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<Date> values) {
			addCriterion("create_time in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<Date> values) {
			addCriterion("create_time not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(Date value1, Date value2) {
			addCriterion("create_time between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
			addCriterion("create_time not between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNull() {
			addCriterion("update_time is null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNotNull() {
			addCriterion("update_time is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeEqualTo(Date value) {
			addCriterion("update_time =", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotEqualTo(Date value) {
			addCriterion("update_time <>", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThan(Date value) {
			addCriterion("update_time >", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("update_time >=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThan(Date value) {
			addCriterion("update_time <", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
			addCriterion("update_time <=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIn(List<Date> values) {
			addCriterion("update_time in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotIn(List<Date> values) {
			addCriterion("update_time not in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeBetween(Date value1, Date value2) {
			addCriterion("update_time between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
			addCriterion("update_time not between", value1, value2, "updateTime");
			return (Criteria) this;
		}
	}

	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_role_menu
     *
     * @mbg.generated do_not_delete_during_merge Tue Oct 29 16:16:53 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}