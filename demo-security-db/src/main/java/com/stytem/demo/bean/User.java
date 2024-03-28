package com.stytem.demo.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    /**
     *id
     */
    private Integer id;
    /**
     *用户名
     */
    private String username;
    /**
     *密码
     */
    private String password;
    /**
     *是否禁用
     */
    private Boolean enabled;
    /**
     *是否锁定
     */
    private Boolean locked;

    /**
     * 用户对应的角色
     */
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    //账户是否没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否没有被锁定
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    //账户证书是否没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账户是否禁用
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorList=new ArrayList<>();
        for (Role role:roles) {
            authorList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}