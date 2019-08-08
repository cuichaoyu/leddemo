package com.lc.klt.vo;

import com.lc.klt.vo.program.FileSource;


/**  
* @ClassName: PictureVo
* @Description:  TODO（图片类）
* @Author： ccy
* @CreateDate： 2019-07-31 17:49:31
* @Update（更新记录）： userName do something(add/update/delete) at what time 
* @Company:LCKJ(乐程科技)
*/
public class PictureVo extends CommonVo{

    private String Width ;
    private String Height ;
    private String ReserveAS ; //是否保持高度比(0：否；1：是)

    public PictureVo(){
        this.Type = "2" ;
    }
    public PictureVo(String path){
        Type = "2" ;
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

    public String getReserveAS() {
        return ReserveAS;
    }

    public void setReserveAS(String reserveAS) {
        ReserveAS = reserveAS;
    }

}
