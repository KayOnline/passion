package org.kay.framework.dataset;

import javax.xml.bind.annotation.XmlAttribute;

public class QueryParameter {

	private int sequence;
	private String argumentName;
	private String inOut;
	private String dataType;
	private String javaType;
	private int jdbcType;

	@XmlAttribute
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@XmlAttribute
	public String getArgumentName() {
		return argumentName;
	}

	public void setArgumentName(String argumentName) {
		this.argumentName = argumentName;
	}

	@XmlAttribute
	public String getInOut() {
		return inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	@XmlAttribute
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@XmlAttribute
	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	@XmlAttribute
	public int getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(int jdbcType) {
		this.jdbcType = jdbcType;
	}

}
