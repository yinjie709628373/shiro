package com.example.demo.service.impl;

import com.example.demo.dao.SysPermissionMapper;
import com.example.demo.entity.SysPermission;
import com.example.demo.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysPermissionServicImpl implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Override
    public List<SysPermission> findUrlByUserId(String userid) {
        return sysPermissionMapper.findUrlByUserId(userid);
    }

}
