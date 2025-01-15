package com.example.webapp.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/admin/*") // Áp dụng filter cho các đường dẫn trong thư mục /admin
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        // Kiểm tra session
        if (session != null) {
            Integer idRole = (Integer) session.getAttribute("idRole");
            if (idRole != null && idRole == 1) {
                // Người dùng là admin, cho phép truy cập
                chain.doFilter(request, response);
                return;
            }
        }

        // Nếu không có quyền, chuyển hướng về trang đăng nhập
        httpResponse.sendRedirect("/login.jsp");
    }

    @Override
    public void destroy() {
        // Hủy filter nếu cần
    }
}
