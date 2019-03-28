package com.example.demo.service;

import com.example.demo.entity.SysPermission;

import java.util.List;

public interface ISysPermissionService {
    public List<SysPermission> findUrlByUserId(String userid);
}
