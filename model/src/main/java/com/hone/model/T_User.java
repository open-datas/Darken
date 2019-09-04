package com.hone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>用户对象</p>
 * @author hourz
 * @since 2018-09-01
 */
@Data
@ApiModel("用户信息")
public class T_User {
	@ApiModelProperty(notes = "主键")
	private Long id;
	@ApiModelProperty(notes = "登录名")
	private String loginName;
	@ApiModelProperty(notes = "用户名")
	private String username;
	@ApiModelProperty(notes = "密码")
	private String password;
	@ApiModelProperty(notes = "手机号")
	private String mobile;
	@ApiModelProperty(notes = "单位ID")
	private String deptId;
	@ApiModelProperty(notes = "单位名称")
	private String deptName;
	@ApiModelProperty(notes = "创建时间")
	private Date createTime;
	@ApiModelProperty(notes = "修改时间")
	private Date updateTime;
	@ApiModelProperty(notes = "删除时间")
	private Date removeTime;
	@ApiModelProperty(notes = "操作人ID")
	private Long operateUserID;
	@ApiModelProperty(notes = "操作人")
	private String operateUser;
	@ApiModelProperty(notes = "备注")
	private String remark;
}
