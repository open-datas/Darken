package com.hone.dao.auth.provider;

import com.hone.entity.auth.P_User;
import com.hone.entity.auth.UserParam;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * <p>用户SQL封装实现</p>
 * @author hourz
 * @since 2019-06-10
 */
public class UserProvider {

	public String list(Map<String, Object> item) {
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			if(item.get("loginName") != null && item.get("loginName") != "") {
				WHERE("login_name=#{loginName}");
			}
			if(item.get("username") != null && item.get("username") != "") {
				WHERE("username=#{username}");
			}
			if(item.get("deptId") != null && item.get("deptId") != "") {
				WHERE("dept_id=#{deptId}");
			}
			if(item.get("mobile") != null && item.get("mobile") != "") {
				WHERE("mobile=#{mobile}");
			}
			if(item.get("mail") != null && item.get("mail") != "") {
				WHERE("mail=#{mail}");
			}
			ORDER_BY("create_time desc");
		}}.toString();
	}
	
	public String save(UserParam param) {
		return new SQL(){{
			INSERT_INTO("t_user");
			INTO_COLUMNS("login_name", "username", "password", 
					"dept_id", "mobile", "mail", 
					"create_time", "operate_user_id", "status");
            INTO_VALUES("#{loginName}", "#{username}", "#{password}", 
            		"#{deptId}", "#{mobile}", "#{mail}", 
            		"#{createTime}", "#{operateUserId}", "#{status}");
		}}.toString();
	}
	
	public String update(UserParam param) {
		return new SQL(){{
			UPDATE("t_user");
			if(param.getLoginName() != null && param.getLoginName() != "") {
				SET("login_name=#{loginName}");
			}
			if(param.getUsername() != null && param.getUsername() != "") {
				SET("username=#{username}");
			}
			if(param.getDeptId() != null && param.getDeptId() != "") {
				SET("dept_id=#{deptId}");
			}
			if(param.getMobile() != null && param.getMobile() != "") {
				SET("mobile=#{mobile}");
			}
			if(param.getMail() != null && param.getMail() != "") {
				SET("mail=#{mail}");
			}
			if(param.getUpdateTime() != null) {
				SET("update_time=#{updateTime}");
			}
			if(param.getOperateUserId() != null) {
				SET("operate_user_id=#{operateUserId}");
			}
			WHERE("id=#{id}");
		}}.toString();
	}
	
	public String updateToken(P_User user) {
		return new SQL(){{
			UPDATE("t_user");
			if(user.getTokenId() != null && user.getTokenId() != "") {
				SET("token_id = #{tokenId}");
			}
			WHERE("id=#{id}");
		}}.toString();
	}
	
	public String remove(String ids) {
		StringBuilder sb = new StringBuilder("delete t_user where id in (");
        for (String id: ids.split(",")) {
            sb.append("\"").append(id).append("\",");
        }
        int index = sb.lastIndexOf(",");
        sb.replace(index, index+1, ")");
        return sb.toString();
	}

	public String query(String userId){
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE("user_id=#{userId}");
		}}.toString();
	}
	
	public String queryByUserName(String userName){
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE("username=#{username}");
		}}.toString();
	}

	public String queryDeptId(Long deptId){
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE("dept_id=#{deptId}");
		}}.toString();
	}
}
