package org.kay.framework.taglib.easyui;

import org.apache.log4j.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LayoutAreaTag extends BodyTagSupport {

	private static final Logger logger = Logger.getLogger(LayoutAreaTag.class);

	private static final long serialVersionUID = 1L;

	private String title;
	private String region;
	private String border = "true";
	private String split = "false";
	private String style;
	private String iconCls;
	private String href = "";
	private String collapsible = "true";

	@Override
	public int doStartTag() throws JspException {
		// create data options
		StringBuffer buffer = new StringBuffer(16);
		buffer.append("<div data-options=\"");
		buffer.append("title:'" + this.title + "',");
		buffer.append("region:'" + this.region + "',");
		buffer.append("border:'" + this.border + "',");
		buffer.append("split:'" + this.split + "',");
		buffer.append("iconCls:'" + this.iconCls + "',");
		buffer.append("href:'" + this.href + "',");
		buffer.append("collapsible:'" + this.collapsible + "'\"");
		buffer.append(" style=\"" + this.style + "\">");

		try {
			JspWriter out = this.pageContext.getOut();
			out.write(buffer.toString());
		} catch (Exception e) {
			logger.error(TagConstants.MSG_ERROR, e);
		}

		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doAfterBody() throws JspException {
		try {
			JspWriter out = this.bodyContent.getEnclosingWriter();
			out.print(this.bodyContent.getString());
		} catch (Exception e) {
			logger.error(TagConstants.MSG_ERROR, e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.write("</div>");
		} catch (Exception e) {
			logger.error(TagConstants.MSG_ERROR, e);
		}
		return EVAL_PAGE;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCollapsible() {
		return collapsible;
	}

	public void setCollapsible(String collapsible) {
		this.collapsible = collapsible;
	}

}
