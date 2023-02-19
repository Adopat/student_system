package com.xxx.sms.controller.student;

import com.xxx.sms.model.Student;
import com.xxx.sms.service.StudentService;
import com.xxx.sms.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Adopat
 */
@WebServlet("/student/saveOrUpdate")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. POST 请求设置编码格式，解决获取中文乱码的问题
        req.setCharacterEncoding("utf-8");
        // 2. 获取浏览器传递的 id 参数
        String id = req.getParameter("id");
        // 3. 新建学生对象
        Student student = new Student();
        // 4. 设置id:如果id不存在，则设置为0。否则就是传递的id
        student.setId(Integer.parseInt(("".equals(id)) ? "0" : req.getParameter("id")));
        // 5. 设置学生姓名
        student.setName(req.getParameter("name"));
        // 6. 设置学生性别
        student.setSex(Integer.parseInt(req.getParameter("sex")));
        // 7. 设置学生年龄
        student.setAge(Integer.parseInt(req.getParameter("age")));
        // 8. 设置学生班级id
        student.setClassId(Integer.parseInt(req.getParameter("classId")));
        StudentService studentService = new StudentServiceImpl();
        boolean flag;
        // 9. 如果 id 为 0,表示是新建学生信息
        if (student.getId() == 0) {
            // 10. 调用service 的新增方法
            flag = studentService.add(student);
        } else {
            // 11. 否则调用service 的编辑方法
            flag = studentService.update(student);
        }
        // 12. 如果新增或者修改成功，则重定向到学生列表页面
        if (flag) {
            resp.sendRedirect(req.getContextPath() + "/student/list");
        }
    }
}
