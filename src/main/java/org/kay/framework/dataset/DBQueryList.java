package org.kay.framework.dataset;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DBQueryList")
public class DBQueryList {

	private List<DBQuery> dBQuerys = new ArrayList<DBQuery>();

	@XmlElement(name = "DBQuery")
	public List<DBQuery> getdBQuerys() {
		return dBQuerys;
	}

	public void setdBQuerys(List<DBQuery> dBQuerys) {
		this.dBQuerys = dBQuerys;
	}
	
	public DBQueryList appendDBQuery(DBQuery dBQuery) {
		this.dBQuerys.add(dBQuery);
		return this;
	}
	
	
}
