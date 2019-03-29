package com.example.demo.service.impl;

import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.service.ISysPermissionService;
import com.example.demo.service.ISysRoleService;
import com.example.demo.service.ISysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysPermissionService sysPermissionService;
    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public SysUser getByUsercodeAndPassword(String usercode, String password) {
        //TODO 可进行判断输入的账号密码是否为空
        //md5加密
        password = DigestUtils.md5Hex(password);
        return sysUserMapper.getByUsercodeAndPassword(usercode, password);
    }

    @Override
    public SysUser findByUserid(String userid) {
        try {
            SysUser user = sysUserMapper.findByUserid(userid);
            if (null == user) {
                user.setPermissions(sysPermissionService.findUrlByUserId(userid));
                user.setRoles(sysRoleService.findByUserId(userid));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SysUser findByUsercode(String usercode) {
        return sysUserMapper.findByUsercode(usercode);
    }
}
