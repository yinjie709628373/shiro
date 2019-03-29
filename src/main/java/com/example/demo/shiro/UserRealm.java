package com.example.demo.shiro;

import com.example.demo.entity.SysUser;
import com.example.demo.service.ISysPermissionService;
import com.example.demo.service.ISysRoleService;
import com.example.demo.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysPermissionService sysPermissionService;
    @Autowired
    private ISysUserService sysUserService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //获取用户
        Subject subjec= SecurityUtils.getSubject();
        SysUser user=(SysUser)subjec.getPrincipal();
        //获取用户的权限进行操作
        //添加资源授权字符串 通过用户添加的字段授权字符串或者用权限的percode集合
        //info.addStringPermission("");
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //根据输入的查询是否存在该用户
        UsernamePasswordToken  token=(UsernamePasswordToken)authenticationToken;
        SysUser user= sysUserService.findByUsercode(token.getUsername());
        if(null==user){
            System.out.println("拉闸");
            return null;
        }
        System.out.println("准备匹配");
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
