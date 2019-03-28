package com.example.demo.dao;

import com.example.demo.entity.SysRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper  extends Mapper<SysRole> {
    public List<SysRole> findByUserId(String userid);
}
