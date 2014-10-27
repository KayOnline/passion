package ${entity.entityPackage};

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import org.kay.framework.annotation.MetaData;
import org.kay.framework.persistence.entity.BaseEntity;

@Entity
@Table(name = "${entity.entityTable?upper_case}", schema = "")
public class ${entity.entityName} extends BaseEntity {

	private static final long serialVersionUID = 1L;

	<#list entity.entityFields as field>
	<#if field.primary == 'Y'>@Id</#if>
	@Column(name = "${field.fieldColumn?upper_case}", nullable = <#if field.mandatory == 'Y'>true<#else>false</#if><#if field.dataScale?number > 0>, precision = ${field.dataPrecision}, scale = ${field.dataScale}</#if>)
	@MetaData(caption="${field.fieldCaption}", extAttrType="", extAttrCode="")
	private ${field.fieldType} ${field.fieldName};
	
	</#list>
	
	<#list entity.entityFields as field>
	public ${field.fieldType} get${field.fieldName?cap_first}() {
		return this.${field.fieldName};
	}
	
	public void set${field.fieldName?cap_first}(${field.fieldType} ${field.fieldName}) {
		this.${field.fieldName} = ${field.fieldName};
	}
	
	</#list>

}