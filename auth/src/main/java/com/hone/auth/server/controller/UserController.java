package com.hone.auth.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hone.auth.server.service.UserService;
import com.hone.common.json.CResult;
import com.hone.entity.auth.P_User;
import com.hone.entity.auth.UserParam;
import com.hone.entity.auth.UserQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
@Api(description = "用户管理-API接口")
public class UserController {

	@Autowired
	private UserService userService;
    @Autowired
    private HttpServletRequest request;
    
	@ApiOperation(value = "用户列表", notes ="用户相关信息" )
    @RequestMapping(value = "/list",method = RequestMethod.POST)
	public PageInfo<P_User> list(UserQuery query){
		PageInfo<P_User> result = userService.list(query);
		return result;
	}
	
	@ApiOperation(value = "用户保存", notes ="用户保存必须信息" )
    @RequestMapping(value = "/save",method = RequestMethod.POST)
	public CResult<P_User> save(UserParam param) {
		if(param.getOperateUserId() == null) {
			String userId = request.getHeader("user_id");
			param.setOperateUserId(Long.getLong(userId));
		}
		Long id = userService.save(param);
		return new CResult<P_User>(true,"200", null, null , id, "新增用戶成功！");
	}
	
	@ApiOperation(value = "用户修改", notes ="用户保存必须信息" )
    @RequestMapping(value = "/update",method = RequestMethod.POST)
	public CResult<P_User> update(UserParam param) {
		if(param.getOperateUserId() == null) {
			String userId = request.getHeader("user_id");
			param.setOperateUserId(Long.getLong(userId));
		}
		boolean status = userService.update(param);
		return new CResult<P_User>(status, "200", null, null , null, "新增用戶成功！");
	}
	
	@ApiOperation(value = "用户删除", notes ="用户删除信息" )
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
	public CResult<P_User> remove(String id) {
		boolean status = userService.remove(id);
		return new CResult<P_User>(status, "200", null, null , null, "新增用戶成功！");
	}
	
	@ApiOperation(value = "用户查询", notes ="用户查询信息" )
    @RequestMapping(value = "/query",method = RequestMethod.POST)
	public CResult<P_User> query(String id) {
		P_User user = userService.query(id);
		return new CResult<P_User>(true, "200", user, null , null, "新增用戶成功！");
	}
}
