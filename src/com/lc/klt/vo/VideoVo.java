package com.lc.klt.vo;

import com.lc.klt.vo.program.FileSource;

/**
 * @ClassName: VideoVo
 * @Description:  TODO（视频类）
 * @Author： ccy
 * @CreateDate： 2019-07-31 17:48:22
 * @Update（更新记录）： userName do something(add/update/delete) at what time
 * @Company:LCKJ(乐程科技)
 */
public class VideoVo extends CommonVo{
    private String Length; //视频总长度(毫秒)
    private String VideoWidth; //视频实际宽
    private String VideoHeight; //视频实际高
    private String InOffset; //播放起点位置(毫秒)
    private String PlayLength; //播放总时长(总长-播放起点) (毫秒)
    private String Volume = "1.000000"; // 声音音量（0-1.000000）
    private String Loop = "1"; //是否循环播放（0：否；1：是）
    private String ReserveAS = "0"; //播放时是否保持宽高比

    public VideoVo(){
        this.Type = "3" ;
    }
    public VideoVo(String path){
        this.Type = "3" ;
        FileSource = new FileSource(path);
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getVideoWidth() {
        return VideoWidth;
    }

    public void setVideoWidth(String videoWidth) {
        VideoWidth = videoWidth;
    }

    public String getVideoHeight() {
        return VideoHeight;
    }

    public void setVideoHeight(String videoHeight) {
        VideoHeight = videoHeight;
    }

    public String getInOffset() {
        return InOffset;
    }

    public void setInOffset(String inOffset) {
        InOffset = inOffset;
    }

    public String getPlayLength() {
        return PlayLength;
    }

    public void setPlayLength(String playLength) {
        PlayLength = playLength;
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public String getLoop() {
        return Loop;
    }

    public void setLoop(String loop) {
        Loop = loop;
    }

    public String getReserveAS() {
        return ReserveAS;
    }

    public void setReserveAS(String reserveAS) {
        ReserveAS = reserveAS;
    }


}
