package com.example.demo.shiro;

import com.example.demo.entity.SysUser;
import com.example.demo.service.ISysPermissionService;
import com.example.demo.service.ISysRoleService;
import com.example.demo.service.ISysUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.security.auth.Subject;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysPermissionService sysPermissionService;
    @Autowired
    private ISysUserService sysUserService;

    @Value("${publicUrl}") //公共访问地址需要认证
    private String publicUrl;
    @Value("${operUrl}") //公开访问地址不需要认证
    private String operUrl;
    /**
     * ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro过滤器
        /**
         * anon:无需认证
         * authc：必须认证
         * user:如果使用rememberme的功能直接访问
         * perms:该资源必须得到资源权限才能访问
         * role：该资源必须得到资源权限才能访问
         */
        Map<String, String > map=new LinkedHashMap<>();
        //公共访问地址
        String[] publicUrls=publicUrl.split(",");
        for (String a :publicUrls){
            map.put(a,"authc");
        }
        //连接数据库后的permission的访问地址
        //获取登录用户
        SysUser user= (SysUser)SecurityUtils.getSubject().getPrincipal();
        //user.getPermissions();
        //设置无需登录的url
        String[] operUrls  =operUrl.split(",");
        for (String c :operUrls){
            map.put(c,"anon");
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //修改到跳转的页面
        //shiroFilterFactoryBean.setLoginUrl("https://www.baidu.com");
       //设置未授权页面
       // shiroFilterFactoryBean.setUnauthorizedUrl();
        return  shiroFilterFactoryBean;
    }
    /**
     * DefaultWebSecurityManager
     */
        @Bean(name="securityManager")
        public DefaultWebSecurityManager getDefaultWebScurityManager(@Qualifier("userRealm") UserRealm userRealm){
            DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
           securityManager.setRealm(userRealm);
            return  securityManager;
        }

    /**
     * Realm
     */
    @Bean(name = "userRealm")
    public  UserRealm getRealm(){
        return  new UserRealm();
    }
}
