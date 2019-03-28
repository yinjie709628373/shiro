package com.example.demo.service;

import com.example.demo.entity.SysUser;

public interface ISysUserService {
    //通过账号密码查询用户
    SysUser getByUsercodeAndPassword(String usercode,String password);
}
