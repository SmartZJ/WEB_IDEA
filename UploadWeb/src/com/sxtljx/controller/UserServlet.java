package com.sxtljx.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    //声明储存用户、密码的容器
    private Map<String, String> userMap=new HashMap<String, String>();
    @Override
    public void init() throws ServletException {
        userMap.put("张三","123");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //取出请求中的mark标记
        String mark=request.getParameter("mark");

        //通过mark 进行功能选择
        if("login".equals(mark)){
            userLogin(request,response);
        }else if("regis".equals(mark)){

            try {
                userRegis(request, response);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

        }else if("show".equals(mark)){
            show(request, response);
        }else if("upload".equals(mark)){
            try {
                upload(request,response);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

        }else if("download".equals(mark)){
            //1)设置下载的响应头
            String imgName=new String(request.getParameter("imgName").getBytes("utf-8"),"iso8859-1");
            response.setHeader("content-disposition", "attachment;filename="+imgName);

            show(request, response);
        }else if("success".equals(mark)){
            success(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    /**
     * 登录方法
     * */
    public void userLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置响应编码
        response.setHeader("content-type", "text/html;charset=utf-8");

        //获取表单参数
        String uname=request.getParameter("uname");
        String pwd=request.getParameter("pwd");

        if(uname.isEmpty()||pwd.isEmpty()){
            request.setAttribute("msg", "<font color='red'>登陆信息填写不完整</font>");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }else{
            //通过用户名尝试从map容器中获取正确密码
            String rightPwd=userMap.get(uname);
            if(pwd.equals(rightPwd)){
                request.getRequestDispatcher("userServlet?mark=success").forward(request, response);

            }else{
                request.setAttribute("msg", "<font color='red'>用户名或密码错误</font>");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        }
    }

    /**
     * 用户注册
     */
    public void userRegis(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException, FileUploadException {
        //初始化表单参数
        String uname="";
        String pwd="";
        String repwd="";
        FileItem photoItem=null;
        // 创建一个生产解析请求对象的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 配置工厂的临时文件夹
        ServletContext servletContext = this.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // 通过工厂生产解析请求的工具对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决的是文件名为中文
        upload.setHeaderEncoding("utf-8");
        //设置响应编码
        response.setHeader("content-type", "text/html;charset=utf-8");
            // 解析请求
        List<FileItem> items = upload.parseRequest(request);
            //遍历集合
        for(FileItem item:items){
                //获取表单域元素的名字
            String fieldName=item.getFieldName();
                //判断当前元素是否为普通表单域元素   true 普通表单域元素  false 文件域元素
            boolean result=item.isFormField();
            if(result){
                //普通表单域元素
                //获取值
                String value=item.getString("utf-8");
                if (fieldName.equals("uname")){
                    uname=value;
                }else if (fieldName.equals("pwd")){
                    pwd=value;
                }else if (fieldName.equals("repwd")){
                       repwd=value;
                }
            }else {
                photoItem=item;
            }
        }
        //注册信息判断
        if(uname.isEmpty()||pwd.isEmpty()||repwd.isEmpty()||photoItem.getSize()==0){
            request.setAttribute("msg", "<font color='red'>注册信息填写不完整</font>");
            request.getRequestDispatcher("regis.jsp").forward(request, response);
            return;
        }else if(!pwd.equals(repwd)){
            request.setAttribute("msg", "<font color='red'>两次密码输入不一致</font>");
            request.getRequestDispatcher("regis.jsp").forward(request, response);
            return;
        }else if(userMap.containsKey(uname)){
            request.setAttribute("msg", "<font color='red'>该用户名已经被使用</font>");
            request.getRequestDispatcher("regis.jsp").forward(request, response);
            return;
        }else{
            userMap.put(uname, pwd);
            //获取路径名
            String photoPath = servletContext.getRealPath("userimg");
            //获取文件名
            String photoName=photoItem.getName();
            System.out.println(photoName);
            //获取文件的流信息
            InputStream in=photoItem.getInputStream();
            OutputStream out=new FileOutputStream(photoPath+"\\"+photoName);
            byte[] b=new byte[1024];
            int temp=0;
            while((temp=in.read(b))!=-1){
                out.write(b, 0, temp);
            }
            out.flush();
            out.close();
            in.close();
            //创建用户图片文件夹
            File userFile = new File(photoPath+"\\"+uname);
            //将用户名和图片名保存到作用域中
            servletContext.setAttribute(uname, photoName);
            request.setAttribute("msg", "<font color='green'>注册成功，请登陆</font>");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }


    }
    /**
     * 图片显示方法
     * */
    public void show(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
        //获取页面出入的path值
        String path=request.getParameter("path");
        //获取输入流
        InputStream in=this.getServletContext().getResourceAsStream(path);
        OutputStream out=response.getOutputStream();
        byte[] b=new byte[1024];
        int temp=0;
        while((temp=in.read(b))!=-1){
            out.write(b, 0, temp);
        }

        out.flush();
        out.close();
        in.close();
    }

    /**
    * 图片上传
    * */
    public void upload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException {
        //初始化属性
        String uname=request.getParameter("uname");
        FileItem photoItem=null;
        String photoPath="";

        // 创建一个生产解析请求对象的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 配置工厂的临时文件夹
        ServletContext servletContext = this.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // 通过工厂生产解析请求的工具对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决的是文件名为中文
        upload.setHeaderEncoding("utf-8");
        //设置响应编码
        response.setHeader("content-type", "text/html;charset=utf-8");
        // 解析请求
        List<FileItem> items = upload.parseRequest(request);
        //遍历集合
        for(FileItem item:items) {
            //获取表单域元素的名字
            String fieldName = item.getFieldName();
            //判断当前元素是否为普通表单域元素   true 普通表单域元素  false 文件域元素
            boolean result = item.isFormField();
            if (!result) {
                photoItem = item;
            }
        }
        System.out.println("photoItem===>"+photoItem);
        if (photoItem.getSize()==0){
            request.setAttribute("msg","<font color='red'>请选择文件</font>");
            request.getRequestDispatcher("userServlet?mark=success").forward(request, response);
            return;
        }

        //获取路径名
        photoPath = servletContext.getRealPath("userimg")+"\\"+uname;
        //获取文件名
        String photoName=photoItem.getName();
        //获取文件的流信息
        InputStream in=photoItem.getInputStream();
        OutputStream out=new FileOutputStream(photoPath+"\\"+photoName);
        byte[] b=new byte[1024];
        int temp=0;
        while((temp=in.read(b))!=-1){
            out.write(b, 0, temp);
        }
        out.flush();
        out.close();
        in.close();
        request.getRequestDispatcher("userServlet?mark=success").forward(request, response);

    }

    /**
     * 登录成功方法
     * */
    public void success(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
        String uname=request.getParameter("uname");
        String photoName = (String)request.getServletContext().getAttribute(uname);
        Set<String> imgList = request.getServletContext().getResourcePaths("/userimg/"+uname);
        String path = "userServlet?mark=show&path=userimg/"+photoName;
        request.setAttribute("list",imgList);
        request.getSession().setAttribute("uname", uname);
        request.setAttribute("path",path);
        request.getRequestDispatcher("success.jsp").forward(request, response);
        }
}
