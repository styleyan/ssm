package com.isyxf.ssm.service;

import com.isyxf.ssm.entity.Staff;

import java.util.List;

public interface StaffService {
    void add(Staff staff);
    void remove(Integer id);
    void edit(Staff staff);
    Staff get(Integer id);
    List<Staff> getAll();
}
