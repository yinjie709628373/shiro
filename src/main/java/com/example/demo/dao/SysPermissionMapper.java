package com.example.demo.dao;

import com.example.demo.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysPermissionMapper extends Mapper<SysPermission> {
    List<SysPermission> findUrlByUserId(@Param("userid") String userid);
}
