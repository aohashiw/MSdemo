package com.aohashi.demo.entity;

import java.util.List;


public class SystemPermission {

    private static final long serialVersionUID = 353629772108330570L;
    private Integer id;
    private String name;
    private List<SystemRole> roles;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SystemRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SystemRole> roles) {
        this.roles = roles;
    }

}

