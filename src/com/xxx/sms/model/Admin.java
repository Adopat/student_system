package com.xxx.sms.model;

/**
 * 管理员实体类
 */
public class Admin {
    // id
    private int id;
    // 账号
    private String username;
    // 密码
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
