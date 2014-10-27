package org.kay.framework.model.easyui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.kay.framework.persistence.model.PageInfo;
import org.kay.framework.persistence.model.QueryParamList;
import org.kay.framework.persistence.model.SortParamList;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataWrap<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	// Store view object list
	private List<T> dataList;
	private PageInfo pageInfo = new PageInfo();
	private SortParamList sortParamList;
	private QueryParamList queryParamList;

	@JsonIgnore
	private Class<T> elementClass;
	@JsonIgnore
	private List<T> updatedList = new ArrayList<T>();
	@JsonIgnore
	private List<T> insertedList = new ArrayList<T>();
	@JsonIgnore
	private List<T> deletedList = new ArrayList<T>();

	public DataWrap() {
		queryParamList = new QueryParamList();
	}

	public DataGrid<T> createDataGrid() {
		DataGrid<T> dataGrid = new DataGrid<T>(this.getPageInfo().getAllRowNum(), this.dataList);
		return dataGrid;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public SortParamList getSortParamList() {
		return sortParamList;
	}

	public void setSortParamList(SortParamList sortParamList) {
		this.sortParamList = sortParamList;
	}

	public QueryParamList getQueryParamList() {
		return queryParamList;
	}

	public void setQueryParamList(QueryParamList queryParamList) {
		this.queryParamList = queryParamList;
	}

	public Class<T> getElementClass() {
		return elementClass;
	}

	public void setElementClass(Class<T> elementClass) {
		this.elementClass = elementClass;
	}

	public List<T> getUpdatedList() {
		return updatedList;
	}

	public void setUpdatedList(List<T> updatedList) {
		this.updatedList = updatedList;
	}

	public List<T> getInsertedList() {
		return insertedList;
	}

	public void setInsertedList(List<T> insertedList) {
		this.insertedList = insertedList;
	}

	public List<T> getDeletedList() {
		return deletedList;
	}

	public void setDeletedList(List<T> deletedList) {
		this.deletedList = deletedList;
	}

}
