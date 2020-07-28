package com.jfast.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysUserExample {

	protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public SysUserExample() {
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

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andUidIsNull() {
			addCriterion("uid is null");
			return (Criteria) this;
		}

		public Criteria andUidIsNotNull() {
			addCriterion("uid is not null");
			return (Criteria) this;
		}

		public Criteria andUidEqualTo(String value) {
			addCriterion("uid =", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotEqualTo(String value) {
			addCriterion("uid <>", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidGreaterThan(String value) {
			addCriterion("uid >", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidGreaterThanOrEqualTo(String value) {
			addCriterion("uid >=", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLessThan(String value) {
			addCriterion("uid <", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLessThanOrEqualTo(String value) {
			addCriterion("uid <=", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLike(String value) {
			addCriterion("uid like", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotLike(String value) {
			addCriterion("uid not like", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidIn(List<String> values) {
			addCriterion("uid in", values, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotIn(List<String> values) {
			addCriterion("uid not in", values, "uid");
			return (Criteria) this;
		}

		public Criteria andUidBetween(String value1, String value2) {
			addCriterion("uid between", value1, value2, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotBetween(String value1, String value2) {
			addCriterion("uid not between", value1, value2, "uid");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Byte value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Byte value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Byte value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Byte value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Byte value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Byte> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Byte> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Byte value1, Byte value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Byte value1, Byte value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andUserNameIsNull() {
			addCriterion("user_name is null");
			return (Criteria) this;
		}

		public Criteria andUserNameIsNotNull() {
			addCriterion("user_name is not null");
			return (Criteria) this;
		}

		public Criteria andUserNameEqualTo(String value) {
			addCriterion("user_name =", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameNotEqualTo(String value) {
			addCriterion("user_name <>", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameGreaterThan(String value) {
			addCriterion("user_name >", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameGreaterThanOrEqualTo(String value) {
			addCriterion("user_name >=", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameLessThan(String value) {
			addCriterion("user_name <", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameLessThanOrEqualTo(String value) {
			addCriterion("user_name <=", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameLike(String value) {
			addCriterion("user_name like", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameNotLike(String value) {
			addCriterion("user_name not like", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameIn(List<String> values) {
			addCriterion("user_name in", values, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameNotIn(List<String> values) {
			addCriterion("user_name not in", values, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameBetween(String value1, String value2) {
			addCriterion("user_name between", value1, value2, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameNotBetween(String value1, String value2) {
			addCriterion("user_name not between", value1, value2, "userName");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNull() {
			addCriterion("password is null");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNotNull() {
			addCriterion("password is not null");
			return (Criteria) this;
		}

		public Criteria andPasswordEqualTo(String value) {
			addCriterion("password =", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotEqualTo(String value) {
			addCriterion("password <>", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThan(String value) {
			addCriterion("password >", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("password >=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThan(String value) {
			addCriterion("password <", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThanOrEqualTo(String value) {
			addCriterion("password <=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLike(String value) {
			addCriterion("password like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotLike(String value) {
			addCriterion("password not like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordIn(List<String> values) {
			addCriterion("password in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotIn(List<String> values) {
			addCriterion("password not in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordBetween(String value1, String value2) {
			addCriterion("password between", value1, value2, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotBetween(String value1, String value2) {
			addCriterion("password not between", value1, value2, "password");
			return (Criteria) this;
		}

		public Criteria andPhoneIsNull() {
			addCriterion("phone is null");
			return (Criteria) this;
		}

		public Criteria andPhoneIsNotNull() {
			addCriterion("phone is not null");
			return (Criteria) this;
		}

		public Criteria andPhoneEqualTo(String value) {
			addCriterion("phone =", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotEqualTo(String value) {
			addCriterion("phone <>", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThan(String value) {
			addCriterion("phone >", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("phone >=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThan(String value) {
			addCriterion("phone <", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThanOrEqualTo(String value) {
			addCriterion("phone <=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLike(String value) {
			addCriterion("phone like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotLike(String value) {
			addCriterion("phone not like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneIn(List<String> values) {
			addCriterion("phone in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotIn(List<String> values) {
			addCriterion("phone not in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneBetween(String value1, String value2) {
			addCriterion("phone between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotBetween(String value1, String value2) {
			addCriterion("phone not between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andPositionIsNull() {
			addCriterion("position is null");
			return (Criteria) this;
		}

		public Criteria andPositionIsNotNull() {
			addCriterion("position is not null");
			return (Criteria) this;
		}

		public Criteria andPositionEqualTo(String value) {
			addCriterion("position =", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionNotEqualTo(String value) {
			addCriterion("position <>", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionGreaterThan(String value) {
			addCriterion("position >", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionGreaterThanOrEqualTo(String value) {
			addCriterion("position >=", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionLessThan(String value) {
			addCriterion("position <", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionLessThanOrEqualTo(String value) {
			addCriterion("position <=", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionLike(String value) {
			addCriterion("position like", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionNotLike(String value) {
			addCriterion("position not like", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionIn(List<String> values) {
			addCriterion("position in", values, "position");
			return (Criteria) this;
		}

		public Criteria andPositionNotIn(List<String> values) {
			addCriterion("position not in", values, "position");
			return (Criteria) this;
		}

		public Criteria andPositionBetween(String value1, String value2) {
			addCriterion("position between", value1, value2, "position");
			return (Criteria) this;
		}

		public Criteria andPositionNotBetween(String value1, String value2) {
			addCriterion("position not between", value1, value2, "position");
			return (Criteria) this;
		}

		public Criteria andDepartmentIsNull() {
			addCriterion("department is null");
			return (Criteria) this;
		}

		public Criteria andDepartmentIsNotNull() {
			addCriterion("department is not null");
			return (Criteria) this;
		}

		public Criteria andDepartmentEqualTo(String value) {
			addCriterion("department =", value, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentNotEqualTo(String value) {
			addCriterion("department <>", value, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentGreaterThan(String value) {
			addCriterion("department >", value, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
			addCriterion("department >=", value, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentLessThan(String value) {
			addCriterion("department <", value, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentLessThanOrEqualTo(String value) {
			addCriterion("department <=", value, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentLike(String value) {
			addCriterion("department like", value, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentNotLike(String value) {
			addCriterion("department not like", value, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentIn(List<String> values) {
			addCriterion("department in", values, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentNotIn(List<String> values) {
			addCriterion("department not in", values, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentBetween(String value1, String value2) {
			addCriterion("department between", value1, value2, "department");
			return (Criteria) this;
		}

		public Criteria andDepartmentNotBetween(String value1, String value2) {
			addCriterion("department not between", value1, value2, "department");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(String value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(String value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(String value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(String value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(String value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(String value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLike(String value) {
			addCriterion("status like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotLike(String value) {
			addCriterion("status not like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<String> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<String> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(String value1, String value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(String value1, String value2) {
			addCriterion("status not between", value1, value2, "status");
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

		public Criteria andCreateDateIsNull() {
			addCriterion("create_date is null");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNotNull() {
			addCriterion("create_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreateDateEqualTo(Date value) {
			addCriterion("create_date =", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotEqualTo(Date value) {
			addCriterion("create_date <>", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThan(Date value) {
			addCriterion("create_date >", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("create_date >=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThan(Date value) {
			addCriterion("create_date <", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThanOrEqualTo(Date value) {
			addCriterion("create_date <=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateIn(List<Date> values) {
			addCriterion("create_date in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotIn(List<Date> values) {
			addCriterion("create_date not in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateBetween(Date value1, Date value2) {
			addCriterion("create_date between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotBetween(Date value1, Date value2) {
			addCriterion("create_date not between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIsNull() {
			addCriterion("update_date is null");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIsNotNull() {
			addCriterion("update_date is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateDateEqualTo(Date value) {
			addCriterion("update_date =", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotEqualTo(Date value) {
			addCriterion("update_date <>", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateGreaterThan(Date value) {
			addCriterion("update_date >", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("update_date >=", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateLessThan(Date value) {
			addCriterion("update_date <", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
			addCriterion("update_date <=", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIn(List<Date> values) {
			addCriterion("update_date in", values, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotIn(List<Date> values) {
			addCriterion("update_date not in", values, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateBetween(Date value1, Date value2) {
			addCriterion("update_date between", value1, value2, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
			addCriterion("update_date not between", value1, value2, "updateDate");
			return (Criteria) this;
		}

		public Criteria andLockedIsNull() {
			addCriterion("locked is null");
			return (Criteria) this;
		}

		public Criteria andLockedIsNotNull() {
			addCriterion("locked is not null");
			return (Criteria) this;
		}

		public Criteria andLockedEqualTo(Integer value) {
			addCriterion("locked =", value, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedNotEqualTo(Integer value) {
			addCriterion("locked <>", value, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedGreaterThan(Integer value) {
			addCriterion("locked >", value, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedGreaterThanOrEqualTo(Integer value) {
			addCriterion("locked >=", value, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedLessThan(Integer value) {
			addCriterion("locked <", value, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedLessThanOrEqualTo(Integer value) {
			addCriterion("locked <=", value, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedIn(List<Integer> values) {
			addCriterion("locked in", values, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedNotIn(List<Integer> values) {
			addCriterion("locked not in", values, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedBetween(Integer value1, Integer value2) {
			addCriterion("locked between", value1, value2, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedNotBetween(Integer value1, Integer value2) {
			addCriterion("locked not between", value1, value2, "locked");
			return (Criteria) this;
		}

		public Criteria andLockedTimeIsNull() {
			addCriterion("locked_time is null");
			return (Criteria) this;
		}

		public Criteria andLockedTimeIsNotNull() {
			addCriterion("locked_time is not null");
			return (Criteria) this;
		}

		public Criteria andLockedTimeEqualTo(Date value) {
			addCriterion("locked_time =", value, "lockedTime");
			return (Criteria) this;
		}

		public Criteria andLockedTimeNotEqualTo(Date value) {
			addCriterion("locked_time <>", value, "lockedTime");
			return (Criteria) this;
		}

		public Criteria andLockedTimeGreaterThan(Date value) {
			addCriterion("locked_time >", value, "lockedTime");
			return (Criteria) this;
		}

		public Criteria andLockedTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("locked_time >=", value, "lockedTime");
			return (Criteria) this;
		}

		public Criteria andLockedTimeLessThan(Date value) {
			addCriterion("locked_time <", value, "lockedTime");
			return (Criteria) this;
		}

		public Criteria andLockedTimeLessThanOrEqualTo(Date value) {
			addCriterion("locked_time <=", value, "lockedTime");
			return (Criteria) this;
		}

		public Criteria andLockedTimeIn(List<Date> values) {
			addCriterion("locked_time in", values, "lockedTime");
			return (Criteria) this;
		}

		public Criteria andLockedTimeNotIn(List<Date> values) {
			addCriterion("locked_time not in", values, "lockedTime");
			return (Criteria) this;
		}

		public Criteria andLockedTimeBetween(Date value1, Date value2) {
			addCriterion("locked_time between", value1, value2, "lockedTime");
			return (Criteria) this;
		}

		public Criteria andLockedTimeNotBetween(Date value1, Date value2) {
			addCriterion("locked_time not between", value1, value2, "lockedTime");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {
		protected Criteria() {
			super();
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
	
}