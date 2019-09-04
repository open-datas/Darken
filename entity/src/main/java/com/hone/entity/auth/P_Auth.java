package com.hone.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>用户登录信息</p>
 * @author hourz
 * @since 2019-06-06
 */
@ApiModel("登录信息")
public class P_Auth {

	// 登录名(可以是用户名、手机、邮箱)
	@ApiModelProperty(notes = "登录名(可以是用户名、手机、邮箱)")
	private String tokenId;
	// 登录名(可以是用户名、手机、邮箱)
	@ApiModelProperty(notes = "登录名(可以是用户名、手机、邮箱)")
	private String userId;
	// 登录名(可以是用户名、手机、邮箱)
	@ApiModelProperty(notes = "登录名(可以是用户名、手机、邮箱)")
	private String loginName;
	// 密码
	@ApiModelProperty(notes = "密码")
	private String password;
	// 是否记住登录
	@ApiModelProperty(notes = "是否记住登录")
	private boolean rememberMe;
	// 验证码
	@ApiModelProperty(notes = "验证码")
	private String captcha;
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
