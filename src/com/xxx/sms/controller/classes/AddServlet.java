package com.xxx.sms.controller.classes;

import com.xxx.sms.dao.impl.ClassesDaoImpl;
import com.xxx.sms.model.Classes;
import com.xxx.sms.service.impl.ClassesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/classes/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. POST 请求设置编码格式，解决获取中文乱码的问题
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Classes classes = new Classes();
        classes.setId(id);
        classes.setName(name);
        ClassesServiceImpl classesService = new ClassesServiceImpl();
        boolean addResult = classesService.add(classes);
        // 3. 删除成功，重定向到学生列表页面
        if (addResult) {
            resp.sendRedirect(req.getContextPath()+"/classes/list");
        }
    }
}
