package com.isyxf.ssm.service.impl;

import com.isyxf.ssm.dao.DepartmentDao;
import com.isyxf.ssm.entity.Department;
import com.isyxf.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void remove(Integer id) {
        departmentDao.delete(id);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public Department get(Integer id) {
        return departmentDao.selectById(id);
    }

    public List<Department> getAll() {
        List<Department> list = new ArrayList<Department>();
        try{
            list = departmentDao.selectAll();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
