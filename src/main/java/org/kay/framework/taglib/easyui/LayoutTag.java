package org.kay.framework.taglib.easyui;

import org.apache.log4j.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LayoutTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(LayoutTag.class);

	private String id = "2";

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.write("<div class=\"easyui-layout\" style=\"width:100%;height:100%\" fit=\"true\">");
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
