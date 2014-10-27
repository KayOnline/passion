package org.kay.framework.taglib.easyui;

import org.apache.log4j.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

/*
 {
 field : 'groupLevel',
 title : '组织机构级别',
 width : 100,
 halign : 'center',
 align : 'center',
 formatter: formatComboByGridEditor,
 editor : {
 type : 'ajaxcombobox',
 options : {
 dropCode : 'GROUP_LEVEL',
 panelHeight : 'auto',
 "valueField":"value" ,
 "textField":"label",
 "data":[{"value":"01","label":"公司"},{"value":"02","label":"部门"}]
 }
 }
 }
 */
public class DataGridColumnTag extends BodyTagSupport implements Cloneable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(DataGridColumnTag.class);

	private String title;
	private String field;
	private int width = 100;
	private int rowspan = 1;
	private int colspan = 1;
	private String align = "center";
	private String halign = "center";
	private boolean sortable = true;
	private String order = "asc";
	// FIXME have no ideal how to deal with them
	// private boolean resizable;
	// private boolean fixed;
	private boolean hidden = false;
	private boolean checkbox = false;
	private String editorType = "";
	private String editorOptionsDropCode = "";

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			Tag parent = findAncestorWithClass(this, DataGridTag.class);
			if (parent != null && parent instanceof DataGridTag) {
				DataGridTag dataGridTag = (DataGridTag) parent;
				dataGridTag.getColumns().add((DataGridColumnTag) this.clone());
			}
		} catch (Exception e) {
			logger.error(TagConstants.MSG_ERROR, e);
		}
		return EVAL_PAGE;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getHalign() {
		return halign;
	}

	public void setHalign(String halign) {
		this.halign = halign;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}

	public String getEditorOptionsDropCode() {
		return editorOptionsDropCode;
	}

	public void setEditorOptionsDropCode(String editorOptionsDropCode) {
		this.editorOptionsDropCode = editorOptionsDropCode;
	}

}
