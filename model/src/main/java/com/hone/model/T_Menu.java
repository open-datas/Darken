package com.hone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("单位信息")
public class T_Menu {
    @ApiModelProperty(notes = "主键")
    private Long id;
    @ApiModelProperty(notes = "菜单名称")
    private String menuName;
    @ApiModelProperty(notes = "菜单地址")
    private String address;
    @ApiModelProperty(notes = "等级")
    private Integer level;
    @ApiModelProperty(notes = "菜单图标")
    private String icon;
    @ApiModelProperty(notes = "上级菜单ID")
    private Long parentId;
    @ApiModelProperty(notes = "新增时间")
    private Date createTime;
    @ApiModelProperty(notes = "更新时间")
    private Date updateTime;
    @ApiModelProperty(notes = "删除时间")
    private Date removeTime;
    @ApiModelProperty(notes = "操作人员")
    private Long operate_user_id;
    @ApiModelProperty(notes = "菜单备注")
    private String remark;
}
