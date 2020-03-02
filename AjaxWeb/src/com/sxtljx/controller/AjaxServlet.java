package com.sxtljx.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ajaxServlet")
public class AjaxServlet extends HttpServlet {
    private HashMap<String,String> map = new HashMap<>();
    private ArrayList<String> list = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=utf-8");
//        String uname = request.getParameter("uname");
//        System.out.println("uname:"+uname);
        PrintWriter pw  = response.getWriter();
//        if (!"".equals(uname)){
//            if ( map.containsKey(uname)){
//                pw.print("false");
//            }else if(!uname.equals("")){
//                pw.print("true");
//            }
//        }
        String alist = "";
        for (int i = 0; i < list.size() ; i++) {
            if (i==0){
                alist+="[\""+list.get(i)+"\",";
            }else if (i==list.size()-1){
                alist+="\""+list.get(i)+"\"]";
            }else {
                alist+="\""+list.get(i)+"\",";
            }

        }
        System.out.println(alist);
        pw.print(alist);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        map.put("lisi","123");
        list.add("易烊千玺参加军训");
        list.add("贵州茅台股价新高");
        list.add("孟晚舟被捕画面");
        list.add("荷兰弟取关迪士尼");
    }
}
