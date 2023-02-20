package com.xxx.sms.dao.impl;

import com.xxx.sms.dao.AdminDao;
import com.xxx.sms.model.Admin;
import com.xxx.sms.util.CommonUtil;
import com.xxx.sms.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Adopat
 */
public class AdminDaoImpl implements AdminDao {
    Connection conn = null;
    PreparedStatement ps = null;

    @Override
    public Admin login(String username, String password) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2. sql 语句
            String sql = "select * from s_admin where username=? and password=?";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 给 ？赋值
            ps.setString(1, username);
            // MD5加密
            ps.setString(2,  CommonUtil.MD5(password));
            // 5. 执行sql
            ResultSet rs = ps.executeQuery();
            // 6. 如果查询出数据，则返回该条数据
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                return admin;
            // 7. 否则返回空
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8. 释放资源
            JDBCUtils.close(ps, conn);
        }
        return null;
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public Admin register(String username, String password) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2.  sql 语句
            String sql = "insert into s_admin(username, password) values (?,?)";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 给 ？赋值
            ps.setString(1, username);
            ps.setString(2, CommonUtil.MD5(password));
            // 5 执行sql
            int count = ps.executeUpdate();
            if (count > 0) {
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setPassword(password);
                return admin;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            JDBCUtils.close(ps, conn);
        }
        return null;
    }
}
