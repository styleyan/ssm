package com.isyxf.ssm.service.impl;

import com.isyxf.ssm.dao.SelfDao;
import com.isyxf.ssm.dao.StaffDao;
import com.isyxf.ssm.entity.Staff;
import com.isyxf.ssm.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("selfService")
public class SelfServiceImpl implements SelfService {

    @Autowired
    private SelfDao selfDao;
    @Autowired
    private StaffDao staffDao;

    public Staff login(String account, String password) {
        Staff staff = selfDao.selectByAccount(account);

        if (staff != null) {
            return staff;
        }

        return null;
    }

    public void changePassword(Integer id, String password) {
        Staff staff = staffDao.selectById(id);
        staff.setPassword(password);

        staffDao.update(staff);
    }
}
