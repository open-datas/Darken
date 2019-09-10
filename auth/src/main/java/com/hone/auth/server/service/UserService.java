package com.hone.auth.server.service;

import com.github.pagehelper.PageInfo;
import com.hone.entity.auth.P_User;
import com.hone.entity.auth.UserParam;
import com.hone.entity.auth.UserQuery;

/**
 * <p>用户对象</p>
 * @author hourz
 * @since 2019-07-29
 */
public interface UserService {

	PageInfo<P_User> list(UserQuery query);

	Long save(UserParam param);

	boolean update(UserParam param);

	boolean remove(String id);

	P_User query(String id);

}
