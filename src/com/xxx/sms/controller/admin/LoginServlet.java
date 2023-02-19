package com.xxx.sms.controller.admin;

import com.xxx.sms.model.Admin;
import com.xxx.sms.service.AdminService;
import com.xxx.sms.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 公众号：知否技术
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取浏览器传递的 username 参数
        String username = request.getParameter("username");
        // 2. 获取浏览器传递的 password 参数
        String password = request.getParameter("password");
        AdminService adminService = new AdminServiceImpl();
        // 3. 调用 service 层的登录方法，根据账号和密码获取管理员信息
        Admin admin = adminService.login(username, password);
        // 4. 如果管理员存在
        if (null != admin) {
            HttpSession session = request.getSession();
            // 5. 将管理员信息存放到 session 里面
            session.setAttribute("admin", admin);
            // 6. 重定向到用户管理的页面。注：这里 request.getContextPath() 是获取项目的根路径
            response.sendRedirect(request.getContextPath()+"/student/list");
        } else {
            // 7. 如果不存在，就将错误信息放到 request 域里面，然后请求转发到登录页面
            request.setAttribute("error","账号或者密码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
