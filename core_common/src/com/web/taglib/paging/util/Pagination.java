package com.web.taglib.paging.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pagination implements Serializable {

	/**
	 *
	 */
	public static final int DEFAULT_PAGE_SIZE = 50;
	private static final long serialVersionUID = -8964564357413471064L;
	private int pageSize;
	private long totalRecord;
	private List<String> pageList;
	private String currentPage;
	private String nextPage;
	private String prevPage;
	private String nextScroll;
	private String prevScroll;
	private String totalPages;

	private Pagination() {
		pageList = new ArrayList<String>();
		pageSize = 50;
		totalRecord = 0;
	}

	public static Pagination getInstance() {
		return getInstance(1);
	}

	public static Pagination getInstance(long currentPage) {
		return getInstance(currentPage, 0);
	}

	public static Pagination getInstance(long currentPage, long totalRecord) {
		return getInstance(currentPage, totalRecord, 50);
	}

	public static Pagination getInstance(long currentPage, long totalRecord, int pageSize) {

		Pagination pagination = new Pagination();

		pagination.setCurrentPage(String.valueOf(currentPage));

		long noOfPages = totalRecord % pageSize == 0 ? (totalRecord / pageSize) : (totalRecord / pageSize + 1);

		long currentScroll = (currentPage - 1) / 10;

		// long start = currentScroll * 10 + 1;
		long start = currentScroll * 10 + 1;
		if (start == 0) {
			start = 1;
		}

		for (long i = start; (i <= (currentScroll + 1) * 10) && (i <= noOfPages); i++) {
			pagination.getPageList().add(String.valueOf(i));
		}

		long prevPage = currentPage == 1 ? -1 : (currentPage - 1);
		long nextPage = currentPage == noOfPages ? -1 : (currentPage + 1);

		pagination.setNextPage(String.valueOf(nextPage));
		pagination.setPrevPage(String.valueOf(prevPage));

		pagination.setNextScroll(String.valueOf(noOfPages));
		pagination.setPrevScroll(String.valueOf(1));

		pagination.setTotalPages(String.valueOf(noOfPages));

		pagination.setPageSize(pageSize);
		
		pagination.setTotalRecord(totalRecord);

		return pagination;

	}

	public List<String> getPageList() {
		return pageList;
	}

	public void setPageList(List<String> pageList) {
		this.pageList = pageList;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(String prevPage) {
		this.prevPage = prevPage;
	}

	public String getNextScroll() {
		return nextScroll;
	}

	public void setNextScroll(String nextScroll) {
		this.nextScroll = nextScroll;
	}

	public String getPrevScroll() {
		return prevScroll;
	}

	public void setPrevScroll(String prevScroll) {
		this.prevScroll = prevScroll;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isPrevScrollAvail() {
		return !this.prevPage.equals("-1");
	}

	public boolean isNextScrollAvail() {
		return !this.nextPage.equals("-1");
	}

	public boolean isPrevPageAvail() {
		return !this.prevPage.equals("-1");
	}

	public boolean isNextPageAvail() {
		return !this.nextPage.equals("-1");
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public PageDTO getPageDTO() {
		PageDTO page = new PageDTO();
		page.setPageNo(new Integer(currentPage).intValue());
		page.setPageSize(pageSize);
		return page;
	}

}
