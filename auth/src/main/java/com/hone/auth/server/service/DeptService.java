package com.hone.auth.server.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hone.entity.auth.DeptParam;
import com.hone.entity.auth.DeptQuery;
import com.hone.entity.auth.P_Dept;

public interface DeptService {

	PageInfo<P_Dept> list(DeptQuery query);

	boolean save(DeptParam param);

	boolean update(DeptParam param);

	boolean remove(String ids);

	P_Dept query(Long id);

	List<P_Dept> listByParentId(Long parentId);

	
}
