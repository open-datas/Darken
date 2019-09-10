package com.hone.auth.server.service;

import com.hone.common.json.CResult;
import com.hone.entity.auth.P_Auth;
import com.hone.entity.auth.P_User;
import com.hone.entity.auth.UserParam;

/**
 * AuthServcie
 *
 * @Author hourz
 * @since 2019-07-12
 */
public interface AuthService {
	/**
	 * 验证登录
	 * @param auth
	 * @return
	 */
    CResult<P_User> checkUser(P_Auth auth);

    /**
     * 登录
     * @param auth
     * @return
     */
    CResult<P_User> loginIn(P_Auth auth);

    /**
     * 退出
     * @param auth
     * @return
     */
    CResult<P_User> loginOut(P_Auth auth);

    /**
     * 注册
     * @param user
     * @return
     */
	Long register(UserParam param);
}
