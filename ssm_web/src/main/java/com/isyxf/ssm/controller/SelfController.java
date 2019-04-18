package com.isyxf.ssm.controller;

import com.isyxf.ssm.entity.Staff;
import com.isyxf.ssm.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("selfController")
public class SelfController {
    @Autowired
    SelfService selfService;

    // /toLogin.do
    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    // /login.do
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        Staff staff = selfService.login(account, password);

        if (staff == null) {
            response.sendRedirect("toLogin.do");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("USER", staff);
        response.sendRedirect("main.do");
    }

    // logout.do
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("USER", null);
        response.sendRedirect("toLogin.do");
    }

    // /main.do
    public void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
