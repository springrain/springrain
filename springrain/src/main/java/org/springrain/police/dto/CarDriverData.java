package org.springrain.police.dto;

import java.util.List;

/**
 * 违法汽车列表，分页信息
 * @author 跨时代
 *
 */
public class CarDriverData {
	private List<CarDriverInfro> content;
	private String totalPages;//总页数
	private String totalCount;//总条数
	private String pageNo;//当前页
	private String pageSize;//每页多少条
	
	
	public List<CarDriverInfro> getContent() {
		return content;
	}

	public void setContent(List<CarDriverInfro> content) {
		this.content = content;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
