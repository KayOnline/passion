package org.kay.framework.model.easyui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class TreeNode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * EasyUI TreeNode Model
	 * Every node can contains following properties
	 */
	private String id;						// node id, which is important to load remote data.
	private String text;					// node text to show.
	private String state = "open";			// node state, 'open' or 'closed', default is 'open'. 
	private Boolean checked = false;		// Indicate whether the node is checked selected. 
	private Map<String, Object> attributes; // custom attributes can be added to a node.
	private List<TreeNode> children;	// an array nodes defines some children nodes.
	
	// Extension prperties
	private String _parentId;				// parent node id.
	private String iconCls;					// node icon.
	private String url;
	private BigDecimal displayNo;
	

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public BigDecimal getDisplayNo() {
		return displayNo;
	}

	public void setDisplayNo(BigDecimal displayNo) {
		this.displayNo = displayNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
