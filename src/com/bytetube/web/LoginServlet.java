package com.bytetube.web;

import com.bytetube.domain.User;
import com.bytetube.service.UserService;
import com.bytetube.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * //1.获取参数
         *         String username = request.getParameter("username");
         *         String password = request.getParameter("password");
         *         String checkCode = request.getParameter("checkCode");
         *
         *         //2。先验证checkCode
         *         HttpSession session = request.getSession();
         *         String checkCode_session = (String)session.getAttribute("checkCode_session");
         *         session.removeAttribute("checkCode_session");
         *             //如果if通过，说明checkCode是正确的
         *         if (checkCode_session != null && checkCode_session.equalsIgnoreCase(checkCode)) {
         *             if ("dal".equals(username) && "111111".equals(password)) {
         *                 session.setAttribute("user",username);
         *                 response.sendRedirect(request.getContextPath()+"/success.jsp");
         *             }else{
         *                 request.setAttribute("login_error","username or password is wrong");
         *                 request.getRequestDispatcher("/login.jsp").forward(request,response);
         *             }
         *         }else {
         *             request.setAttribute("checkCode_error","checkCode error");
         *             request.getRequestDispatcher("/login.jsp").forward(request,response);
         *         }
         */

        //1.先获取验证码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifycode = request.getParameter("verifycode");

        //2.验证验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//目的：保证验证码的唯一化
        if (checkcode_server != null && checkcode_server.equalsIgnoreCase(verifycode)) {
            //验证码正确
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            UserService service = new UserServiceImpl();
            User loginUser = service.login(user.getUsername(), user.getPassword());
            if (loginUser != null) {
                //登录成功
                session.setAttribute("user",loginUser);
                response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
            }else {
                //登录失败
                request.setAttribute("login_msg","username or password is wrong");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }

        }else {
            //说明验证码错误
            request.setAttribute("login_msg","checkcode error!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }



//       // return;
//        //代码能来到这里，说明验证码是正确的
//        Map<String, String[]> map = request.getParameterMap();
//        User user = new User();
//        try {
//            BeanUtils.populate(user,map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//
//        UserService service = new UserServiceImpl();
//        User loginUser = service.login(user.getUsername(), user.getPassword());
//        if (loginUser != null) {
//            //登录成功
//            session.setAttribute("user",loginUser);
//            response.sendRedirect(request.getContextPath()+"/index.jsp");
//        }else {
//            //登录失败
//            request.setAttribute("login_msg","username or password is wrong");
//            request.getRequestDispatcher("/login.jsp").forward(request,response);
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
