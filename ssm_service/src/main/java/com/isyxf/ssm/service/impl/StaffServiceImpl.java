package com.isyxf.ssm.service.impl;

import com.isyxf.ssm.dao.StaffDao;
import com.isyxf.ssm.entity.Staff;
import com.isyxf.ssm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

    public void add(Staff staff) {
        staff.setPassword("123");
        staff.setWorkTime(new Date());
        staff.setStatus("正常");

        staffDao.insert(staff);
    }

    public void remove(Integer id) {
        staffDao.delete(id);
    }

    public void edit(Staff staff) {
        staffDao.update(staff);
    }

    public Staff get(Integer id) {
        return staffDao.selectById(id);
    }

    public List<Staff> getAll() {
        return  staffDao.selectAll();
    }
}
