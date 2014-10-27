package org.kay.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_EXTEND_PROPERTY")
public class SysExtendProperty implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EXT_CODE")
	private String extCode;
	@Column(name = "EXT_NAME")
	private String extName;
	@Column(name = "EXT_SQL")
	private String extSql;
	@Column(name = "EXT_TYPE")
	private String extType;
	@Column(name = "REF_TABLE")
	private String refTable;

	public SysExtendProperty() {
	}

	public String getExtCode() {
		return extCode;
	}

	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public String getExtSql() {
		return extSql;
	}

	public void setExtSql(String extSql) {
		this.extSql = extSql;
	}

	public String getExtType() {
		return extType;
	}

	public void setExtType(String extType) {
		this.extType = extType;
	}

	public String getRefTable() {
		return refTable;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}

}