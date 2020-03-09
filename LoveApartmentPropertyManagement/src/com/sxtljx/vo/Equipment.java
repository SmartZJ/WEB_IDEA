package com.sxtljx.vo;

public class Equipment {
    private int id;
    private String number;
    private String ename;
    private String manufacturer;
    private int price;
    //0:不可用 1：可用
    private int state;
    //0：固定资产 1：流动资产
    private int type;
    private String startTime;
    private String buyer;
    private String maintainer;

    public Equipment() {
    }

    public Equipment(int id, String number,String ename, String manufacturer, int price, int state, int type, String startTime, String buyer, String maintainer) {
        this.id = id;
        this.number = number;
        this.ename = ename;
        this.manufacturer = manufacturer;
        this.price = price;
        this.state = state;
        this.type = type;
        this.startTime = startTime;
        this.buyer = buyer;
        this.maintainer = maintainer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", ename='" + ename + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", state=" + state +
                ", type='" + type + '\'' +
                ", startTime='" + startTime + '\'' +
                ", buyer='" + buyer + '\'' +
                ", maintainer='" + maintainer + '\'' +
                '}';
    }
}
