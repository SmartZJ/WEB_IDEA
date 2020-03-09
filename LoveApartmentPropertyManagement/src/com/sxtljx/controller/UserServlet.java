package com.sxtljx.controller;

import com.sxtljx.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {
    UserDao userDao = new UserDao();
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
        }else if("vailcode".equals(mark)){
            getvailcode(request,response);
        }
    }

    private void getvailcode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String valicode = request.getParameter("vailcode");
//        System.out.println(valicode);
        PrintWriter pw = response.getWriter();
        if (valicode.equalsIgnoreCase(String.valueOf(request.getSession().getAttribute("rand")))){
            pw.print("true");
        }else {
            pw.print("false");
        }
    }

    /**
     * 登录方法
     */
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
                //响应
                PrintWriter pw = response.getWriter();
                pw.print("true");
            }else {
                //登录失败
                request.setAttribute("msg", "<font color='red'>用户名或密码错误</font>");
                PrintWriter pw = response.getWriter();
                pw.print("false");
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
