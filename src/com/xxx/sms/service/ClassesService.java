package com.xxx.sms.service;

import com.xxx.sms.model.Classes;
import com.xxx.sms.model.Page;
import com.xxx.sms.model.Student;

import java.util.List;
import java.util.Map;

public interface ClassesService {
    Page<Classes> list(int currentPage, Map<String, Object> paramMap);
    List<Classes> getClassList();

    boolean delete(int parseInt);
    boolean add(Classes classes);
    boolean update(Classes classes);

    Classes find(int id);
}
