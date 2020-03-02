package com.sxtljx.vo;

public class AgeArea {
    private String ageArea;
    private int count;

    public AgeArea(String ageArea, int count) {
        this.ageArea = ageArea;
        this.count = count;
    }

    public AgeArea() {

    }

    public String getAgeArea() {
        return ageArea;
    }

    public void setAgeArea(String ageArea) {
        this.ageArea = ageArea;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AgeArea{" +
                "ageArea='" + ageArea + '\'' +
                ", count=" + count +
                '}';
    }
}
