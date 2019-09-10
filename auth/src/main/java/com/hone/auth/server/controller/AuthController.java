package com.hone.auth.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hone.auth.server.service.AuthService;
import com.hone.common.json.CResult;
import com.hone.entity.auth.P_Auth;
import com.hone.entity.auth.P_User;
import com.hone.entity.auth.UserParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * <p>权限验证</p>
 * @Author hourz
 * @since 2019-07-12
 */
@RestController
@RequestMapping
@Api(description = "登录用户-验证-API接口")
public class AuthController {

    public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    //
    @Autowired
    private AuthService authService;
    //
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "登录验证")
    @RequestMapping(value = "/auth/check",method = RequestMethod.POST)
    public CResult<P_User> checkUser(@RequestBody(required = false) @ApiParam(name="P_Auth",value="验证信息") P_Auth auth){
        if(auth.getUserId()==null || "".equals(auth.getUserId())) {
        	String userId = request.getHeader("user_id");
        	if(userId == null && "".equals(userId)) {
        		return new CResult<P_User>(false, "200", "不存在用户登录，请登录以后再退出！");
        	} else {
        		auth.setUserId(userId);
        	}
        }
        CResult<P_User> result = authService.checkUser(auth);
        return result;
    }

    @ApiOperation(value = "登录用户", notes ="用户名、证件号、手机号、姓名、邮箱" )
    @RequestMapping(value = "/auth/login",method = RequestMethod.POST)
    public CResult<P_User> loginIn(@RequestBody(required = false) @ApiParam(name="P_Auth",value="验证信息") P_Auth auth) {
        String userId = request.getHeader("user_id");
        if(userId != null && "".equals(userId)) {
            return new CResult<P_User>(false, "400", "已存在用户登录，请先退出再登录！");
        }
        CResult<P_User> result = authService.loginIn(auth);
        return result;
    }

    @ApiOperation(value = "退出用户", notes ="用户名、邮箱、手机号" )
    @RequestMapping(value = "/auth/out",method = RequestMethod.POST)
    public CResult<P_User> loginOut(@RequestBody(required = false) @ApiParam(name="P_Auth",value="验证信息") P_Auth auth) {
        String userId = request.getHeader("user_id");
        if(userId == null && "".equals(userId)) {
            return new CResult<P_User>(false, "400", "不存在用户登录，请登录以后再退出！");
        }
        CResult<P_User> result = authService.loginOut(auth);
        return result;
    }

    @ApiOperation(value = "用户注册", notes ="用户名、邮箱、手机号" )
    @RequestMapping(value = "/auth/register",method = RequestMethod.POST)
    public CResult<P_User> register(@RequestBody(required = false) @ApiParam(name="UserParam",value="注册信息") UserParam param) {
        Long id = authService.register(param);
		return new CResult<P_User>(true,"200", null, null , id, "用户注册成功！");
    }
}
