package com.sxtljx.controller;

import com.google.gson.Gson;
import com.sxtljx.dao.EquipmentDao;
import com.sxtljx.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EquipmentServlet extends HttpServlet {
    EquipmentDao equipmentDao = new EquipmentDao();

    @Override
    public void init() throws ServletException {
//        for (int i=0;i<350;i++){
//            equipmentDao.addEquipment(
//                    "TEST-"+i,
//                    "设备"+i,
//                    "无印",
//                    i*10*(int)(Math.random()*100),
//                    (int)(Math.random()*2),
//                    (int)(Math.random()*2),
//                    "2020-03-06 01:02:00",
//                    "采购员"+i,
//                    "维修员"+(i+1)
//            );
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码
        request.setCharacterEncoding("utf-8");
        //设置响应编码
        response.setHeader("content-type", "text/html;charset=utf-8");
        //获取请求标识
        String mark = request.getParameter("mark");


        if ("addEquipment".equals(mark)){
            addEquipment(request,response);
        }else if ("showEquipments".equals(mark)){
            showEquipments(request,response);
        }else if ("updataEquipment".equals(mark)){
            updataEquipment(request,response);
        }else if ("delEquipment".equals(mark)){
            delEquipment(request,response);
        }else if ("qureyEquipmentById".equals(mark)){
            qureyEquipmentById(request,response);
        }else if ("queryEquipmentByOption".equals(mark)){
            queryEquipmentByOption(request,response);
        }else if ("".equals(mark)){

        }else if ("".equals(mark)){

        }else if ("".equals(mark)){

        }
    }

    private void queryEquipmentByOption(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value=request.getParameter("value");
        String flag=request.getParameter("flag");

        //获取当前页
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        //查询员工的总数量
        int count = Integer.parseInt(request.getParameter("count"));
        //创建PageUtil对象
        PageUtil page=new PageUtil(count, curPage);
        //0:按编号查询 1：按名称查询
        //获取员工信息设置于 request作用域
        if ("1".equals(flag)){
            request.setAttribute("list",equipmentDao.queryEquipmentByNumber(value));
        }else if ("0".equals(flag)){
            request.setAttribute("list",equipmentDao.queryEquipmentByEname(value));
        }
        //page信息存于作用域
        request.setAttribute("page",page);
        //请求转发
        request.getRequestDispatcher("showQureyEquipments.jsp").forward(request,response);
    }


    private void qureyEquipmentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        //设置查询到的设备信息
        request.setAttribute("item",equipmentDao.queryEquipmentById(id));
        request.setAttribute("curPage",curPage);
        request.getRequestDispatcher("updataEquipment.jsp").forward(request,response);
    }


    /**
     * 添加设备
     */
    private void addEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取得数据
        String number = request.getParameter("number");
        String ename = request.getParameter("name");
        String manufacturer = request.getParameter("manufacturer");
        int price = Integer.parseInt(request.getParameter("price"));
        int state = Integer.parseInt(request.getParameter("state"));
        int type = Integer.parseInt(request.getParameter("type"));
        String startTime = request.getParameter("startTime");
        String buyer = request.getParameter("buyer");
        String maintainer = request.getParameter("maintainer");
        //存入数据库
        equipmentDao.addEquipment(number,ename,manufacturer,price,state,type,startTime,buyer,maintainer);
        //跳回界面
        request.getRequestDispatcher("addEquipments.jsp").forward(request,response);
    }

    /**
     * 删除设备
     * @param request
     * @param response
     */
    private void delEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPage = request.getParameter("curPage");
        int id = Integer.parseInt(request.getParameter("id"));
        equipmentDao.delEquipmentById(id);
        request.getRequestDispatcher("equipmentServlet?mark=showEquipments&curPage="+curPage).forward(request,response);
    }
    /**
     * 修改设备
     * @param request
     * @param response
     */
    private void updataEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取得数据
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int id = Integer.parseInt(request.getParameter("id"));
        String number = request.getParameter("number");
        String ename = request.getParameter("name");
        String manufacturer = request.getParameter("manufacturer");
        int price = Integer.parseInt(request.getParameter("price"));
        int state = Integer.parseInt(request.getParameter("state"));
        int type = Integer.parseInt(request.getParameter("type"));
        String startTime = request.getParameter("startTime");
        String buyer = request.getParameter("buyer");
        String maintainer = request.getParameter("maintainer");
        //存入数据库
        equipmentDao.updataEquipment(id,number,ename,manufacturer,price,state,type,startTime,buyer,maintainer);
        //跳回界面
        request.getRequestDispatcher("equipmentServlet?mark=showEquipments&curPage"+curPage).forward(request,response);
    }
    private void showEquipments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前页
        int curPage=Integer.parseInt(request.getParameter("curPage"));
        //查询员工的总数量
        int count=equipmentDao.queryCountEquipment();
        //创建PageUtil对象
        PageUtil page=new PageUtil(count, curPage);
        //获取员工信息设置于 request作用域
        request.setAttribute("list",equipmentDao.queryEquipments(page.getStartRow(),page.getPageRow()));
        //page信息存于作用域
        request.setAttribute("page",page);
        //请求转发
        request.getRequestDispatcher("showEquipments.jsp").forward(request,response);

    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
