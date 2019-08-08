package com.lc.klt.vo;

import java.util.List;


/**
 * @ClassName: TextVo
 * @Description:  TODO（文字类）
 * @Author： ccy
 * @CreateDate： 2019-07-31 17:48:22
 * @Update（更新记录）： userName do something(add/update/delete) at what time
 * @Company:LCKJ(乐程科技)
 */
public class TextVo extends CommonVo{
    private String Text;
    private String TextColor = "0xFF0000";
    private int CenteralAlign = 1; //是否垂直居中（0：否；1：是）
    private LogFont LogFont  = new LogFont(); // 样式


    public TextVo(){
        this.Type = "5" ;
    }

    public TextVo(String text){
        this.Type = "5" ;
        this.Text = text ;
    }

    /**
     *
     * @param text 文字
     * @param TextColor 文字颜色
     */
    public TextVo(String text,String TextColor){
        this.Type = "5" ;
        this.Text = text ;
        this.TextColor = TextColor ;
    }

    /**
     *
     * @param text 文字
     * @param TextColor 文字颜色
     * @param lfHeight 字体 高
     * @param lfWidth 字体 宽
     */
    public TextVo(String text,String TextColor,LogFont font){
        this.Type = "5" ;
        this.Text = text ;
        this.TextColor = TextColor ;
        this.LogFont = font;
    }

    public TextVo(String text,String TextColor,int CenteralAlign){
        this.Type = "5" ;
        this.Text = text ;
        this.TextColor = TextColor ;
        this.CenteralAlign = CenteralAlign ;
    }

    public int getCenteralAlign() {
        return CenteralAlign;
    }

    public void setCenteralAlign(int centeralAlign) {
        CenteralAlign = centeralAlign;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTextColor() {
        return TextColor;
    }

    public void setTextColor(String textColor) {
        TextColor = textColor;
    }

    public static class LogFont {
        /**
         * lfHeight : 36
         * lfWidth : 0
         */

        private String lfHeight = "10"; //逻辑单位的字符或者字符元高度
        private String lfWidth = "10"; //逻辑单位的字符字体的平均宽度
        private String lfFaceName = "黑体";
        private String lfItalic; //是否使用斜体（0：否；1：是）
        private String lfUnderline; //是否添加下划线（0：否；1：是）
        private String lfStrikeOut; //是否添加删除线（0：否；1：是）
        private String lfCharSet; //字符集


        public LogFont(){

        }

        public LogFont(String lfHeight,String lfWidth){
            this.lfHeight = lfHeight ;
            this.lfWidth = lfWidth ;
        }
        public LogFont(String lfHeight,String lfWidth,String lfFaceName){
            this.lfHeight = lfHeight ;
            this.lfWidth = lfWidth ;
            this.lfFaceName = lfFaceName ;
        }
        public String getLfFaceName() {
            return lfFaceName;
        }

        public void setLfFaceName(String lfFaceName) {
            this.lfFaceName = lfFaceName;
        }

        public String getLfHeight() {
            return lfHeight;
        }

        public void setLfHeight(String lfHeight) {
            this.lfHeight = lfHeight;
        }

        public String getLfWidth() {
            return lfWidth;
        }

        public void setLfWidth(String lfWidth) {
            this.lfWidth = lfWidth;
        }

        public String getLfItalic() {
            return lfItalic;
        }

        public void setLfItalic(String lfItalic) {
            this.lfItalic = lfItalic;
        }

        public String getLfUnderline() {
            return lfUnderline;
        }

        public void setLfUnderline(String lfUnderline) {
            this.lfUnderline = lfUnderline;
        }

        public String getLfStrikeOut() {
            return lfStrikeOut;
        }

        public void setLfStrikeOut(String lfStrikeOut) {
            this.lfStrikeOut = lfStrikeOut;
        }

        public String getLfCharSet() {
            return lfCharSet;
        }

        public void setLfCharSet(String lfCharSet) {
            this.lfCharSet = lfCharSet;
        }
    }
}
