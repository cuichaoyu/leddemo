package com.lc.vo;

public class LedVo {
    private String id = ""; // 屏的ID
    private String username = "admin"; // 屏的用户名
    private String password = "123456"; //屏的密码
    private int width = 192; // 屏幕高
    private int height = 128; // 屏幕宽
    //private String ip = "192.168.100.233"; // 诺瓦屏Ip 地址
    private String ip = "192.168.100.240"; // 卡莱特Ip 地址

    public LedVo(){

    }
    public LedVo(String ip,int width,int height){
        this.ip = ip ;
        this.width = width ;
        this.height = height ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
