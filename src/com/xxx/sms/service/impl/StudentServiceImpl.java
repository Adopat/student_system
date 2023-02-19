package com.xxx.sms.service.impl;

import com.xxx.sms.dao.StudentDao;
import com.xxx.sms.dao.impl.StudentDaoImpl;
import com.xxx.sms.model.Page;
import com.xxx.sms.model.Student;
import com.xxx.sms.service.StudentService;

import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public Page<Student> list(int currentPage, Map<String, Object> paramMap) {
        // list
        List<Student> studentList = studentDao.list(currentPage, paramMap);
        // total
        int total = studentDao.getTotal(paramMap);
        Page<Student> page = new Page<>(currentPage, total, studentList);
        return page;
    }
    @Override
    public boolean delete(int id) {
        return studentDao.delete(id);
    }

    @Override
    public boolean add(Student student) {
        return studentDao.add(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public Student find(int id) {
        return studentDao.
find(id);
    }
}
