package com.example.demo.service.impl;

import com.example.demo.dao.SysRoleMapper;
import com.example.demo.entity.SysRole;
import com.example.demo.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public List<SysRole> findByUserId(String userid) {
        return sysRoleMapper.findByUserId(userid);
    }
}
