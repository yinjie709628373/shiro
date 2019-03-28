package com.example.demo.entity;

import javax.management.relation.Role;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class SysUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String usercode;

    private String username;

    private String password;

    private String salt;

    private String locked;
    // 角色
    private List<SysRole> roles;
    //权限
    private List<SysPermission> permissions;
    public  void setRoles(List<SysRole> roles){
        this.roles=roles;
    }
    public void setPermissions(List<SysPermission> permissions){
        this.permissions=permissions;
    }
    public  List<SysPermission> getPermissions(){
        return this.permissions;
    }
    public  List<SysRole> getRoles(){
        return this.roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked == null ? null : locked.trim();
    }
}
