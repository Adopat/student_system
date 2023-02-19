package com.xxx.sms.dao;

import com.xxx.sms.model.Classes;

import java.util.List;
/**
 * Adopat
 */
public interface ClassesDao {

    /**
     * 获取所有班级信息
     * @return
     */
    List<Classes> getClassList();
}
