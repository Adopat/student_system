package com.xxx.sms.controller.classes;

import com.xxx.sms.model.Classes;
import com.xxx.sms.model.Page;
import com.xxx.sms.model.Student;
import com.xxx.sms.service.ClassesService;
import com.xxx.sms.service.StudentService;
import com.xxx.sms.service.impl.ClassesServiceImpl;
import com.xxx.sms.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/classes/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取当前页
        String current = req.getParameter("currentPage");
        // 2. 默认为第一页
        int currentPage = (null != current && current.length() > 0) ? Integer.parseInt(current) : 1;
        // 3. 封装查询参数：姓名和班级id
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",req.getParameter("id"));
        paramMap.put("name",req.getParameter("name"));
        ClassesServiceImpl classesService1 = new ClassesServiceImpl();

        // 4. 调用service层的list方法
        Page<Classes> page = classesService1.list(currentPage, paramMap);
        ClassesService classesService = new ClassesServiceImpl();
        // 5. 获取所有班级信息
        List<Classes> classList = classesService.getClassList();
        // 6. 将数据全部存放到 request 域里面
        req.setAttribute("page", page);
        req.setAttribute("classList", classList);
        req.setAttribute("paramMap", paramMap);
        // 7. 请求转发到学生管理的 jsp 页面
        req.getRequestDispatcher("/WEB-INF/view/classes/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
