package com.xxx.sms.controller.student;

import com.xxx.sms.service.StudentService;
import com.xxx.sms.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Adopat
 */
@WebServlet("/student/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取 id
        String id = req.getParameter("id");
        StudentService studentService = new StudentServiceImpl();
        // 2. 调用 studentService 的 delete 方法，删除学生信息
        boolean delResult = studentService.delete(Integer.parseInt(id));
        // 3. 删除成功，重定向到学生列表页面
        if (delResult) {
            resp.sendRedirect(req.getContextPath()+"/student/list");
        }
    }
}
