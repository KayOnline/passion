package org.kay.framework.dataset;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class DBQuery implements Cloneable {

	// FORM PART
	// FIXME 目前只支持存储过程，不支持查询语句
	private String queryName;// = "HS_P_USER_INFO";
	private String queryAuthor;// = "KAY";
	private String queryDescription;// = "检索基本信息";
	private String queryObject;// = "hs_p_user_info";
	private String queryDbType;// = DataSetConstant.DBTYPE_ORALCE;
	private String queryType;// = DataSetConstant.PROC_DATASET;
	private String prepareQuery;// = "{ call HS_P_USER_INFO( ? , ? , ? , ? ) }";
	private List<QueryParameter> queryParameterList;

	// private DBType query
	// private Class beanClass;
	// private boolean keyData;
	// private boolean dialectPaging;
	// private String query = "HS_P_USER_INFO";
	// private String tables[];
	// private Column columns[];
	// private QueryParameter params[];
	// private String paramSequence[];
	// private String updatableTable;
	// private boolean updatable;
	// private int rsParamIndex;
	// private int rsParamType;
	// private String rsParamName;
	@XmlAttribute
	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	@XmlAttribute
	public String getQueryAuthor() {
		return queryAuthor;
	}

	public void setQueryAuthor(String queryAuthor) {
		this.queryAuthor = queryAuthor;
	}

	@XmlAttribute
	public String getQueryDescription() {
		return queryDescription;
	}

	public void setQueryDescription(String queryDescription) {
		this.queryDescription = queryDescription;
	}

	@XmlAttribute
	public String getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(String queryObject) {
		this.queryObject = queryObject;
	}

	@XmlAttribute
	public String getQueryDbType() {
		return queryDbType;
	}

	public void setQueryDbType(String queryDbType) {
		this.queryDbType = queryDbType;
	}

	@XmlAttribute
	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	@XmlAttribute
	public String getPrepareQuery() {
		return prepareQuery;
	}

	public void setPrepareQuery(String prepareQuery) {
		this.prepareQuery = prepareQuery;
	}

	@XmlElement(name = "queryParameter")
	public List<QueryParameter> getQueryParameterList() {
		return queryParameterList;
	}

	public void setQueryParameterList(List<QueryParameter> queryParameterList) {
		this.queryParameterList = queryParameterList;
	}

}
