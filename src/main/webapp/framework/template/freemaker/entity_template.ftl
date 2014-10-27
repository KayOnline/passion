package ${bussiPackage}.entity.${entityPackage};

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

/**
 * @Title: Entity
 * @Description: ${ftl_description}
 * @author 
 * @date ${ftl_create_time}
 *
 */
@Entity
@Table(name = "${tableName}", schema = "")
@SuppressWarnings("serial")
public class ${entityName}Entity implements java.io.Serializable {
	<#list originalColumns as po>
	/**${po.filedComment}*/
	private ${po.fieldType} ${po.fieldName};
	</#list>
	
	<#list originalColumns as po>
	/**
	 * 方法: 取得${po.filedComment}
	 * @return: ${po.fieldType} ${po.filedComment}
	 */
	<#if po.fieldName == jeecg_table_id>
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "${entityName}Gen")
    @TableGenerator(name = "${entityName}Gen",
        table = "tid",
        pkColumnName = "key_name",
        valueColumnName = "value",
        pkColumnValue = "${entityName?upper_case}_ID",
        allocationSize = 1
    )
	</#if>
	@Column(name = "${po.fieldDbName}", nullable = <#if po.nullable == 'Y'>true<#else>false</#if><#if po.precision != ''>, precision = ${po.precision}</#if><#if po.scale != ''>, scale = ${po.scale}</#if><#if po.charmaxLength != ''&& po.charmaxLength?length lte 8 >, length = ${po.charmaxLength}</#if>)
	public ${po.fieldType} get${po.fieldName?cap_first}() {
		return this.${po.fieldName};
	}

	/**
	 * 方法: 设置${po.filedComment}
	 * @param: ${po.fieldType} ${po.filedComment}
	 */
	public void set${po.fieldName?cap_first}(${po.fieldType} ${po.fieldName}) {
		this.${po.fieldName} = ${po.fieldName};
	}
	
	</#list>
}
