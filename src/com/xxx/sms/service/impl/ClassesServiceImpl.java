package com.xxx.sms.service.impl;

import com.xxx.sms.dao.ClassesDao;
import com.xxx.sms.dao.impl.ClassesDaoImpl;
import com.xxx.sms.model.Classes;
import com.xxx.sms.model.Page;
import com.xxx.sms.model.Student;
import com.xxx.sms.service.ClassesService;

import java.util.List;
import java.util.Map;

public class ClassesServiceImpl implements ClassesService {
    private ClassesDao classesDao = new ClassesDaoImpl();

    @Override
    public Page<Classes> list(int currentPage, Map<String, Object> paramMap) {
        // list
        List<Classes> studentList = classesDao.list(currentPage, paramMap);
        // total
        int total = classesDao.getTotal(paramMap);
        Page<Classes> page = new Page<>(currentPage, total, studentList);
        return page;
    }

    @Override
    public List<Classes> getClassList() {
        return classesDao.getClassList();
    }

    @Override
    public boolean delete(int id) {
        return classesDao.delete(id);
    }

    @Override
    public boolean add(Classes classes) {
        return classesDao.add(classes);
    }

    @Override
    public boolean update(Classes classes) {
        return classesDao.update(classes);
    }

    @Override
    public Classes find(int id) {
        return classesDao.find(id);
    }
}
