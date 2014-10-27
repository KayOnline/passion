package org.kay.framework.persistence.model;

import java.io.Serializable;

public class QueryParam implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String RELATION_EQUAL = "=";
	public static final String RELATION_GT = ">";
	public static final String RELATION_LT = "<";
	public static final String RELATION_GE = ">=";
	public static final String RELATION_LE = "<=";
	public static final String RELATION_NOTEQUAL = "<>";
	public static final String RELATION_LIKE = "LIKE";
	public static final String RELATION_ISNULL = "IS NULL";
	public static final String RELATION_NOTNULL = "IS NOT NULL";

	private String name;
	private Object value;
	private String relation = RELATION_EQUAL;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
