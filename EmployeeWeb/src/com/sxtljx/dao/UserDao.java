package com.sxtljx.dao;

import java.sql.*;

public class UserDao {
    /**
     * 用户登陆，如果登陆成功返回 true  登陆失败返回false
     */
    public boolean userLogin(String uname,String pwd){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            //  select * from user where uname="sky" and pwd="' or '1'='1"
            ps=conn.prepareStatement("select * from e_user where uname=? and pwd=?");
            ps.setString(1, uname);
            ps.setString(2, pwd);
            rs=ps.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 用户注册
     */
    public void userRegis(String uname, String pwd) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps = conn.prepareStatement("insert into e_user values(null,?,?)");
            ps.setString(1,uname);
            ps.setString(2, pwd);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    /**
     * 验证用户名是否存在
     * @param uname 传入用户名
     * @return 存在true  不存在false
     */
    public boolean valiUname(String uname) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("select * from e_user where uname=?");
            ps.setString(1, uname);
            rs=ps.executeQuery();
            while(rs.next()){

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
