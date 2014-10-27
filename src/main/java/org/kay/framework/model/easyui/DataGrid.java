package org.kay.framework.model.easyui;

import java.io.Serializable;
import java.util.List;

/*
 {
 "total" : "28",
 "rows" : [{
 "itemid" : "EST-1",
 "productid" : "FI-SW-01",
 "listprice" : "16.50",
 "unitcost" : "10.00",
 "status" : "P",
 "attr1" : "Large"
 }, {
 "itemid" : "EST-10",
 "productid" : "K9-DL-01",
 "listprice" : "18.50",
 "unitcost" : "12.00",
 "status" : "P",
 "attr1" : "Spotted Adult Female"
 }]
 }
 */
public class DataGrid<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int total;
	private List<T> rows;

	public DataGrid() {
		super();
	}

	public DataGrid(int total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
