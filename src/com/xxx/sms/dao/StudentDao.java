package com.xxx.sms.dao;

import com.xxx.sms.model.Student;

import java.util.List;
import java.util.Map;

/**
 * Adopat
 */
public interface StudentDao {
    /**
     * 分页查询学生列表信息
     *
     * @param currentPage
     * @param paramMap
     * @return
     */
    List<Student> list(int currentPage, Map<String, Object> paramMap);

    /**
     * 获取学生总条数
     *
     * @param paramMap
     * @return
     */
    int getTotal(Map<String, Object> paramMap);

    /**
     * 根据id删除学生信息
     *
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 新增学生信息
     *
     * @param student
     * @return
     */
    boolean add(Student student);

    /**
     * 编辑学生信息
     *
     * @param student
     * @return
     */
    boolean update(Student student);

    /**
     * 查询学生信息
     * @param id
     * @return
     */
    Student find(int id);
}
