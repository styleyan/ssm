package com.isyxf.ssm.controller;

import com.isyxf.ssm.entity.Department;
import com.isyxf.ssm.entity.Staff;
import com.isyxf.ssm.service.DepartmentService;
import com.isyxf.ssm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("staffController")
public class StaffController {
    @Autowired
    private StaffService staffService;

    // /staff/list.do
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> list = staffService.getAll();
        request.setAttribute("LIST", list);
        request.getRequestDispatcher("../staff_list.jsp").forward(request, response);
    }

    // /staff/toAdd.do
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../staff_add.jsp").forward(request, response);
    }

    /**
     * 保存数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idNumber = request.getParameter("idNumber");
        String info = request.getParameter("info");
        Date bornDate = null;

        try {
            bornDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer did = Integer.parseInt(request.getParameter("did"));

        Staff staff = new Staff();

        staff.setAccount(account);
        staff.setName(name);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setInfo(info);
        staff.setBornDate(bornDate);
        staff.setDid(did);

        staffService.add(staff);
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
        Integer id = Integer.parseInt(httpServletRequest.getParameter("id"));
        Staff staff = staffService.get(id);
        httpServletRequest.setAttribute("OBJ", staff);
        httpServletRequest.getRequestDispatcher("../staff_edit.jsp").forward(httpServletRequest, httpServletResponse);
    }

    /**
     * 编辑信息
     */
    public void edit(HttpServletRequest httpServletRequest, HttpServletResponse response) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        String account = httpServletRequest.getParameter("account");
        String name = httpServletRequest.getParameter("name");
        String sex = httpServletRequest.getParameter("sex");
        String idNumber = httpServletRequest.getParameter("idNumber");
        String info = httpServletRequest.getParameter("info");
        Date bornDate = null;


        try {
            bornDate = new SimpleDateFormat("yyyy-MM-dd").parse(httpServletRequest.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer did = Integer.parseInt(httpServletRequest.getParameter("did"));

        Staff staff = staffService.get(Integer.parseInt(id));
        staff.setInfo(info);
        staff.setBornDate(bornDate);
        staff.setAccount(account);
        staff.setName(name);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setDid(did);

        staffService.edit(staff);
        // 回控制器
        response.sendRedirect("list.do");
    }

    /**
     * 删除
     */
    public void remove(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Integer id = Integer.parseInt(httpServletRequest.getParameter("id"));
        staffService.remove(id);

        httpServletResponse.sendRedirect("list.do");
    }
}
