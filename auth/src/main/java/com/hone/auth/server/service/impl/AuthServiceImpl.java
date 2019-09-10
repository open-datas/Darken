package com.hone.auth.server.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;

import com.hone.auth.server.service.AuthService;
import com.hone.auth.server.util.JwtUtils;
import com.hone.common.encrypt.EncryptUtils;
import com.hone.common.json.CResult;
import com.hone.common.pattern.PatternUtils;
import com.hone.dao.auth.AuthDao;
import com.hone.dao.auth.UserDao;
import com.hone.entity.auth.P_Auth;
import com.hone.entity.auth.P_User;
import com.hone.entity.auth.UserParam;

/**
 * AuthServiceImpl
 *
 * @Author hourz
 * @since 2019-07-12
 */
@Service
public class AuthServiceImpl implements AuthService {
	
	@Resource
	private AuthDao authDao;
	
	@Resource
	private UserDao userDao;
	
	@Autowired
    private ConsumerTokenServices consumerTokenServices;
	
	@Resource
	private StringRedisTemplate redisTemplate;
	
	/**
	 * <p>验证用户</p>
	 */
    @Override
    public CResult<P_User> checkUser(P_Auth auth) {
    	try {
			if(redisTemplate.opsForValue().get(auth.getLoginName()).equals(auth.getTokenId())) {
				// 查询用户信息
				P_User user = userDao.query(Long.valueOf(auth.getUserId()));
				if(user.getUpdateTime() != null) {
					if(isTokenBoforePwdReset(auth.getTokenId(), user.getUpdateTime())) {
						return new CResult<P_User>(true , "200", null, null, null, "用户验证成功！");
					}else {
						return new CResult<P_User>(false , "200", null, null, null, "密码已经修改，请重新登录！");
					}
				} else {
					return new CResult<P_User>(true , "200", null, null, null, "用户验证成功！");
				}
			} else {
				return new CResult<P_User>(false , "200", null, null, null, "用户未登录请重试！");
			}
		} catch (Exception e) {
			return new CResult<P_User>(false , "200", "用户登录验证异常信息: " + e.getMessage());
		}
    }

    @Override
    public CResult<P_User> loginIn(P_Auth auth) {
    	if(auth.getTokenId() != null || auth.getTokenId() != "") {
    		removeToken(auth.getTokenId());
    		// return new CResult<P_User>(false , "200", "用户已登录，请先退出再尝试登录！ ");
    	}
    	P_User user = new P_User();
    	try {
    		// 验证邮箱
    		if(PatternUtils.isEmail(auth.getLoginName())) {
    			user = authDao.loginMail(auth);
    		// 验证手机号
    		} else if(PatternUtils.isMobile(auth.getLoginName())) {
    			user = authDao.loginMobile(auth);
    		// 验证登录名
    		} else {
    			user = authDao.loginName(auth);
    		}
    		if(user != null ) {
    			if(user.getPassword().equals(EncryptUtils.md5Encode(auth.getPassword()))) {
    				// 获取tokenId
    				user.setTokenId(JwtUtils.getInstance().generateToken(user));
    				userDao.updateToken(user);
    				//redisTemplate.
    				return new CResult<P_User>(true, "200", user, null, null, "");
    			}else {
    				return new CResult<P_User>(true, "200", "密码错误，请重新输入！");
    			}
    		} else {
    			return new CResult<P_User>(true, "200", "登录名错误，请重新输入！");
    		}
		} catch (Exception e) {
			return new CResult<P_User>(false, "200", "删除用户异常: " + e.getMessage());
		}
    }

    @Override
    public CResult<P_User> loginOut(P_Auth auth) {
    	Boolean remove = false;
    	try {
    		if(removeToken(auth.getTokenId())) {
    			return new CResult<P_User>(remove, "200", "删除用户tokenId异常！");
    		}
    		// 删除Redis
    		remove = redisTemplate.delete(auth.getLoginName());
    		return new CResult<P_User>(remove, "200", "");
		} catch (Exception e) {
			return new CResult<P_User>(remove, "200", "删除用户异常: " + e.getMessage());
		}
    }
    
    /**
     * 删除TokenId
     * @param tokenId
     * @return
     */
    public Boolean removeToken(String tokenId) {
    	// 删除Token
		consumerTokenServices.revokeToken(tokenId);
    	return false;
    }
    
    /**
     * 刷新TokenId
     * @param tokenId
     * @return
     */
    public Boolean refreshToken(String tokenId) {
    	// 刷新tokenId
    	try {
    		JwtUtils.getInstance().refreshToken(tokenId);
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
    
    /**
     * 刷新TokenId
     * @param tokenId
     * @return
     */
    public Boolean validateToken(String tokenId) {
    	// 刷新tokenId
    	try {
    		JwtUtils.getInstance().validateToken(tokenId);
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
    
    /**
     * 查看Token是否在修改密码之前
     * @param tokenId
     * @return
     */
    public Boolean isTokenBoforePwdReset(String tokenId, Date pwdResDate) {
    	// 刷新tokenId
    	try {
    		JwtUtils.getInstance().isTokenBoforePwdReset(tokenId, pwdResDate);
    		return true;
		} catch (Exception e) {
			return false;
		}
    }

    /**
     * 用户注册
     */
	@Override
	public Long register(UserParam param) {
		param.setCreateTime(new Date());
		userDao.save(param);
		return param.getUserId();
	}
    
}
