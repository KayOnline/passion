package org.kay.framework.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;

public class QueryParamList implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<QueryParam> queryParams = new ArrayList<QueryParam>();

	public QueryParamList() {
	}

	public void addParam(String name, Object value) {
		QueryParam queryParam = new QueryParam();
		queryParam.setName(name);
		queryParam.setValue(value);
		this.queryParams.add(queryParam);
	}

	public void addParam(String name, Object value, String relation) {
		QueryParam queryParam = new QueryParam();
		queryParam.setName(name);
		queryParam.setValue(value);
		queryParam.setRelation(relation);
		this.queryParams.add(queryParam);
	}

	public void addParam(QueryParam queryParam) {
		if (queryParam != null && queryParam.getName() != null && queryParam.getValue() != null) {
			this.queryParams.add(queryParam);
		}
	}

	public void addParamList(QueryParamList queryParamList) {
		if (queryParamList != null && queryParamList.getQueryParams().size() > 0) {
			this.queryParams.addAll(queryParamList.getQueryParams());
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getQueryParamValue(String name, Class<T> clazz) {
		if (!StringUtils.isEmpty(name)) {
			for (QueryParam queryParam : this.queryParams) {
				if (name.equals(queryParam.getName())) {
					return (T)queryParam.getValue();
				}
			}
		}
		return null;
	}

	public List<QueryParam> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(List<QueryParam> queryParams) {
		this.queryParams = queryParams;
	}

	public int size() {
		return this.queryParams.size();
	}

	public QueryParam get(int index) {
		return this.queryParams.get(index);
	}

	public QueryParam get(String name) {
		if (!StringUtils.isEmpty(name)) {
			for (QueryParam queryParam : this.queryParams) {
				if (name.equals(queryParam.getName()))
					return queryParam;
			}
		}
		return null;
	}

	public boolean remove(String name) {
		if (!StringUtils.isEmpty(name)) {
			for (QueryParam queryParam : this.queryParams) {
				if (name.equals(queryParam.getName()))
					this.queryParams.remove(name);
				return true;
			}
		}
		return false;
	}

}
