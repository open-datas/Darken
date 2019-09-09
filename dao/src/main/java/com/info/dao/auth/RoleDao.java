package com.info.dao.auth;

import com.hone.entity.auth.P_Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>角色操作</p>
 * @author hourz
 * @since 2018-09-17
 */
@Mapper
public interface RoleDao {


    List<P_Role> listByUserId(Long id);
}
