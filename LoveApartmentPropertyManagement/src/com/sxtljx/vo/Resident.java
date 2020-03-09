package com.sxtljx.vo;

import java.util.Date;

public class Resident {
    private int id;
    private String name;
    private int gender;
    private int age;
    private String idNum;
    private int edu;
    private String email;
    private String phoneNum;
    private String startDate;
    private String endDate;
    private String imgPath;
    private int buildId;
    private int unitId;
    private int roomId;
    private String eduName;
    private String address;



    public Resident() {
    }

    public Resident(int id, String name, int gender, int age, String idNum, int edu, String email, String phoneNum, String startDate, String endDate, String imgPath, int buildId, int unitId, int roomId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.idNum = idNum;
        this.edu = edu;
        this.email = email;
        this.phoneNum = phoneNum;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imgPath = imgPath;
        this.buildId = buildId;
        this.unitId = unitId;
        this.roomId = roomId;
    }

    public Resident(int id, String name, int gender, int age, String idNum, int edu, String email, String phoneNum, String startDate, String endDate, String imgPath, int buildId, int unitId, int roomId, String eduName,String address) {
        this(id, name, gender, age,idNum,edu,email, phoneNum, startDate, endDate, imgPath,buildId,unitId,roomId);
        this.eduName = eduName;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public int getEdu() {
        return edu;
    }

    public void setEdu(int edu) {
        this.edu = edu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getEduName() {
        return eduName;
    }

    public void setEduName(String eduName) {
        this.eduName = eduName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", idNum='" + idNum + '\'' +
                ", edu=" + edu +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", buildId=" + buildId +
                ", unitId=" + unitId +
                ", roomId=" + roomId +
                ", eduName='" + eduName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
