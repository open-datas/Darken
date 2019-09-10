package com.hone.dao.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.hone.dao.auth.provider.DeptProvider;
import com.hone.entity.auth.DeptParam;
import com.hone.entity.auth.DeptQuery;
import com.hone.entity.auth.P_Dept;

/**
 * <p>单位操作</p>
 * @author hourz
 * @since 2018-09-17
 */
@Mapper
public interface DeptDao {

	@SelectProvider(method = "list", type = DeptProvider.class)
	List<P_Dept> list(DeptQuery query);
	
	@SelectProvider(method = "save", type = DeptProvider.class)
	void save(DeptParam param);
	
	@SelectProvider(method = "update", type = DeptProvider.class)
	void update(DeptParam param);
	
	@SelectProvider(method = "remove", type = DeptProvider.class)
	void remove(String ids);
	
	@SelectProvider(method = "query", type = DeptProvider.class)
	P_Dept query(Long id);
	
	@SelectProvider(method = "queryByUserId", type = DeptProvider.class)
	P_Dept queryByUserId(Long userId);
	
	@SelectProvider(method = "queryByMenuId", type = DeptProvider.class)
	List<P_Dept> queryByMenuId(Long menuId);
	
	@SelectProvider(method = "queryByParentId", type = DeptProvider.class)
	List<P_Dept> listByParentId(Long parentId);
}
