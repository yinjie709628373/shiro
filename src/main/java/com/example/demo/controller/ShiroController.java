package com.example.demo.controller;

import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysRole;
import com.example.demo.service.ISysPermissionService;
import com.example.demo.service.ISysRoleService;
import com.example.demo.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags ="shiro测试")
public class ShiroController {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysPermissionService sysPermissionService;
    @Autowired
    private ISysUserService sysUserService;
    @RequestMapping(value = {"/welcome"},method = RequestMethod.GET)
    @ResponseBody
    public Object welcome() {
        List<SysRole> roles=sysRoleService.findByUserId("zhangsan");
        List<SysPermission> permissions=sysPermissionService.findUrlByUserId("zhangsan");
        System.out.println(roles);
        System.out.println(permissions);
        return sysUserService.findByUserid("zhangsan");
    }
    @RequestMapping(value = {"/add"},method = RequestMethod.GET)
    public Object add() {
        return "add";
    }
    @RequestMapping(value = {"/update"},method = RequestMethod.POST)
    public Object update() {
        return "update";
    }
    @ApiOperation("登录接口（first）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usercode", value = "账号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "String")
    })
    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    public Object login(String usercode,String password) {
        //shiro认证
        Subject subject= SecurityUtils.getSubject();
        //密码需要加密
        UsernamePasswordToken token=new UsernamePasswordToken(usercode,password);
        try {
            //调用login
            subject.login(token);
        }catch (UnknownAccountException e){
            //IncorrectCredentialsException 也可以
            // e.printStackTrace();
            //跳转页面
            return "failed";
        }
        //跳转页面
        return "success";
    }
}
