package com.hone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>单位信息</p>
 * @author hourz
 * @since 2019-06-06
 */
@Data
@ApiModel("单位信息")
public class T_Dept {
	@ApiModelProperty(notes = "主键")
	private Long id;
	@ApiModelProperty(notes = "登录名")
	private String deptName;
	@ApiModelProperty(notes = "用户名")
	private String deptCode;
	@ApiModelProperty(notes = "单位电话")
	private String deptPhone;
	@ApiModelProperty(notes = "手机号")
	private String deptAddress;
	@ApiModelProperty(notes = "上级单位ID")
	private Long parentId;
	@ApiModelProperty(notes = "上级单位名称")
	private String parentName;
	@ApiModelProperty(notes = "创建时间")
	private Date createTime;
	@ApiModelProperty(notes = "登录名")
	private Date updateTime;
	@ApiModelProperty(notes = "删除时间")
	private Date removeTime;
	@ApiModelProperty(notes = "操作人ID")
	private Long operateUserID;
	@ApiModelProperty(notes = "登录名")
	private String operateUser;
	@ApiModelProperty(notes = "备注")
	private String remark;
}
