package org.kay.framework.taglib.easyui;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.util.StringUtils;

/*
 $('#tg').tree({
 url:'retrieveTree',
 onClick: function(node){
 alert(node.text);  // alert node text property when clicked
 }
 });
 */
public class TreeTag extends SimpleTagSupport {

	private String id;
	private String url;
	private String onClick;

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div id=\"" + this.id + "\"></div>");
		buffer.append("<script type=\"text/javascript\">");
		buffer.append(this.id + "=$('#" + this.id + "').tree({");
		buffer.append("url:'" + this.url + "',");
		buffer.append("animate:true,");
		if (!StringUtils.isEmpty(this.onClick)) {
			buffer.append("onClick:" + this.onClick);
		}
		buffer.append("});");
		buffer.append("</script>");
		out.write(buffer.toString());
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

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

}
