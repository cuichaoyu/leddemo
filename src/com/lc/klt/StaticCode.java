package com.lc.klt;

import com.lc.klt.vo.TextVo;

public class StaticCode {
    public static String HTTP = "http://" ;
    public static String FTP = "ftp://" ;
    public static String URL = "/api/program/" ;// 发送节目
    public static String SHOWTOAST = "/api/showToast/" ;// 查看是否显示路径标示
    public static String ACTION = "/api/action/" ; // 屏幕电源指令
    public static String FILENAME = "allFile" ; // 文件保留的文件夹名称
    public static String NOWPLAYING = "/api/vsns.json" ; // 正在播放的节目名称

    public static TextVo.LogFont FONT10 = new TextVo.LogFont("10","10"); // 10号字体
    public static TextVo.LogFont FONT12 = new TextVo.LogFont("12","12"); //
    public static TextVo.LogFont FONT14 = new TextVo.LogFont("14","14"); //
    public static TextVo.LogFont FONT18 = new TextVo.LogFont("18","18"); //
    public static TextVo.LogFont FONT20 = new TextVo.LogFont("20","20"); //
    public static String COLOR_RED = "0xFF0000"; //
    public static String COLOR_GREEN = "0x7FFF00"; //
    public static String COLOR_YELLOW = "0xFFFF00"; //
    public static String COLOR_WHITE = "0xFFFFFF"; //

    /**
     * 屏幕IP
     */
    public static String IP = "ip" ;
    /**
     * 车次
     */
    public static String CC = "cc" ;
    /**
     * 起始车站
     */
    public static String QSZ = "qsz" ;
    /**
     * 终到站车站
     */
    public static String ZDZ  = "zdz" ;
    /**
     *当前车站
     */
    public static String DQCZ = "dqcz" ;
    /**
     * 到达时刻
     */
    public static String DDSK = "ddsk" ;
    /**
     *  发车时刻
     */
    public static String FCSK = "fcsk" ;
    /**
     *  左车厢
     */
    public static String ZCX = "zcx" ;
    /**
     * 右车厢
     */
    public static String YCX = "ycx" ;
    /**
     * 当前车厢
     */
    public static String DQCX = "dqcx" ;
}
