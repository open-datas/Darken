package com.hone.dao.auth.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.hone.entity.auth.DeptParam;

/**
 * <p>单位SQL封装实现</p>
 * @author hourz
 * @since 2018-09-17
 */
public class DeptProvider {

	public String list(Map<String, Object> item) {
		return new SQL(){{
			SELECT("*");
			FROM("t_dept");
			if(item.get("deptName") != null && item.get("deptName") != "") {
				WHERE("dept_name=#{deptName}");
			}
			if(item.get("deptCode") != null && item.get("deptCode") != "") {
				WHERE("dept_code=#{deptCode}");
			}
			if(item.get("deptPhone") != null && item.get("deptPhone") != "") {
				WHERE("dept_phone=#{deptPhone}");
			}
			if(item.get("deptAddress") != null && item.get("deptAddress") != "") {
				WHERE("dept_address=#{deptAddress}");
			}
			if(item.get("userId") != null && item.get("userId") != "") {
				WHERE("operate_user_id=#{userId}");
			}
			if(item.get("remark") != null && item.get("remark") != "") {
				WHERE("remark=#{remark}");
			}
			ORDER_BY("create_time desc");
		}}.toString();
	}
	
	public String save(DeptParam param) {
		return new SQL(){{
			INSERT_INTO("t_dept");
			INTO_COLUMNS("dept_name", "dept_code", "dept_phone", 
					"parent_id", "dept_address", "description", 
					"dept_status", "create_time", "operate_user_id", "remark");
            INTO_VALUES("#{deptName}", "#{deptCode}", "#{deptPhone}", 
            		"#{parentId}", "#{deptAddress}", "#{description}",
            		"#{deptStatus}", "#{createTime}", "#{operateUserId}", "#{remark}");
		}}.toString();
	}
	
	public String update(DeptParam param) {
		return new SQL(){{
			UPDATE("t_dept");
			if(param.getDeptName() != null && param.getDeptName() != "") {
				SET("dept_name = #{deptName}");
			}
			if(param.getDeptCode() != null && param.getDeptCode() != "") {
				SET("dept_code = #{deptCode}");
			}
			if(param.getDeptPhone() != null && param.getDeptPhone() != "") {
				SET("dept_phone = #{deptPhone}");
			}
			if(param.getDeptAddress() != null && param.getDeptAddress() != "") {
				SET("dept_address = #{deptAddress}");
			}
			if(param.getDescription() != null && param.getDescription() != "") {
				SET("description = #{description}");
			}
			if(param.getDeptStatus() != null && param.getDeptStatus() != "") {
				SET("status = #{status}");
			}
			SET("operate_user_id = #{operateUserId}");
			SET("update_time = #{updateTime}");
			WHERE("id=#{id}");
		}}.toString();
	}
	
	public String remove(String ids) {
		StringBuilder sb = new StringBuilder("delete t_dept where id in (");
        for (String id: ids.split(",")) {
            sb.append("\"").append(id).append("\",");
        }
        int index = sb.lastIndexOf(",");
        sb.replace(index, index+1, ")");
        return sb.toString();
	}

	public String query(String id){
		return new SQL(){{
			SELECT("*");
			FROM("t_dept");
			WHERE("id=#{id}");
		}}.toString();
	}
	
	public String queryByUserId(String userId){
		return new SQL(){{
			SELECT("*");
			FROM("t_dept");
			WHERE("operate_user_id=#{userId}");
		}}.toString();
	}
	
	public String queryByMenuId(String menuId){
		return new SQL(){{
			SELECT("*");
			FROM("t_dept d right join t_role_dept r on d.id = r.dept_id");
			WHERE("r.menuId=#{menuId}");
		}}.toString();
	}
	
	public String queryParentId(Long parentId){
		return new SQL(){{
			SELECT("*");
			FROM("t_dept");
			WHERE("parent_id=#{parentId}");
		}}.toString();
	}
	
}
