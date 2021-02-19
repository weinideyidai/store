package com.fei.store.security.config;

import com.fei.store.security.domain.JwtUser;
import com.fei.store.service.PermissionService;
import com.fei.store.service.RoleService;
import com.fei.store.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Configuration
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        // 获得loadUserByUsername()方法的结果
        JwtUser user = (JwtUser) authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // 遍历用户所有角色
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            Integer roleId = roleService.selectByName(roleName).getId();
            // 得到角色所有的权限
            List<PermissionVO> permissionList = permissionService.listByRoleId(roleId);

            // 遍历permissionList
            for (PermissionVO sysPermission : permissionList) {
                // 如果访问的Url和权限用户符合的话，返回true
                if (targetUrl.equals(sysPermission.getUrl()) && sysPermission.getMname().equals(targetPermission)) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
