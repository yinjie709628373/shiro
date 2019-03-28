package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper   extends Mapper<SysUser> {
    SysUser getByUsercodeAndPassword(String usercode, String password);
}
