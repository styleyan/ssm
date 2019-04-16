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
        request.setAttribute("LIST", list);
        request.getRequestDispatcher("../department_list.jsp").forward(request, response);
    }

    // /department/toAdd.do
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../department_add.jsp").forward(request, response);
    }

    /**
     * 保存数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Department department = new Department();

        department.setName(name);
        department.setAddress(address);

        departmentService.add(department);

        // 回控制器
        response.sendRedirect("list.do");
    }

    /**
     * 编辑
     * /department/toEdit.do
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void toEdit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("../department_edit.jsp").forward(httpServletRequest, httpServletResponse);
    }

    /**
     * 编辑信息
     */
    public void edit(HttpServletRequest httpServletRequest, HttpServletResponse response) throws ServletException, IOException {
        String name = httpServletRequest.getParameter("name");
        String address = httpServletRequest.getParameter("address");
        String id = httpServletRequest.getParameter("id");
        Department department = new Department();

        department.setAddress(address);
        department.setName(name);
        department.setId(Integer.parseInt(id));

        departmentService.edit(department);

        // 回控制器
        response.sendRedirect("list.do");
    }
}
