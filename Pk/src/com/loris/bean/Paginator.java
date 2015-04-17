package com.loris.bean;

public class Paginator {
	private Integer pageNumber;
	private Integer recordSize;
	private Boolean moreRecords;
	private int totalItems; 
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getRecordSize() {
		return recordSize;
	}
	public void setRecordSize(Integer recordSize) {
		this.recordSize = recordSize;
	}
	public Boolean getMoreRecords() {
		return moreRecords;
	}
	public void setMoreRecords(Boolean moreRecords) {
		this.moreRecords = moreRecords;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public void moreRecords(){
		if ((pageNumber.intValue() * recordSize.intValue()) < totalItems)
			this.moreRecords = Boolean.TRUE;
		else		
			this.moreRecords = Boolean.FALSE;
		
	}
}