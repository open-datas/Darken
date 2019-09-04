package com.hone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>角色信息</p>
 * @author hourz
 * @since 2019-06-10
 */
@Data
@ApiModel("角色信息")
public class T_Role {
    @ApiModelProperty(notes = "主键")
    private Long id;
    @ApiModelProperty(notes = "角色名称")
    private String roleName;
    @ApiModelProperty(notes = "角色代码")
    private String roleCode;
    @ApiModelProperty(notes = "创建时间")
    private Date createTime;
    @ApiModelProperty(notes = "修改时间")
    private Date updateTime;
    @ApiModelProperty(notes = "删除时间")
    private Date removeTime;
    @ApiModelProperty(notes = "操作人员")
    private Long operateUserId;
    @ApiModelProperty(notes = "备注信息")
    private String remark;
}
