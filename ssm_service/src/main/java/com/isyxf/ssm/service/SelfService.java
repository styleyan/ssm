package com.isyxf.ssm.service;

import com.isyxf.ssm.entity.Staff;

public interface SelfService {
    Staff login(String account, String password);
    void changePassword(Integer id, String password);
}
