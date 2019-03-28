package com.example.demo.service.impl;

import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.service.ISysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser getByUsercodeAndPassword(String usercode, String password) {
        //TODO 可进行判断输入的账号密码是否为空
        //md5加密
        password= DigestUtils.md5Hex(password);
        return sysUserMapper.getByUsercodeAndPassword(usercode,password);
    }
}
