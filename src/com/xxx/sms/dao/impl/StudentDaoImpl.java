package com.xxx.sms.dao.impl;

import com.xxx.sms.dao.StudentDao;
import com.xxx.sms.model.Admin;
import com.xxx.sms.model.Page;
import com.xxx.sms.model.Student;
import com.xxx.sms.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 公众号：知否技术
 */
public class StudentDaoImpl implements StudentDao {
    Connection conn = null;
    PreparedStatement ps = null;

    @Override
    public List<Student> list(int currentPage, Map<String, Object> paramMap) {
        List<Student> studentList = new ArrayList<>();
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2. 拼接 sql 语句
            String sql = "select s.*,c.`name` as class_name from s_student as s \n" +
                    "LEFT JOIN s_class as c ON c.id=s.class_id";
            // 3最终执行的sql
            StringBuffer resultSql = new StringBuffer(sql);
            resultSql.append(" where 1 = 1");
            // 4查询参数
            if (null != paramMap.get("name") && !"".equals(paramMap.get("name"))) {
                resultSql.append(" and s.name like '%" + paramMap.get("name") + "%'");
            }
            if (null != paramMap.get("classId") && !"".equals(paramMap.get("classId"))) {
                resultSql.append(" and s.class_id =" + paramMap.get("classId"));
            }
            // 5拼接分页查询语句
            resultSql.append(" limit " + (currentPage - 1) * Page.pageSize + "," + Page.pageSize);
            // 6. 创建执行sql的对象
            ps = conn.prepareStatement(resultSql.toString());
            // 7. 执行sql
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setClassId(rs.getInt("class_id"));
                student.setClassName(rs.getString("class_name"));
                student.setAge(rs.getInt("age"));
                student.setSex(rs.getInt("sex"));
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8. 释放资源
            JDBCUtils.close(ps, conn);
        }
        return studentList;
    }

    @Override
    public int getTotal(Map<String, Object> paramMap) {
        int total = 0;
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2. 拼接 sql 语句
            String sql = "select count(*) as total from s_student";
            // 3. 最终执行的sql
            StringBuffer resultSql = new StringBuffer(sql);
            resultSql.append(" where 1 = 1");
            // 4. 查询参数
            if (null != paramMap.get("name") && !"".equals(paramMap.get("name"))) {
                resultSql.append(" and name like '%" + paramMap.get("name") + "%'");
            }
            if (null != paramMap.get("classId") && !"".equals(paramMap.get("classId"))) {
                resultSql.append(" and class_id =" + paramMap.get("classId"));
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

    @Override
    public boolean delete(int id) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2.  sql 语句
            String sql = "delete from s_student where id = ?";
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

    @Override
    public boolean add(Student student) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2.  sql 语句
            String sql = "insert into s_student(class_id,name,sex,age) values(?,?,?,?)";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 给 ？赋值
            ps.setInt(1, student.getClassId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getSex());
            ps.setInt(4, student.getAge());
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

    @Override
    public boolean update(Student student) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2.  sql 语句
            String sql = "update s_student set class_id=?,name = ?,sex=?,age=? where id = ?";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 给 ？赋值
            ps.setInt(1, student.getClassId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getSex());
            ps.setInt(4, student.getAge());
            ps.setInt(5,student.getId());
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

    @Override
    public Student find(int id) {
        try {
            // 1. 获取数据库连接对象
            conn = JDBCUtils.getConnection();
            // 2.  sql 语句
            String sql = "select * from s_student where id = ?";
            // 3. 创建执行sql的对象
            ps = conn.prepareStatement(sql);
            // 4. 给 ？赋值
            ps.setInt(1, id);
            // 5 执行sql
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setClassId(rs.getInt("class_id"));
                student.setAge(rs.getInt("age"));
                student.setSex(rs.getInt("sex"));
                return student;
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
