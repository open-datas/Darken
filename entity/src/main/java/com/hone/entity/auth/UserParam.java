package com.hone.entity.auth;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户查询对象")
public class UserParam {
	// 登录名
	@ApiModelProperty(notes = "用戶Id")
	private Long userId;
	// 登录名
	@ApiModelProperty(notes = "登录名", required = true)
	private String loginName;
	// 用户名
	@ApiModelProperty(notes = "用户名", required = true)
	private String username;
	// 手机号
	@ApiModelProperty(notes = "手机号", required = true)
	private String mobile;
	// 邮箱
	@ApiModelProperty(notes = "邮箱", required = true)
	private String mail;
	// 单位ID
	@ApiModelProperty(notes = "单位ID")
	private String deptId;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
