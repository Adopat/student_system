package com.xxx.sms.service;

import com.xxx.sms.model.Admin;

public interface AdminService {

    Admin login(String username, String password);
    Admin register(String username,String password);
}
