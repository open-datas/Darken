package com.hone.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>登录信息</p>
 * @author hourz
 * @since 2019-06-10
 */
@Data
@ApiModel("登录信息")
public class T_Auth {
	@ApiModelProperty(notes = "登录名(可以是用户名、手机、邮箱)")
	private String loginName;
	@ApiModelProperty(notes = "密码")
	private String password;
	@ApiModelProperty(notes = "是否记住登录")
	private boolean rememberMe;
	@ApiModelProperty(notes = "验证码")
	private String captcha;
}
