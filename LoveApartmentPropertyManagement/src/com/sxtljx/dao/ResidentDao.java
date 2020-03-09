package com.sxtljx.dao;

import com.sxtljx.vo.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResidentDao {
    //添加方法

    /**
     * 添加住户
     */
    public void addResident(String name, String gender, String age, String idNum, String edu, String email, String phoneNum, String startDate, String endDate, String imgPath) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            ps = conn.prepareStatement("insert into love_resident values(default,?,?,?,?,?,?,?,?,?,?)");
            if (imgPath.equals("")) {
                imgPath = "residentImgs/defult.jpg";
            }
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(gender));
            ps.setInt(3, Integer.parseInt(age));
            ps.setString(4, idNum);
            ps.setString(5, edu);
            ps.setString(6, email);
            ps.setString(7, phoneNum);
            ps.setString(8, startDate);
            ps.setString(9, endDate);
            ps.setString(10, imgPath);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    //删除方法
    /**
     * 删除住户
     *
     * @param id 住户id
     */
    public void delResidentById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "delete from love_resident where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    /**
     * 删除住户和房间的关系
     *
     * @param idNum
     */
    public void delResInBUR(String idNum) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            ps = conn.prepareStatement("update build_unit_room set id_number=null where id_number= ? ");
            ps.setString(1, idNum);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    //查询类方法
    public List<Education> queryEduList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Education> eduList = new ArrayList<Education>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select * from love_edu";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("edu_id");
                String name = rs.getString("edu_name");
                eduList.add(new Education(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return eduList;
    }

    public List<Build> queryBuilds() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Build> buildList = new ArrayList<Build>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select * from love_build";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("build_id");
                String name = rs.getString("build_name");
                buildList.add(new Build(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return buildList;
    }

    public List<Unit> queryUnits() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Unit> unitList = new ArrayList<Unit>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select * from love_unit";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("unit_id");
                String name = rs.getString("unit_name");
                unitList.add(new Unit(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return unitList;
    }

    public List<Room> queryRooms() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Room> roomList = new ArrayList<Room>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select * from love_room";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("room_id");
                String name = rs.getString("room_name");
                roomList.add(new Room(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return roomList;
    }


    public List<Resident> queryResident(int startRow, int pageRow) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Resident> residentList = new ArrayList<Resident>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select * from love_resident r\n" +
                    "left join love_edu e on r.edu=e.edu_id \n" +
                    "left join build_unit_room bur on r.id_number=bur.id_number\n" +
                    "left join love_build b on bur.build_id=b.build_id\n" +
                    "left join love_unit u on bur.unit_id=u.unit_id\n" +
                    "left join love_room ro on bur.room_id=ro.room_id limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, startRow);
            ps.setInt(2, pageRow);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int gender = rs.getInt("gender");
                int age = rs.getInt("age");
                String idNum = rs.getString("id_number");
                int edu = rs.getInt("edu");
                String email = rs.getString("email");
                String phoneNum = rs.getString("phone_num");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String imgPath = rs.getString("img_path");
                int build = rs.getInt("build_id");
                int unit = rs.getInt("unit_id");
                int room = rs.getInt("room_id");
                String eduName = rs.getString("edu_name");
                String address = rs.getString("build_name") + rs.getString("unit_name") + rs.getString("room_name");
                residentList.add(new Resident(id, name, gender, age, idNum, edu, email, phoneNum, startDate, endDate, imgPath, build, unit, room, eduName, address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return residentList;
    }

    public List<Resident> queryResident() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Resident> residentList = new ArrayList<Resident>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select * from love_resident r \n" +
                    "left join love_edu e on r.edu=e.edu_id \n" +
                    "left join build_unit_room bur on r.id_number=bur.id_number\n" +
                    "left join love_build b on bur.build_id = b.build_id\n" +
                    "left join love_unit u on bur.unit_id = u.unit_id\n" +
                    "left join love_room ro on bur.room_id = ro.room_id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int gender = rs.getInt("gender");
                int age = rs.getInt("age");
                String idNum = rs.getString("id_number");
                int edu = rs.getInt("edu");
                String email = rs.getString("email");
                String phoneNum = rs.getString("phone_num");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String imgPath = rs.getString("img_path");
                int build = rs.getInt("build_id");
                int unit = rs.getInt("unit_id");
                int room = rs.getInt("room_id");
                String eduName = rs.getString("edu_name");
                String address = rs.getString("build_name") + rs.getString("unit_name") + rs.getString("room_name");
                residentList.add(new Resident(id, name, gender, age, idNum, edu, email, phoneNum, startDate, endDate, imgPath, build, unit, room, eduName, address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return residentList;
    }

    /**
     * 根据ID查询住户
     */

    /**
     * 根据年龄段查询住户
     *
     * @param num1
     * @param num2
     * @param startRow
     * @param pageRow
     * @return
     */
    public List<Resident> queryResidentsByAges(int num1, int num2, int startRow, int pageRow) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Resident> residentList = new ArrayList<Resident>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            ps = conn.prepareStatement("select * from love_resident r\n" +
                    "left join love_edu e on r.edu=e.edu_id \n" +
                    "left join build_unit_room bur on r.id_number=bur.id_number\n" +
                    " left join love_build b on bur.build_id = b.build_id\n" +
                    "left join love_unit u on bur.unit_id = u.unit_id\n" +
                    " left join love_room ro on bur.room_id = ro.room_id\n" +
                    "where r.age between ? and ? limit ?,?");
            ps.setInt(1, num1);
            ps.setInt(2, num2);
            ps.setInt(3, startRow);
            ps.setInt(4, pageRow);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int gender = rs.getInt("gender");
                int age = rs.getInt("age");
                String idNum = rs.getString("id_number");
                int edu = rs.getInt("edu");
                String email = rs.getString("email");
                String phoneNum = rs.getString("phone_num");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String imgPath = rs.getString("img_path");
                int build = rs.getInt("build_id");
                int unit = rs.getInt("unit_id");
                int room = rs.getInt("room_id");
                String eduName = rs.getString("edu_name");
                String address = rs.getString("build_name") + rs.getString("unit_name") + rs.getString("room_name");
                residentList.add(new Resident(id, name, gender, age, idNum, edu, email, phoneNum, startDate, endDate, imgPath, build, unit, room, eduName, address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return residentList;
    }

    /**
     * 根据年龄段查住户
     * @param num
     * @param startRow
     * @param pageRow
     * @return
     */
    public List<Resident> queryResidentsByAges(int num, int startRow, int pageRow) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Resident> residentList = new ArrayList<Resident>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            ps = conn.prepareStatement("select * from love_resident r\n" +
                    "left join love_edu e on r.edu=e.edu_id \n" +
                    "left join build_unit_room bur on r.id_number=bur.id_number\n" +
                    " left join love_build b on bur.build_id = b.build_id\n" +
                    "left join love_unit u on bur.unit_id = u.unit_id\n" +
                    " left join love_room ro on bur.room_id = ro.room_id\n" +
                    "where r.age > ? limit ?,?");
            ps.setInt(1, num);
            ps.setInt(2, startRow);
            ps.setInt(3, pageRow);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int gender = rs.getInt("gender");
                int age = rs.getInt("age");
                String idNum = rs.getString("id_number");
                int edu = rs.getInt("edu");
                String email = rs.getString("email");
                String phoneNum = rs.getString("phone_num");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String imgPath = rs.getString("img_path");
                int build = rs.getInt("build_id");
                int unit = rs.getInt("unit_id");
                int room = rs.getInt("room_id");
                String eduName = rs.getString("edu_name");
                String address = rs.getString("build_name") + rs.getString("unit_name") + rs.getString("room_name");
                residentList.add(new Resident(id, name, gender, age, idNum, edu, email, phoneNum, startDate, endDate, imgPath, build, unit, room, eduName, address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return residentList;
    }
    public Resident queryResidentById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Resident resident = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select * from love_resident r\n" +
                    "left join build_unit_room bur on r.id_number=bur.id_number where r.id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int rid = rs.getInt("id");
                String name = rs.getString("name");
                int gender = rs.getInt("gender");
                int age = rs.getInt("age");
                String idNum = rs.getString("id_number");
                int edu = rs.getInt("edu");
                String email = rs.getString("email");
                String phoneNum = rs.getString("phone_num");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String imgPath = rs.getString("img_path");
                int build = rs.getInt("build_id");
                int unit = rs.getInt("unit_id");
                int room = rs.getInt("room_id");
                resident = new Resident(rid, name, gender, age, idNum, edu, email, phoneNum, startDate, endDate, imgPath, build, unit, room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return resident;
    }
    /**
     * 身份证查重
     * @param idNum
     * @return
     */
    public boolean qureyResidentIdNumIsExist(String idNum) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select * from love_resident where id_number= ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idNum);
            rs = ps.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return true;
    }
    //查询房间是否有住户
    public boolean qureyRoomIsEmpty(int buildId, int unitId, int roomId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select id_number from build_unit_room where build_id= ? and unit_id= ? and room_id= ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, buildId);
            ps.setInt(2, unitId);
            ps.setInt(3, roomId);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("id_number")==null){
                        return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    //查询住户人数
    public int queryCountResident() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select count(*) from love_resident";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    /**
     * 查询各学历的人数
     */
    public List<Education> queryCountEduByBuildId(int buildId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Education> eduCount = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select edu_name eduname,count(edu_name) count from love_resident r\n" +
                    "left join build_unit_room bur on r.id_number=bur.id_number\n" +
                    "left join love_build b on bur.build_id=b.build_id\n" +
                    "left join love_edu e on r.edu = e.edu_id where b.build_id = ?\n" +
                    "GROUP BY e.edu_name ORDER BY e.edu_id  ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, buildId);
            rs = ps.executeQuery();
            while (rs.next()) {
                eduCount.add(new Education(rs.getString("eduname"), rs.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return eduCount;
    }

    /**
     * 查询年龄段人数
     *
     * @return
     */
    public List<AgeArea> queryCountByAges() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AgeArea> ageList = new ArrayList<AgeArea>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "select temp.ageArea ageArea,count(*) count from ( " +
                    "select  " +
                    "case when age between 0 and 10 then '0-10' " +
                    "when age between 11 and 20 then '11-20' " +
                    "when age between 21 and 30 then '21-30' " +
                    "when age between 31 and 40 then '31-40' " +
                    "when age between 41 and 50 then '41-50' " +
                    "when age between 51 and 60 then '51-60' " +
                    "else '60以上' end ageArea " +
                    "from love_resident  " +
                    ") temp group by temp.ageArea;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ageArea = rs.getString(1);
                int count = rs.getInt(2);

                ageList.add(new AgeArea(ageArea, count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return ageList;
    }

    /**
     * 修改住户
     *
     */
    public void updataResidentWithRoom(String idNum, String build, String unit, String room) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            ps = conn.prepareStatement("update build_unit_room set id_number=? where build_id = ? and unit_id = ? and room_id= ?");
            ps.setString(1, idNum);
            ps.setInt(2, Integer.parseInt(build));
            ps.setInt(3, Integer.parseInt(unit));
            ps.setInt(4, Integer.parseInt(room));
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


    public void updataResident(String id, String name, String gender, String age, String idNum, String edu, String email, String phoneNum, String startDate, String endDate, String imgPath) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            if ("".equals(imgPath)) {
                ps = conn.prepareStatement("update love_resident set name=?,gender=?,age=?,id_number=?,edu=?,email=?,phone_num=?,start_date=?,end_date=?,build_id=?,unit_id=?,room_id=?  where id = ? ");
            } else {
                ps = conn.prepareStatement("update love_resident set name=?,gender=?,age=?,id_number=?,edu=?,email=?,phone_num=?,start_date=?,end_date=?,build_id=?,unit_id=?,room_id=?,img_path=?  where id = ? ");
            }
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(gender));
            ps.setInt(3, Integer.parseInt(age));
            ps.setString(4, idNum);
            ps.setString(5, edu);
            ps.setString(6, email);
            ps.setString(7, phoneNum);
            ps.setString(8, startDate);
            ps.setString(9, endDate);
            ps.setInt(10, 0);
            ps.setInt(11, 0);
            ps.setInt(12, 0);
            if ("".equals(imgPath)) {
                ps.setInt(13, Integer.parseInt(id));
            } else {
                ps.setString(13, imgPath);
                ps.setInt(14, Integer.parseInt(id));
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 关系表
     */
    public void addBUR(int b, int u, int r) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartment_love", "root", "root");
            String sql = "insert into build_unit_room values(null,?,?,?,null)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, b);
            ps.setInt(2, u);
            ps.setInt(3, r);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
    }



}
