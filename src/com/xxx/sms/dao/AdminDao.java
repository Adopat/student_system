package com.xxx.sms.dao;

import com.xxx.sms.model.Admin;
/**
 * 公众号：知否技术
 */
public interface AdminDao {

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    Admin login(String username, String password);
}
