package com.bytetube.web;

import com.bytetube.domain.PageBean;
import com.bytetube.domain.User;
import com.bytetube.service.UserService;
import com.bytetube.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        //1.1获取页面参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if (currentPage == null|| "".equals(currentPage)) {
            currentPage = "1";
        }

        if (rows == null||"".equals(rows)) {
            rows ="5";
        }

        Map<String, String[]> conditions = request.getParameterMap();
        //2.向后台请求数据

        UserService service  = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage, rows, conditions);
        //3.将装载好的数据存入域中，供前端调用
        request.setAttribute("pb",pb);
        request.setAttribute("condition",conditions);

        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
