package org.springrain.system.dto;

import org.springrain.system.entity.Role;
import org.springrain.system.entity.RoleOrg;

import java.io.Serializable;
import java.util.List;

/**
 * @descriptions: 角色更新
 * @author: YSK
 * @date: 2021/7/6 9:18
 * @version: 1.0
 */
public class RoleDTO extends Role implements Serializable {
    /**
     * 部门ids
     */
    private List<String> orgIds;
    /**
     * 角色部门对象
     */
    private List<RoleOrg> roleOrgList;

    public List<RoleOrg> getRoleOrgList() {
        return roleOrgList;
    }

    public void setRoleOrgList(List<RoleOrg> roleOrgList) {
        this.roleOrgList = roleOrgList;
    }

    public List<String> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<String> orgIds) {
        this.orgIds = orgIds;
    }
}
