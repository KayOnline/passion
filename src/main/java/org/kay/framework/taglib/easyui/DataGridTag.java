package org.kay.framework.taglib.easyui;

import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.kay.entity.SysExtendProperty;
import org.kay.framework.persistence.dao.BaseDao;
import org.kay.framework.persistence.util.BeanLocator;

/*
 $(function() {
 $dg = $('#dg').datagrid({
 url : 'retrieveDataGrid',
 title : '组织机构详细列表',
 iconCls : 'icon-rainbow',
 fit : true,
 idField : 'partyId',
 striped : true,
 fitColumns : false,
 singleSelect : true,
 selectOnCheck : false,
 checkOnSelect : false,
 collapsible : true,
 rownumbers : true,
 pagination : true,
 pageNumber : 1,
 pageSize : 10,
 pageList : [ 10, 20, 30, 40, 50 ],
 // sortName: 'userCode',
 // sortOrder: 'asc',
 toolbar: '#tb',
 onClickCell : beginEdit,
 columns : [ [ 
 {field:'ck',title:'选中',width:'100',halign:'center',checkbox:true,hidden:false},
 {field:'partyId',title:'唯一标识',width:'100',halign:'center',checkbox:false,hidden:false},
 {field:'groupCode',title:'组织机构编号',width:'100',halign:'center',checkbox:false,hidden:false,editor : {	type : 'text'}},
 {field:'groupName',title:'组织机构名称',width:'100',halign:'center',checkbox:false,hidden:false,editor : {	type : 'text'}},
 {field:'groupLevel',title:'组织机构级别',width:'100',halign:'center',checkbox:false,hidden:false,formatter : formatComboByGridEditor,editor: {	type: 'combobox' ,options: { panelHeight: 'auto', valueField: 'value', textField: 'label', data:[  {"value":"001","label":"公司"}, {"value":"002","label":"部门"}, {"value":"003","label":"科室"}] }}},
 {field:'description',title:'备注',width:'100',halign:'center',checkbox:false,hidden:false,editor : {	type : 'text'}},
 ] ]
 });
 });*/

public class DataGridTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(DataGridTag.class);

	private String id;
	private String url = "retrieveDataGrid";
	private String title = "标题";
	private String iconCls = "icon-rainbow";
	private String loadMsg = "程序处理中, 请等待...";
	private boolean fit = true;
	private String idField = "partyId";
	private boolean striped = true;
	private boolean fitColumns = false;
	private boolean singleSelect = true;
	private boolean selectOnCheck = false;
	private boolean checkOnSelect = false;
	private boolean collapsible = true;
	private boolean rownumbers = true;
	private boolean ctrlSelect = true;
	private boolean autoRowHeight = false;
	private boolean showHeader = true;
	private boolean showFooter = false;
	private boolean pagination = true;
	private String pagePosition = "bottom";
	private int pageNumber = 1;
	private int pageSize = 10;
	private String pageList = "[ 10, 20, 30, 40, 50 ]";
	private String toolbar = "#tb";
	private String onClickCell;
	private List<DataGridColumnTag> columns = new ArrayList<DataGridColumnTag>();

	// FIXME
	// private String sortName;
	// private String sortOrder;

	@Override
	public int doStartTag() throws JspException {
		this.columns.clear();
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div id=\"" + this.id + "\"></div>");

		// Set formatter function for combobox
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("function formatComboByGridEditor(value, row, rowIndex) {");
		buffer.append("var retLabel = value;");
		buffer.append("if(this.editor && this.editor.options) {");
		buffer.append("var data = this.editor.options.data;");
		buffer.append("$(data).each(function(index, item) {");
		buffer.append("if (item.value == value) {retLabel = item.label;}");
		buffer.append("});}");
		buffer.append("return retLabel;");
		buffer.append("}");
		buffer.append("</script>");

		// Construct datagrid object
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("$(function() {");
		buffer.append(this.id + " = $('#" + this.id + "').datagrid({");
		buffer.append("url : '" + this.url + "',");
		buffer.append("title : '" + this.title + "',");
		buffer.append("iconCls : '" + this.iconCls + "',");
		buffer.append("fit : true,");
		buffer.append("striped : true,");
		buffer.append("fitColumns : false,");
		buffer.append("singleSelect : true,");
		buffer.append("selectOnCheck : false,");
		buffer.append("checkOnSelect : false,");
		buffer.append("collapsible : true,");
		buffer.append("rownumbers : true,");
		buffer.append("ctrlSelect : true,");
		buffer.append("pagination : true,");
		buffer.append("pageNumber : 1,");
		buffer.append("pageSize : 10,");
		buffer.append("pageList : [ 10, 20, 30, 40, 50 ],");
		buffer.append("toolbar: '#" + this.toolbar + "',");
		buffer.append("onClickCell : beginEdit,");
		buffer.append("idField : '" + this.idField + "',");
		buffer.append("columns : [ [");
		buffer.append("{field:'checked',checkbox:true}");
		for (DataGridColumnTag column : columns) {
			buffer.append(",{");
			buffer.append("field:'" + column.getField() + "'");
			buffer.append(",title:'" + column.getTitle() + "'");
			buffer.append(",width:'" + column.getWidth() + "'");
			buffer.append(",align:'" + column.getAlign() + "'");
			buffer.append(",halign:'" + column.getHalign() + "'");
			buffer.append(",checkbox:" + column.isCheckbox());
			buffer.append(",hidden:" + column.isHidden());

			String editorType = column.getEditorType();
			if ("combobox".equals(editorType)) {
				appendComboboxConfig(buffer, column);
			} else if ("text".equals(column.getEditorType())) {
				appendTextConfig(buffer, column);
			} else if ("datebox".equals(editorType)) {
				appendDateboxConfig(buffer, column);
			}
			buffer.append("}");
		}
		buffer.append("] ]");
		buffer.append("});");
		buffer.append("});");
		buffer.append("</script>");

		// Binding datagrid's endEdit event to document
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("$(function() {");
		buffer.append("$(document).click(function() {");
		buffer.append("var rows = $('#" + this.id + "').datagrid('getRows');");
		buffer.append("$.each(rows, function(index, item) {");
		buffer.append("$('#" + this.id + "').datagrid('endEdit', parseInt(index, 10));");
		buffer.append("});");
		buffer.append("});");
		buffer.append("});");
		buffer.append("</script>");

		try {
			JspWriter out = this.pageContext.getOut();
			out.print(buffer);
		} catch (Exception e) {
			logger.error(TagConstants.MSG_ERROR, e);
		}
		return EVAL_PAGE;
	}

	private void appendDateboxConfig(StringBuffer buffer, DataGridColumnTag column) {
		buffer.append(",editor : {");
		buffer.append("	type : '" + column.getEditorType() + "'");
		buffer.append("}");
	}

	private void appendTextConfig(StringBuffer buffer, DataGridColumnTag column) {
		buffer.append(",editor : {");
		buffer.append("	type : '" + column.getEditorType() + "'");
		buffer.append("}");
	}

	private void appendComboboxConfig(StringBuffer buffer, DataGridColumnTag column) {
		// Bind to formatter function
		buffer.append(",formatter : formatComboByGridEditor");
		buffer.append(",editor: {");
		buffer.append("	type: '" + column.getEditorType() + "'");
		buffer.append(" ,options: {");
		buffer.append(" panelHeight: 'auto',");
		buffer.append(" editable: false,");
		buffer.append(" valueField: 'value',");
		buffer.append(" textField: 'label',");
		buffer.append(" data: " + this.getDropData(column.getEditorOptionsDropCode()) + ",");
		buffer.append(" }");
		buffer.append("}");
	}

	// [{"value":"01","label":"YES"},{"value":"02","label":"NO"}]
	private String getDropData(String editorOptionsDropCode) {

		// Retrieve by DropCode
		BaseDao baseDao = BeanLocator.getBean("baseDao", BaseDao.class);
		SysExtendProperty sysExtendProperty = baseDao.find(SysExtendProperty.class, editorOptionsDropCode);
		List<Object> records = baseDao.findByNativeQuery(sysExtendProperty.getExtSql());

		// Construct DropData String
		StringBuffer dropData = new StringBuffer();
		dropData.append("[");
		for (int i = 0; i < records.size(); i++) {
			Object[] cols = (Object[]) records.get(i);
			dropData.append("{\"value\":\"" + cols[0] + "\",\"label\":\"" + cols[1] + "\"}");
			if (i + 1 < records.size()) {
				dropData.append(",");
			}
		}
		dropData.append("]");

		return dropData.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getLoadMsg() {
		return loadMsg;
	}

	public void setLoadMsg(String loadMsg) {
		this.loadMsg = loadMsg;
	}

	public boolean isFit() {
		return fit;
	}

	public void setFit(boolean fit) {
		this.fit = fit;
	}

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public boolean isStriped() {
		return striped;
	}

	public void setStriped(boolean striped) {
		this.striped = striped;
	}

	public boolean isFitColumns() {
		return fitColumns;
	}

	public void setFitColumns(boolean fitColumns) {
		this.fitColumns = fitColumns;
	}

	public boolean isSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	public boolean isSelectOnCheck() {
		return selectOnCheck;
	}

	public void setSelectOnCheck(boolean selectOnCheck) {
		this.selectOnCheck = selectOnCheck;
	}

	public boolean isCheckOnSelect() {
		return checkOnSelect;
	}

	public void setCheckOnSelect(boolean checkOnSelect) {
		this.checkOnSelect = checkOnSelect;
	}

	public boolean isCollapsible() {
		return collapsible;
	}

	public void setCollapsible(boolean collapsible) {
		this.collapsible = collapsible;
	}

	public boolean isRownumbers() {
		return rownumbers;
	}

	public void setRownumbers(boolean rownumbers) {
		this.rownumbers = rownumbers;
	}

	public boolean isCtrlSelect() {
		return ctrlSelect;
	}

	public void setCtrlSelect(boolean ctrlSelect) {
		this.ctrlSelect = ctrlSelect;
	}

	public boolean isAutoRowHeight() {
		return autoRowHeight;
	}

	public void setAutoRowHeight(boolean autoRowHeight) {
		this.autoRowHeight = autoRowHeight;
	}

	public boolean isShowHeader() {
		return showHeader;
	}

	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}

	public boolean isShowFooter() {
		return showFooter;
	}

	public void setShowFooter(boolean showFooter) {
		this.showFooter = showFooter;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public String getPagePosition() {
		return pagePosition;
	}

	public void setPagePosition(String pagePosition) {
		this.pagePosition = pagePosition;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageList() {
		return pageList;
	}

	public void setPageList(String pageList) {
		this.pageList = pageList;
	}

	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	public String getOnClickCell() {
		return onClickCell;
	}

	public void setOnClickCell(String onClickCell) {
		this.onClickCell = onClickCell;
	}

	public List<DataGridColumnTag> getColumns() {
		return columns;
	}

	public void setColumns(List<DataGridColumnTag> columns) {
		this.columns = columns;
	}

}
