package com.hone.auth.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hone.auth.server.service.DeptService;
import com.hone.dao.auth.DeptDao;
import com.hone.entity.auth.DeptParam;
import com.hone.entity.auth.DeptQuery;
import com.hone.entity.auth.P_Dept;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;
	
	@Override
	public PageInfo<P_Dept> list(DeptQuery query) {
		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		List<P_Dept> list = deptDao.list(query);
		return new PageInfo<P_Dept>(list);
	}

	@Override
	public boolean save(DeptParam param) {
		try {
			deptDao.save(param);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(DeptParam param) {
		try {
			deptDao.update(param);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(String ids) {
		try {
			deptDao.remove(ids);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public P_Dept query(Long id) {
		P_Dept dept = deptDao.query(id);
		return dept;
	}

	@Override
	public List<P_Dept> listByParentId(Long parentId) {
		return deptDao.listByParentId(parentId);
	}

}
