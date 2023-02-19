package com.xxx.sms.controller.admin;

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
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取 session 数据
        HttpSession session = req.getSession(false);
        if (session == null) {
            return;
        }
        // 2. 清除 session 里面的管理员信息
        session.removeAttribute("admin");
        // 3. 重定向到登录页面
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
