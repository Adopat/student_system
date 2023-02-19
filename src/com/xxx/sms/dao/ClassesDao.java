package com.xxx.sms.dao;

import com.xxx.sms.model.Classes;

import java.util.List;
/**
 * 公众号：知否技术
 */
public interface ClassesDao {

    /**
     * 获取所有班级信息
     * @return
     */
    List<Classes> getClassList();
}
