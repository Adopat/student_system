package com.xxx.sms.dao.impl;

import com.xxx.sms.dao.ClassesDao;
import com.xxx.sms.model.Classes;
import com.xxx.sms.model.Page;
import com.xxx.sms.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Adopat
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

    /**
     * 删除班级
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2.  sql 语句
            String sql = "delete from s_class where id = ?";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 给 ？赋值
            ps.setInt(1, id);
            // 5 执行sql
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            JDBCUtils.close(ps, conn);
        }
        return false;
    }

    /**
     * 增加班级
     * @param classes
     * @return
     */
    @Override
    public boolean add(Classes classes) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2.  sql 语句
            String sql = "insert into s_class(id,name) values(?,?)";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 给 ？赋值
            ps.setInt(1, classes.getId());
            ps.setString(2, classes.getName());
            // 5 执行sql
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            JDBCUtils.close(ps, conn);
        }
        return false;
    }

    /**
     * 更新班级
     * @param classes
     * @return
     */
    @Override
    public boolean update(Classes classes) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2.  sql 语句
            String sql = "update s_class set name=? where id = ?";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 给 ？赋值
            ps.setString(1, classes.getName());
            ps.setInt(2, classes.getId());
            // 5 执行sql
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            JDBCUtils.close(ps, conn);
        }
        return false;
    }

    /**
     * 查找班级
     * @param id
     * @return
     */
    @Override
    public Classes find(int id) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2.  sql 语句
            String sql = "select * from s_class where id = ?";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 给 ？赋值
            ps.setInt(1, id);
            // 5 执行sql
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Classes classes1 = new Classes();
                classes1.setId(rs.getInt("id"));
                classes1.setName(rs.getString("name"));

                return classes1;
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

    @Override
    public List<Classes> list(int currentPage, Map<String, Object> paramMap) {
        List<Classes> classesList = new ArrayList<>();
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2. 拼接 sql 语句
            String sql = "select * from s_class";
            // 3最终执行的sql
            StringBuffer resultSql = new StringBuffer(sql);
            resultSql.append(" where 1 = 1");
            // 4查询参数
            if (null != paramMap.get("name") && !"".equals(paramMap.get("name"))) {
                resultSql.append(" and name like '%" + paramMap.get("name") + "%'");
            }
            if (null != paramMap.get("id") && !"".equals(paramMap.get("id"))) {
                resultSql.append(" and id =" + paramMap.get("id"));
            }
            // 5拼接分页查询语句
            resultSql.append(" limit " + (currentPage - 1) * Page.pageSize + "," + Page.pageSize);
            // 6. 创建执行sql的对象
            ps = conn.prepareStatement(resultSql.toString());
            // 7. 执行sql
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classes classes = new Classes();
                classes.setId(rs.getInt("id"));
                classes.setName(rs.getString("name"));
                classesList.add(classes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8. 释放资源
            JDBCUtils.close(ps, conn);
        }
        return classesList;
    }

    @Override
    public int getTotal(Map<String, Object> paramMap) {
        int total = 0;
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2. 拼接 sql 语句
            String sql = "select count(*) as total from s_class";
            // 3. 最终执行的sql
            StringBuffer resultSql = new StringBuffer(sql);
            resultSql.append(" where 1 = 1");
            // 4. 查询参数
            if (null != paramMap.get("name") && !"".equals(paramMap.get("name"))) {
                resultSql.append(" and name like '%" + paramMap.get("name") + "%'");
            }
            if (null != paramMap.get("id") && !"".equals(paramMap.get("id"))) {
                resultSql.append(" and id =" + paramMap.get("id"));
            }
            // 5. 创建执行sql的对象
            ps = conn.prepareStatement(resultSql.toString());
            // 6. 执行sql
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 释放资源
            JDBCUtils.close(ps, conn);
        }
        return total;
    }
}
