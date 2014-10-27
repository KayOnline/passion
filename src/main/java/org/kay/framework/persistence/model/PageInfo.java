package org.kay.framework.persistence.model;

import java.io.Serializable;

public class PageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int curPageNum = 1;
	private int rowOfPage;
	private int allRowNum;
	
	public PageInfo() {
	}

	public PageInfo(int curPageNum, int rowOfPage) {
		this.curPageNum = curPageNum;
		this.rowOfPage = rowOfPage;
	}

	public int getCurPageNum() {
		return curPageNum;
	}

	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}

	public int getRowOfPage() {
		return rowOfPage;
	}

	public void setRowOfPage(int rowOfPage) {
		this.rowOfPage = rowOfPage;
	}

	public boolean isEnabled() {
		return this.curPageNum > 0 && this.rowOfPage > 0;
	}

	public int getAllRowNum() {
		return allRowNum;
	}

	public void setAllRowNum(int allRowNum) {
		this.allRowNum = allRowNum;
	}

}
