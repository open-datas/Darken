package com.hone.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 登陆信息对象，这个对象在用户登陆成功后，会被创建并放入Session中
 * @author hourz
 * @since 2016-05-23
 */
@ApiModel("登录信息")
public class LoginInfo {
	// 通行证
	@ApiModelProperty(notes = "通行证")
	private P_User user;
	// IP地址
	@ApiModelProperty(notes = "IP地址")
	private String ip;
	// 主机名称
	@ApiModelProperty(notes = "主机名称")
	private String host;
	// 操作系统
	@ApiModelProperty(notes = "操作系统")
	private String system;
	// 浏览器
	@ApiModelProperty(notes = "浏览器")
	private String browser;
	// 登陆时间
	@ApiModelProperty(notes = "登陆时间")
	private Date loginTime;
	public P_User getUser() {
		return user;
	}
	public void setUser(P_User user) {
		this.user = user;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}

