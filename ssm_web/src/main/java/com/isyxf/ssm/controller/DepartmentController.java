package com.isyxf.ssm.controller;

import com.isyxf.ssm.entity.Department;
import com.isyxf.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller("departmentController")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    // /department/list.do
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> list = departmentService.getAll();
//        List list = new ArrayList();

        request.setAttribute("LIST", list);
        request.getRequestDispatcher("../department_list.jsp").forward(request, response);
    }
}
