package org.kay.framework.persistence.model;

import java.io.Serializable;

public class SortParam implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SORT_TYPE_ASC = "ASC";
	public static final String SORT_TYPE_DES = "DESC";

	private String sortName;
	private String sortType = SortParam.SORT_TYPE_ASC;
	private String alias;

	public SortParam() {
	}

	public SortParam(String sortName) {
		this.sortName = sortName;
	}

	public SortParam(String sortName, String sortType) {
		this.sortName = sortName;
		this.sortType = sortType;
	}

	public SortParam(String sortName, String sortType, String alias) {
		this.sortName = sortName;
		this.sortType = sortType;
		this.alias = alias;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
