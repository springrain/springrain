package org.springrain.demo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

public class CommonJSON {
	private Integer total;
	List<Map<String, Object>> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	public List<Map<String, Object>> addRow(Map<String, Object> map){
		if(CollectionUtils.isEmpty(rows)){
			this.rows=new ArrayList<Map<String,Object>>();
		}
		this.rows.add(map);
		return this.rows;
	}
}
