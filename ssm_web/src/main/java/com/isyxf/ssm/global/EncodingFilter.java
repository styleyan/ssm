package com.isyxf.ssm.global;

import javax.servlet.*;
import java.io.IOException;

/**
 * 编码过滤器
 */
public class EncodingFilter implements Filter {

    private String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        String type = filterConfig.getInitParameter("ENCODING");

        encoding = type != null ? type : "UTF-8";
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        encoding = null;
    }
}
