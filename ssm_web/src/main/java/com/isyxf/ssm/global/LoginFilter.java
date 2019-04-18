package com.isyxf.ssm.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String path = request.getServletPath();

        // 如果是登录页面则直接通过
        if (path.toLowerCase().indexOf("login") != -1) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession httpSession = request.getSession();
            Object obj = httpSession.getAttribute("USER");
            // 从session中有获取到，则直接通过
            if (obj != null) {
                filterChain.doFilter(request, response);
            }else {
                // request.getContextPath() // 获取绝对路径
                response.sendRedirect(request.getContextPath() + "/toLogin.do");
            }
        }
    }

    public void destroy() {

    }
}
