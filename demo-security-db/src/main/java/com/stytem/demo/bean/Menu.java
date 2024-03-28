package com.stytem.demo.bean;

import java.io.Serializable;
import java.util.List;

public class Menu  implements Serializable {

    /**
     *Menu id
     */
    private Integer id;
    /**
     *Menu 匹配正则表达式
     */
    private String pattern;

    /**
     * Menu 对应的角色
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

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pattern='" + pattern + '\'' +
                ", roles=" + roles +
                '}';
    }
}