package com.hone.auth.server.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hone.auth.server.service.DeptService;
import com.hone.common.json.CResult;
import com.hone.entity.auth.DeptParam;
import com.hone.entity.auth.DeptQuery;
import com.hone.entity.auth.P_Dept;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping
@Api(description = "单位管理-API接口")
public class DeptController {

	@Resource
	private DeptService deptService;
	
	@ApiOperation(value = "单位列表", notes ="单位相关信息" )
    @RequestMapping(value = "/list",method = RequestMethod.POST)
	public PageInfo<P_Dept> list(DeptQuery query){
		PageInfo<P_Dept> result = deptService.list(query);
		return result;
	}
	
	@ApiOperation(value = "单位新增", notes ="单位新增相关信息" )
    @RequestMapping(value = "/save",method = RequestMethod.POST)
	public CResult<P_Dept> save(DeptParam param){
		boolean status = deptService.update(param);
		return new CResult<P_Dept>(status, "200", null, null , null, "单位新增成功！");
	}
	
	@ApiOperation(value = "单位修改", notes ="单位修改相关信息" )
    @RequestMapping(value = "/update",method = RequestMethod.POST)
	public CResult<P_Dept> update(DeptParam param){
		boolean status = deptService.update(param);
		return new CResult<P_Dept>(status, "200", null, null , null, "单位修改成功！");
	}
	
	@ApiOperation(value = "单位删除", notes ="单位删除相关信息" )
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
	public CResult<P_Dept> remove(String ids) {
		boolean status = deptService.remove(ids);
		return new CResult<P_Dept>(status, "200", null, null , null, "单位删除成功！");
	}
	
	@ApiOperation(value = "单位查询", notes ="单位查询相关信息" )
    @RequestMapping(value = "/query",method = RequestMethod.POST)
	public CResult<P_Dept> query(Long id) {
		P_Dept dept = deptService.query(id);
		if(dept != null) {
			return new CResult<P_Dept>(true, "200", dept, null , null, "单位查询成功！");
		} else {
			return new CResult<P_Dept>(false, "200", dept, null , null, "单位查询失败，请检查主键是否错误！");
		}
	}
	
	@ApiOperation(value = "下级单位查询", notes ="下级单位相关信息" )
    @RequestMapping(value = "/child/list",method = RequestMethod.POST)
	public CResult<P_Dept> listByParentId(Long parentId) {
		List<P_Dept> list = deptService.listByParentId(parentId);
		if(list != null) {
			return new CResult<P_Dept>(true, "200", null, list , null, "单位查询成功！");
		} else {
			return new CResult<P_Dept>(false, "200", null, list , null, "单位查询失败，请检查主键是否错误！");
		}
	}
}
