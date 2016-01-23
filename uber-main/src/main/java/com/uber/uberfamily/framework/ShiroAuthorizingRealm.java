package com.uber.uberfamily.framework;

import com.uber.uberfamily.model.Permission;
import com.uber.uberfamily.model.Role;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.RoleService;
import com.uber.uberfamily.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.framework
 * @Description //TODO
 * @Date 16/1/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class ShiroAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (null == principals) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        String username = (String) getAvailablePrincipal(principals);
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new UnknownAccountException("User not exist！");
        }
        if (user.getStatus().equals("0")) {
            throw new DisabledAccountException("User disabled");
        }
        Set<Role> userRole = roleService.getRoleSetByUserId(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(getRoleNames(userRole));
        info.setStringPermissions(getPermission(userRole));
        return null;
    }

    private Set<String> getPermission(Set<Role> userRole) {
        Set<String> results = new HashSet<String>();
        for (Role r : userRole) {
            Set<Permission> Permissions = r.getPermissionSet();
            for (Permission p : Permissions) {
                results.add(p.getCode());
            }
        }
        return results;
    }

    private Set<String> getRoleNames(Set<Role> roles) {
        Set<String> results = new HashSet<String>();
        for (Role r : roles) {
            results.add(r.getName());
        }
        return results;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        if (StringUtils.isEmpty(userName)) {
            throw new AccountException("User name is empty");
        }
        User user = userService.getUserByName(userName);
        if (null == user) {
            throw new UnknownAccountException("User not exist！");
        }
        if (user.getStatus().equals("0")) {
            throw new DisabledAccountException("User disabled");
        }
        return new SimpleAuthenticationInfo(
                user.getName(), user.getPassword(), getName());
    }
}
