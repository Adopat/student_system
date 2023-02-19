package com.xxx.sms.service;

import com.xxx.sms.model.Page;
import com.xxx.sms.model.Student;

import java.util.Map;

public interface StudentService {

    Page<Student> list(int currentPage, Map<String, Object> paramMap);

    boolean delete(int id);

    boolean add(Student student);

    boolean update(Student student);

    Student find(int id);
}
