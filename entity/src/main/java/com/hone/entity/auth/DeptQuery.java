package com.hone.entity.auth;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class DeptQuery {
	// 登录名
	@ApiModelProperty(notes = "当前页码")
	private Integer pageNum;
	// 用户名
	@ApiModelProperty(notes = "页面大小")
	private Integer pageSize;
	// 登录名
	@ApiModelProperty(notes = "登录名")
	private String deptName;
	// 用户名
	@ApiModelProperty(notes = "用户名")
	private String deptCode;
	// 手机号
	@ApiModelProperty(notes = "手机号")
	private String deptPhone;
	// 单位地址
	@ApiModelProperty(notes = "单位地址")
	private String deptAddress;
	// 上级单位ID
	@ApiModelProperty(notes = "上级单位ID")
	private Long parentId;
	// 创建时间
	@ApiModelProperty(notes = "创建时间")
	private Date createTime;
	// 修改时间
	@ApiModelProperty(notes = "修改时间")
	private Date updateTime;
	// 删除时间
	@ApiModelProperty(notes = "删除时间")
	private Date removeTime;
	// 操作人ID
	@ApiModelProperty(notes = "操作人ID")
	private Long operateUserId;
	// 备注
	@ApiModelProperty(notes = "备注")
	private String remark;
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptPhone() {
		return deptPhone;
	}
	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}
	public String getDeptAddress() {
		return deptAddress;
	}
	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getRemoveTime() {
		return removeTime;
	}
	public void setRemoveTime(Date removeTime) {
		this.removeTime = removeTime;
	}
	public Long getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
