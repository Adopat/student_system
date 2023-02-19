package com.xxx.sms.dao.impl;

import com.xxx.sms.dao.ClassesDao;
import com.xxx.sms.model.Classes;
import com.xxx.sms.model.Page;
import com.xxx.sms.model.Student;
import com.xxx.sms.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * 公众号：知否技术
 */
public class ClassesDaoImpl implements ClassesDao {
    Connection conn = null;
    PreparedStatement ps = null;

    @Override
    public List<Classes> getClassList() {
        List<Classes> classList = new ArrayList<>();
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2. sql 语句
            String sql = "select * from s_class";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 执行sql
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classes classes = new Classes();
                classes.setId(rs.getInt("id"));
                classes.setName(rs.getString("name"));
                classList.add(classes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 释放资源
            JDBCUtils.close(ps, conn);
        }
        return classList;
    }
}
