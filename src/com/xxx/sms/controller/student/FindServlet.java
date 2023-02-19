package com.xxx.sms.controller.student;

import com.alibaba.fastjson.JSON;
import com.xxx.sms.model.Student;
import com.xxx.sms.service.StudentService;
import com.xxx.sms.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/student/find")
public class FindServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService studentService = new StudentServiceImpl();
        // 1. 获取浏览器传递的 id 信息
        int id = Integer.parseInt(req.getParameter("id"));
        // 2. 调用 studentService 的find 方法，获取学生信息
        Student student = studentService.find(id);
        // 3. 设置响应对象的 contentType 为 json格式
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        // 4. 将数据写入到输出流
        writer.println(JSON.toJSONString(student));
        writer.flush();
        writer.close();
    }
}
