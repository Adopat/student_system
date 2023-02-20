package com.xxx.sms.service.impl;

import com.xxx.sms.dao.AdminDao;
import com.xxx.sms.dao.impl.AdminDaoImpl;
import com.xxx.sms.model.Admin;
import com.xxx.sms.service.AdminService;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin login(String username, String password) {
        return adminDao.login(username, password);
    }

    @Override
    public Admin register(String username, String password) {
        return adminDao.register(username, password);
    }
}
