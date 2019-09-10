package com.hone.auth.server.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.hone.entity.auth.P_User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * <p> token 操作对象</p>
 * @author hourz
 * @since 2019-07-24
 */
@Component
public class JwtUtils implements Serializable {
	
	private static final long serialVersionUID = 3142888202064177925L;
	
	// 双重锁模式:是饱汉模式的优化,进行双重判断,当已经创建过实例对象后就无需加锁,提高效率.
	private static JwtUtils singleton;
	/**
	 * <p>无参构造器</p>
	 */
	private JwtUtils(){
		
	}
	/**
	 * <p>双重锁模式实现</p>
	 * @return
	 */
	public static JwtUtils getInstance(){
		if(singleton == null){
			synchronized(JwtUtils.class){
				if(singleton == null){
					singleton = new JwtUtils();
				}
			}
		}
		return singleton;
	}
	private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    // 私密
    @Value("${JWT.secret}")
    private String secret;
    
    // 到期时间 分钟
    @Value("${JWT.expiration}")
    private Long expiration;
    
    @Resource
    private StringRedisTemplate redisTemplate;
	
	/**
	 * 根据Token --> 得到用户名
	 * @param token 
	 * @return
	 */
	public String getUsernameFromToken(String token) throws Exception {
		String username;
        final Claims claims = getClaimsFromToken(token);
        username = claims.getSubject();
        return username;
	}

	/**
	 * 根据Token --> 得到创建时间
	 * @param token
	 * @return
	 */
	public Date getCreatedDateFromToken(String token) {
		 Date created;
	     try {
	         final Claims claims = getClaimsFromToken(token);
	         created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
	     } catch (Exception e) {
	         created = null;
	     }
	     return created;
	}
	
	/**
	 * 根据UserDetail --> 得到Token
	 * @param userDetails
	 * @return
	 */
	public String generateToken(P_User user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(CLAIM_KEY_USERNAME, user.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
	
	/**
	 * 根据token --> 验证token过期
	 * @param token
	 * @return
	 */
	public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
	
	/**
	 * 根据Token --> 刷新token
	 * @param token
	 * @return
	 */
	public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
	
	/**
	 * 查看Token --> 是否上次修改密码之前
	 * @param created
	 * @param lastPasswordReset
	 * @return
	 */
	public Boolean isTokenBoforePwdReset(String token, Date pwdReset) {
        final Date tokenTime = getCreatedDateFromToken(token);
        return !isTokenDateBeforeRestPwdDate(tokenTime, pwdReset)
                && !isTokenExpired(token);
    }
	
	/**
	 * 根据Token --> 验证是否唯一登录
	 * @param token
	 * @return
	 */
	public Boolean validateTokenOnly(String token){
        boolean flag = false;
        try {
            String userName = getUsernameFromToken(token);
            String checkToken = redisTemplate.opsForValue().get(userName);
            if((checkToken != null && checkToken.equals(token)) || checkToken == null) {
                flag =  true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
	
	/**
	 * 根据Token --> 得到公证信息
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private Claims getClaimsFromToken(String token) throws Exception{
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
	
	/**
	 * 根据token --> 获取过期时间
	 * @return
	 */
	private Date generateExpirationDate() {
        Date date = new Date(System.currentTimeMillis() + expiration *60* 1000);
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return date;
    }
	
	/**
	 * 根据Token --> 查看是否过期
	 * @param token
	 * @return
	 */
	private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        boolean flag = expiration.before(new Date());
        return flag;
    }
	
	/**
	 * 根据token --> 查看过期时间
	 * @param token
	 * @return
	 */
	private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
	
	/**
	 * 根据token --> 一个紧凑的url安全JWT字符串
	 * @param claims
	 * @return
	 */
	private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
	
	/**
	 * 查看密码 --> 是否上次token创建之前
	 * @param created
	 * @param lastPasswordReset
	 * @return
	 */
	private Boolean isTokenDateBeforeRestPwdDate(Date tokenTime, Date restPwdDate) {
        boolean flag = (restPwdDate != null && tokenTime.before(restPwdDate));
        return flag;
    }
}

