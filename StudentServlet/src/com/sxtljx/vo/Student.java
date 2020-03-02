package com.sxtljx.vo;

public class Student {
    private int id;
    private String name;
    private int age;
    private int classNum;

    public Student() {
    }

    public Student(int id, String name, int age, int classNum) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classNum = classNum;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", classNum=" + classNum +
                '}';
    }
}
