package com.info.dao.auth.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * <p>用户SQL封装实现</p>
 * @author hourz
 * @since 2019-06-10
 */
public class AuthProvider {

	/**
	 * 根据邮箱查询
	 * @return
	 */
	public String email() {
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE(" email=#{loginName} ");
		}}.toString();
	}
	/**
	 * 根据短信查询
	 * @return
	 */
	public String mobile() {
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE("mobile=#{loginName}");
		}}.toString();
	}
	/**
	 * 根据登录名查询
	 * @return
	 */
	public String name() {
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE("login_name=#{loginName}");
		}}.toString();
	}
	/**
	 * 根据登录名查询
	 * @return
	 */
	public String check() {
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE("user_id=#{userId}");
		}}.toString();
	}
}
