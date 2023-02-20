package com.xxx.sms.dao;

import com.xxx.sms.model.Admin;
/**
 * Adopat
 */
public interface AdminDao {

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    Admin login(String username, String password);

    /**
     * 注册用户
     * @param username
     * @param password
     * @return
     */
    Admin register(String username, String password);
}
