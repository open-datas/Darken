package com.hone.auth.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hone.auth.server.service.UserService;
import com.hone.dao.auth.UserDao;
import com.hone.entity.auth.P_User;
import com.hone.entity.auth.UserParam;
import com.hone.entity.auth.UserQuery;

/**
 * <p>用户实现对象</p>
 * @author hourz
 * @since 2019-07-29
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;
	
	@Override
	public PageInfo<P_User> list(UserQuery query) {
		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		List<P_User> list = userDao.list(query);
		return new PageInfo<P_User>(list);
	}

	@Override
	public Long save(UserParam param) {
		userDao.save(param);
		return param.getUserId();
	}

	@Override
	public boolean update(UserParam param) {
		userDao.update(param);
		return true;
	}

	@Override
	public boolean remove(String ids) {
		userDao.remove(ids);
		return true;
	}

	@Override
	public P_User query(String id) {
		return userDao.query(Long.parseLong(id));
	}

}
