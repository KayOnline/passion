package org.kay.framework.model.easyui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Combo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String valueField = "value";
	private String textField = "label";
	private List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

	public String getValueField() {
		return valueField;
	}

	public String getTextField() {
		return textField;
	}

	public List<HashMap<String, String>> getData() {
		return data;
	}

	public void addItem(String value, String label) {
		HashMap<String, String> comboItem = new HashMap<String, String>();
		comboItem.put(this.valueField, value);
		comboItem.put(this.textField, label);
		this.data.add(comboItem);
	}

}
