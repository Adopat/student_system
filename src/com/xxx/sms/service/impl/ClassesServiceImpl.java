package com.xxx.sms.service.impl;

import com.xxx.sms.dao.ClassesDao;
import com.xxx.sms.dao.impl.ClassesDaoImpl;
import com.xxx.sms.model.Classes;
import com.xxx.sms.service.ClassesService;

import java.util.List;

public class ClassesServiceImpl implements ClassesService {
    private ClassesDao classesDao = new ClassesDaoImpl();

    @Override
    public List<Classes> getClassList() {
        return classesDao.getClassList();
    }
}
