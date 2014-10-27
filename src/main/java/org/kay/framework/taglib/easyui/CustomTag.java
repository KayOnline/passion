package org.kay.framework.taglib.easyui;

import java.io.IOException;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

public class CustomTag implements SimpleTag {

	private JspTag parent;
	private JspContext jspContext;
	private JspFragment jspBody;

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public void doTag() throws JspException, IOException {

		PageContext pageContext = (PageContext) this.getJspContext();
		JspWriter out = pageContext.getOut();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div style='color:red'>" + this.key + "</div>");
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("console.info('ok');");
		buffer.append("</script>");
		out.write(buffer.toString());
		
		//输出标签体内容
		this.jspBody.invoke(out);
	}

	@Override
	public void setParent(JspTag parent) {
		this.parent = parent;
	}

	@Override
	public JspTag getParent() {
		return this.parent;
	}

	@Override
	public void setJspContext(JspContext pc) {
		this.jspContext = pc;
	}

	@Override
	public void setJspBody(JspFragment jspBody) {
		this.jspBody = jspBody;
	}

	protected JspContext getJspContext() {
		return jspContext;
	}

	protected JspFragment getJspBody() {
		return jspBody;
	}

}
