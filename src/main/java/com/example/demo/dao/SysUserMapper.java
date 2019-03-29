package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper   extends Mapper<SysUser> {
    SysUser getByUsercodeAndPassword(@Param("usercode") String usercode, @Param("password")String password);

    SysUser findByUserid( @Param("userid")String userid);

    SysUser findByUsercode(@Param("usercode") String usercode);
}
