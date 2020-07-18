package com.mtw.common;

import java.util.List;

public class PageInfo<T> {
	//总共多少条
	private int totalRow=0;
	//每页多少条
	private int rowPerPage=5;
	//一共多少页
	private int totalPages;
	//当前第几页
	private int curPage=1;
	//起始第几条
	private int start=1;
	//结束第几条
	private int end;
	//排序的字段名
	private String orderBy;
	//升序降序
	private String order;
	
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	private List<T> results;
	
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	public int getTotalPages() {
		
		return totalRow % rowPerPage >0? 
			totalRow/rowPerPage+1 : totalRow/rowPerPage;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getStart() {
		this.start=(curPage-1)*rowPerPage+1;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return start+rowPerPage-1;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "PageInfo [totalRow=" + totalRow + ", rowPerPage=" + rowPerPage + ", totalPages=" + totalPages
				+ ", curPage=" + curPage + ", start=" + start + ", end=" + end + "]";
	}
	
	
}
