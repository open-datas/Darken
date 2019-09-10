package com.hone.dao.auth;

import com.hone.entity.auth.P_Permission;

import java.util.List;

/**
 * PermissionDao
 *
 * @Author hourz
 * @since 2019-07-16
 */
public interface PermissionDao {

    List<P_Permission> listByRoleId(String id);
}
