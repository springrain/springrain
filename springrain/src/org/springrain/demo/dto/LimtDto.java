package org.springrain.demo.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springrain.demo.entity.Menu;

public class LimtDto {
	//fileds
	private String roleId;
	private String roleName;
	private List<Menu> menus;
	//getter  setter
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public List<Menu> addMenu(Menu menu){
		if(CollectionUtils.isEmpty(this.menus)){
			this.menus=new ArrayList<Menu>();
		}
		this.menus.add(menu);
		return this.menus;
	}
	//constructor
	public LimtDto(){
		
	}
	//equals and hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LimtDto other = (LimtDto) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}
	

	
}
