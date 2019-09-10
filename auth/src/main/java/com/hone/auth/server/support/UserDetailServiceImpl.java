package com.hone.auth.server.support;

import com.hone.dao.auth.PermissionDao;
import com.hone.dao.auth.RoleDao;
import com.hone.dao.auth.UserDao;
import com.hone.entity.auth.P_Permission;
import com.hone.entity.auth.P_Role;
import com.hone.entity.auth.P_User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * <p></p>
 * @Author hourz
 * @since 2019-07-16
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        P_User pUser = userDao.queryByUserName(userName);
        if (pUser == null) {
            throw new UsernameNotFoundException(userName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        // 可用性 :true:可用 false:不可用
        boolean enable = true;
        // 过期性 :true:没过期 false:过期
        boolean accountExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountLocked = true;
        List<P_Role> roles = roleDao.listByUserId(pUser.getId());
        for (P_Role role : roles) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorities.add(grantedAuthority);
            List<P_Permission> permissions = permissionDao.listByRoleId(role.getId());
            // 获取权限
            for (P_Permission permission : permissions) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
                grantedAuthorities.add(authority);
            }
        }
        User user = new User(pUser.getUsername(), pUser.getPassword(), 
        		enable, accountExpired, credentialsExpired, accountLocked, grantedAuthorities);
        return user;
    }
}
