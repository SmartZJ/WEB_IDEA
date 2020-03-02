package com.sxtljx.controller;

import com.sxtljx.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码
        request.setCharacterEncoding("utf-8");
        //设置响应编码
        response.setHeader("content-type", "text/html;charset=utf-8");
        //获取请求标识
        String mark = request.getParameter("mark");
        //根据标识选择对应方法
        if ("login".equals(mark)){
            userLogin(request,response);
        }else if("regis".equals(mark)){
            userRegis(request,response);
        }else if("checkuname".equals(mark)){
            checkUname(request,response);
        }
    }



    //登录方法
    private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求中的用户名密码
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        //判断登录信息是否填写完整
        if (uname.isEmpty()||pwd.isEmpty()){
            request.setAttribute("msg", "<font color='red'>登陆信息填写不完整</font>");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }else{
            //登录成功
            if (userDao.userLogin(uname,pwd)){
                //用户名存入session
                request.getSession().setAttribute("uname", uname);
                //请求转发到EmployeeServlet
                request.getRequestDispatcher("employeeServlet?mark=query&curPage=1").forward(request, response);
            }else {
            //登录失败
                request.setAttribute("msg", "<font color='red'>用户名或密码错误</font>");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        }
    }
    //注册方法
    private void userRegis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求中的用户名密码
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String repwd = request.getParameter("repwd");
        //注册信息不完整
        if (uname.isEmpty()||pwd.isEmpty()||repwd.isEmpty()){
            request.setAttribute("msg", "<font color='red'>注册信息填写不完整</font>");
            request.getRequestDispatcher("regis.jsp").forward(request, response);
            return;
        }else {
            //密码不一致
            if (!pwd.equals(repwd)){
                request.setAttribute("msg", "<font color='red'>两次输入密码不一致</font>");
                request.getRequestDispatcher("regis.jsp").forward(request, response);
                return;
            }else{
                //注册信息存入数据库
                userDao.userRegis(uname,pwd);
                //注册成功 跳转到登录界面
                request.setAttribute("msg", "<font color='green'>注册成功，请登录</font>");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        }
    }
    //用户名检测
    private void checkUname(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = request.getParameter("uname");
        PrintWriter pw = response.getWriter();
        pw.print(userDao.valiUname(uname));
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
