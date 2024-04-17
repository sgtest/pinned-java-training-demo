package com.lz.demo.dto;

import java.util.Date;

/**
 * @author lizhi
 * @create 2023-11-06
 **/
public class User {

    private int id;
    private String username;
    private String address;
    private Date birthday;

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
