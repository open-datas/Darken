package com.hone.entity.auth;

/**
 * P_Role
 *
 * @Author hourz
 * @since 2019-07-16
 */
public class P_Role {
    private String id;
    private String roleName;
    private String roleCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
