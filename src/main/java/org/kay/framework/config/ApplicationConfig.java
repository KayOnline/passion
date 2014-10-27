package org.kay.framework.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * application_config.properties application-context.xml
 * 
 * @author Kay
 * 
 */
public class ApplicationConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private String enterpriseName;
	private String applicationCode;
	private String applicationName;
	private boolean runInDebugMode;
	private Map<String, Object> configProps = new HashMap<String, Object>();

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public boolean isRunInDebugMode() {
		return runInDebugMode;
	}

	public void setRunInDebugMode(boolean runInDebugMode) {
		this.runInDebugMode = runInDebugMode;
	}

	public Map<String, Object> getConfigProps() {
		return configProps;
	}

	public void setConfigProps(Map<String, Object> configProps) {
		this.configProps = configProps;
	}

}
