package com.info.dao.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.info.dao.auth.provider.UserProvider;
import com.hone.entity.auth.P_User;
import com.hone.entity.auth.UserParam;
import com.hone.entity.auth.UserQuery;

/**
 * <p>用户操作</p>
 * @author hourz
 * @since 2018-09-17
 */
@Mapper
public interface UserDao {

	@SelectProvider(method = "list", type = UserProvider.class)
	List<P_User> list(UserQuery query);
	
	@SelectProvider(method = "save", type = UserProvider.class)
	void save(UserParam param);
	
	@SelectProvider(method = "update", type = UserProvider.class)
	void update(UserParam param);
	
	@SelectProvider(method = "updateToken", type = UserProvider.class)
	void updateToken(P_User user);
	
	@SelectProvider(method = "remove", type = UserProvider.class)
	Long remove(String ids);

	@SelectProvider(method = "query", type = UserProvider.class)
    P_User query(Long id);
	
	@SelectProvider(method = "queryByUserName", type = UserProvider.class)
    P_User queryByUserName(String userName);

	@SelectProvider(method = "queryDeptId", type = UserProvider.class)
	List<P_User> queryByDeptId(Long deptId);
}
