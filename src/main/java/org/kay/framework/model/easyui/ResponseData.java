package org.kay.framework.model.easyui;

import java.io.Serializable;
import java.util.Map;

public class ResponseData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, DataWrap> dataWraps;
	private String errorMessage;
	private String message;

	public Map<String, DataWrap> getDataWraps() {
		return dataWraps;
	}

	public void setDataWraps(Map<String, DataWrap> dataWraps) {
		this.dataWraps = dataWraps;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
