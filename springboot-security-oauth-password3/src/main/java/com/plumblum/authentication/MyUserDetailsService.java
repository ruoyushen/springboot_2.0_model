package com.plumblum.authentication;

import com.plumblum.entity.SysPermission;
import com.plumblum.entity.SysRole;
import com.plumblum.entity.SysUser;
import com.plumblum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2019/1/9 16:46
 * @Description:
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    /**
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user =  userService.findByName(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            List<SysPermission> permissions = userService.findPermissionByUserId(user.getId());
            for (SysPermission permission : permissions) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission()));
            }
            List<SysRole> roles = userService.findRoleByUserId(user.getId());
            for (SysRole role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}
