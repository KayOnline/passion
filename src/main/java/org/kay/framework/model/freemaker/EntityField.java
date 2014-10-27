package org.kay.framework.model.freemaker;

public class EntityField {

	// 主键标识Y\N
	private String primary = "N";
	// 可否为空
	private String mandatory;
	// 属性名称
	private String fieldName;
	// 属性类别
	private String fieldType;
	// 文本标题
	private String fieldCaption;
	// 库表字段
	private String fieldColumn;
	// 长度
	private String dataLength;
	// 精度
	private String dataPrecision;
	// 小数位
	private String dataScale;
	// 扩展类型 D 下拉 T 下拉树
	private String extAttrType;
	// 扩展属性名
	private String extAttrCode;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldCaption() {
		return fieldCaption;
	}

	public void setFieldCaption(String fieldCaption) {
		this.fieldCaption = fieldCaption;
	}

	public String getFieldColumn() {
		return fieldColumn;
	}

	public void setFieldColumn(String fieldColumn) {
		this.fieldColumn = fieldColumn;
	}

	public String getExtAttrType() {
		return extAttrType;
	}

	public void setExtAttrType(String extAttrType) {
		this.extAttrType = extAttrType;
	}

	public String getExtAttrCode() {
		return extAttrCode;
	}

	public void setExtAttrCode(String extAttrCode) {
		this.extAttrCode = extAttrCode;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getDataPrecision() {
		return dataPrecision;
	}

	public void setDataPrecision(String dataPrecision) {
		this.dataPrecision = dataPrecision;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public String getDataScale() {
		return dataScale;
	}

	public void setDataScale(String dataScale) {
		this.dataScale = dataScale;
	}

}
