package com.xxx.sms.filter;

import com.xxx.sms.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
/**
 * 公众号：知否技术
 */
@WebFilter(filterName = "accessFilter", urlPatterns = "/*")
public class AccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        // 1. 获取 URI: 例如 /sms/student/list
        String url = request.getRequestURI();
        // 2. 因为 web.xml 里面我们默认配置的欢迎页面是登录页面，而项目进入登录页面的url的末尾是 /sms/
        // 所以这里要排除掉登录、退出、以及静态资源
        if (url.endsWith("/sms/") || url.contains("/login") || url.contains("/logout") || url.contains("/static/")) {
            // 3. 放行
            filterChain.doFilter(request, response);
        } else {
            // 4. 在请求其他资源之前判断admin 有没有登录，如果没有登录则重定向到登录页面
            Admin admin = (Admin) session.getAttribute("admin");
            // 5 .管理员已经登录过
            if (null != admin) {
                // 6. 放行
                filterChain.doFilter(request, response);
            } else {
                // 7. 重定向 login.jsp，请先登录再访问其他资源
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }
}
