package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //用户USER
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class User  extends BaseModel{

    private String name;

    private String cnname;

    private String password;

    private String email;

    private String phone;

    private String status;

    private Set<Role> roles = new HashSet<Role>();

    private List<String> roleList = new ArrayList<String>();

    private String roleIds;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
