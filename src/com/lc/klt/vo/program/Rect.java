package com.lc.klt.vo.program;

/**
 * 显示区域
 */
public class Rect {
    /**
     * X : 0
     * Y : 0
     * Width : 128
     * Height : 64
     */

    private String X = "0";
    private String Y = "0";
    private String Width = ""; // 64
    private String Height = ""; // 32
    private String BorderWidth;
    private String BorderColor;
    public Rect(){

    }
    public Rect(String x,String y,String width,String height){
        this.X = x ;
        this.Y = y ;
        this.Width = width  ;
        this.Height = height  ;
    }

    public String getX() {
        return X;
    }

    public void setX(String X) {
        this.X = X;
    }

    public String getY() {
        return Y;
    }

    public void setY(String Y) {
        this.Y = Y;
    }

    public String getWidth() {
        return Width;
    }

    public void setWidth(String Width) {
        this.Width = Width;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String Height) {
        this.Height = Height;
    }
    public String getBorderWidth() {
        return BorderWidth;
    }

    public void setBorderWidth(String BorderWidth) {
        this.BorderWidth = BorderWidth;
    }

    public String getBorderColor() {
        return BorderColor;
    }

    public void setBorderColor(String BorderColor) {
        this.BorderColor = BorderColor;
    }
}
