package com.sxtljx.dao;

import com.sxtljx.vo.Equipment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDao {
    public void addEquipment(String number,String ename, String manufacturer, int price, int state, int type, String startTime, String buyer, String maintainer ) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            ps=conn.prepareStatement("insert into love_equipment values(default,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,number);
            ps.setString(2,ename);
            ps.setString(3,manufacturer);
            ps.setDouble(4,price);
            ps.setInt(5,state);
            ps.setInt(6,type);
            ps.setString(7,startTime);
            ps.setString(8,buyer);
            ps.setString(9,maintainer);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public int queryCountEquipment() {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql="select count(*) from love_equipment";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
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
        return count;
    }

    public List<Equipment> queryEquipments(int startRow, int pageRow) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Equipment> equipmentList = new ArrayList<Equipment>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql="select * from love_equipment limit ?,?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,startRow);
            ps.setInt(2,pageRow);
            rs=ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String ename=rs.getString("ename");
                String manufacturer=rs.getString("manufacturer");
                int price = rs.getInt("price");
                int state = rs.getInt("state");
                int type = rs.getInt("type");
                String startTime = rs.getString("startTime");
                String buyer = rs.getString("buyer");
                String maintainer = rs.getString("maintainer");
                equipmentList.add(new Equipment(id,number,ename,manufacturer,price,state,type,startTime,buyer,maintainer));
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
        return equipmentList;
    }

    public void delEquipmentById(int id) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql="delete from love_equipment where id = ?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void updataEquipment(int id, String number, String ename, String manufacturer, int price, int state, int type, String startTime, String buyer, String maintainer) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            ps=conn.prepareStatement("update love_equipment set number=?,ename=?,manufacturer=?,price=?,state=?,type=?,startTime=?,buyer=?,maintainer=? where id = ? ");
            ps.setString(1,number);
            ps.setString(2,ename);
            ps.setString(3,manufacturer);
            ps.setDouble(4,price);
            ps.setInt(5,state);
            ps.setInt(6,type);
            ps.setString(7,startTime);
            ps.setString(8,buyer);
            ps.setString(9,maintainer);
            ps.setInt(10,id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public Equipment queryEquipmentById(int id) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Equipment equipment = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql="select * from love_equipment where id = ?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                int eid = rs.getInt("id");
                String number = rs.getString("number");
                String ename=rs.getString("ename");
                String manufacturer=rs.getString("manufacturer");
                int price = rs.getInt("price");
                int state = rs.getInt("state");
                int type = rs.getInt("type");
                String startTime = rs.getString("startTime");
                //格式处理
                 startTime = startTime.substring(0,10)+"T"+startTime.substring(11,16);
                String buyer = rs.getString("buyer");
                String maintainer = rs.getString("maintainer");
                equipment = new Equipment(id,number,ename,manufacturer,price,state,type,startTime,buyer,maintainer);
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
        return equipment;
    }


    public List<Equipment> queryEquipmentByNumber( String value) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Equipment> equipmentList = new ArrayList<Equipment>();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String val = "%"+value+"%";
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql="select * from love_equipment where  number like ? ";
            ps=conn.prepareStatement(sql);
            ps.setString(1,val);
            rs=ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String ename=rs.getString("ename");
                String manufacturer=rs.getString("manufacturer");
                int price = rs.getInt("price");
                int state = rs.getInt("state");
                int type = rs.getInt("type");
                String startTime = rs.getString("startTime");
                String buyer = rs.getString("buyer");
                String maintainer = rs.getString("maintainer");
                equipmentList.add(new Equipment(id,number,ename,manufacturer,price,state,type,startTime,buyer,maintainer));
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
        return equipmentList;
    }

    public List<Equipment> queryEquipmentByEname(String value) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Equipment> equipmentList = new ArrayList<Equipment>();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String val = "%"+value+"%";
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql="select * from love_equipment where  ename like ? ";
            ps=conn.prepareStatement(sql);
            ps.setString(1,val);
            rs=ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String ename=rs.getString("ename");
                String manufacturer=rs.getString("manufacturer");
                int price = rs.getInt("price");
                int state = rs.getInt("state");
                int type = rs.getInt("type");
                String startTime = rs.getString("startTime");
                String buyer = rs.getString("buyer");
                String maintainer = rs.getString("maintainer");
                equipmentList.add(new Equipment(id,number,ename,manufacturer,price,state,type,startTime,buyer,maintainer));
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
        return equipmentList;
    }
}
