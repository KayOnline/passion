package org.kay.framework.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;

public class SortParamList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<SortParam> sortParams = new ArrayList<SortParam>();

	public SortParamList() {
	}

	public void addParam(SortParam sortParam) {
		if (sortParam != null && !StringUtils.isEmpty(sortParam.getSortName())) {
			sortParams.add(sortParam);
		}
	}
	
	// Overload
	public void addParam(String sortName) {
		this.addParam(sortName, null, null);
	}

	// Overload
	public void addParam(String sortName, String sortType) {
		this.addParam(sortName, sortType, null);
	}

	// Overload
	public void addParam(String sortName, String sortType, String alias) {
		SortParam sortParam = new SortParam(sortName, sortType, alias);
		sortParams.add(sortParam);
	}

	public int size() {
		return this.sortParams.size();
	}

	public boolean containSortParam(String paramName) {
		return sortParams.contains(paramName);
	}
	
	public List<SortParam> getSortParams() {
		return this.sortParams;
	}

}
