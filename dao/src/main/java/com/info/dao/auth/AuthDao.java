package com.info.dao.auth;

import com.info.dao.auth.provider.AuthProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.hone.entity.auth.P_Auth;
import com.hone.entity.auth.P_User;

/**
 * <p>登录操作</p>
 * @author hourz
 * @since 2018-09-10
 */
@Mapper
public interface AuthDao {

	/**
	 * 根据邮箱获取用户信息
	 * @param auth 权限
	 * @return 用户信息
	 */
	@SelectProvider(method = "email", type = AuthProvider.class)
	P_User loginMail(P_Auth auth);
	
	/**
	 * 根据手机号获取用户信息
	 * @param auth 权限
	 * @return 用户信息
	 */
	@SelectProvider(method = "mobile", type = AuthProvider.class)
	P_User loginMobile(P_Auth auth);
	
	/**
	 * 根据登录名获取用户信息
	 * @param auth 权限
	 * @return 用户信息
	 */
	@SelectProvider(method = "name", type = AuthProvider.class)
	P_User loginName(P_Auth auth);

	/**
	 * 根据用户ID验证用户登录
	 * @param userId 用户ID
	 * @return 用户信息
	 */
	@SelectProvider(method = "check", type = AuthProvider.class)
	P_User checkUser(String userId);
	
}
