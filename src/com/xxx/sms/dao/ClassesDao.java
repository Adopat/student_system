package com.xxx.sms.dao;

import com.xxx.sms.model.Classes;
import com.xxx.sms.model.Page;

import java.util.List;
import java.util.Map;

/**
 * Adopat
 */
public interface ClassesDao {

    /**
     * 获取所有班级信息
     * @return
     */
    List<Classes> getClassList();

    /**
     * 班级删除方法
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 班级更新方法
     * @param classes
     * @return
     */
    boolean add(Classes classes);

    /**
     * 班级更新方法
     * @param classes
     * @return
     */
    boolean update(Classes classes);

    /**
     * 班级查找方法
     * @param id
     * @return
     */
    Classes find(int id);

    /**
     * 获取班级总条数
     * @param paramMap
     * @return
     */
    int getTotal(Map<String, Object> paramMap);

    /**
     * 分页查询班级信息
     * @param currentPage
     * @param paramMap
     * @return
     */
    List<Classes> list(int currentPage, Map<String, Object> paramMap);
}
