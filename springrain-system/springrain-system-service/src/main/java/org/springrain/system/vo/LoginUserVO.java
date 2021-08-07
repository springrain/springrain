package org.springrain.system.vo;

import org.springrain.system.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @descriptions: 登录用户信息, 用于前端展示
 * @author: YSK
 * @date: 2021/5/7 10:51
 * @version: 1.0
 */
public class LoginUserVO extends User {

    private String roleName;
    private String roleCode;
    private List<String> codes;
    private Map<String, String> codeMap;

    public LoginUserVO() {
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public Map<String, String> getCodeMap() {
        return codeMap;
    }

    public void setCodeMap(Map<String, String> codeMap) {
        this.codeMap = codeMap;
    }

    public String getRoleName() {
        return roleName;
    }

    public LoginUserVO setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public LoginUserVO setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }
}
