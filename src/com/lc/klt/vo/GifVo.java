package com.lc.klt.vo;

import com.lc.klt.vo.program.FileSource;

/**
 * @ClassName: PictureVo
 * @Description:  TODO（动画类）
 * @Author： ccy
 * @CreateDate： 2019-07-31 17:48:22
 * @Update（更新记录）： userName do something(add/update/delete) at what time
 * @Company:LCKJ(乐程科技)
 */
public class GifVo extends CommonVo{
    private String Width ;
    private String Height ;
    private String Length ; //文件的播放时间长度（毫秒）
    private String IsReserveAS ; //是否保持高度比(0：否；1：是)
    private String IsLoop  = "1"; //是否循环播放（0：否；1：是）

    public GifVo(){
        this.Type = "6" ;
    }
    public GifVo(String path){
        this.Type = "6" ;
        this.FileSource = new FileSource(path);
    }

    public String getWidth() {
        return Width;
    }

    public void setWidth(String width) {
        Width = width;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getIsReserveAS() {
        return IsReserveAS;
    }

    public void setIsReserveAS(String isReserveAS) {
        IsReserveAS = isReserveAS;
    }

    public String getIsLoop() {
        return IsLoop;
    }

    public void setIsLoop(String isLoop) {
        IsLoop = isLoop;
    }
}
