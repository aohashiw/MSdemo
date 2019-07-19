package com.aohashi.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;

public class SystemRole implements Serializable {

    private static final long serialVersionUID = -8687790154329829056L;
    private Integer id;
    private String role;
    @JsonInclude(Include.NON_NULL)
    private List<SystemPermission> permissions;
    @JsonInclude(Include.NON_NULL)

    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<SystemPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SystemPermission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }



}
