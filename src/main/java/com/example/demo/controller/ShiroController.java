package com.example.demo.controller;

import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysRole;
import com.example.demo.service.ISysPermissionService;
import com.example.demo.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@Api(tags ="shiro测试")
public class ShiroController {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysPermissionService sysPermissionService;
    @RequestMapping(value = {"/welcome"},method = RequestMethod.GET)
    @ResponseBody
    public Object welcome() {
        List<SysRole> roles=sysRoleService.findByUserId("zhangsan");
        List<SysPermission> permissions=sysPermissionService.findUrlByUserId("zhangsan");
        System.out.println(roles);
        System.out.println(permissions);
        return permissions;
    }

}
