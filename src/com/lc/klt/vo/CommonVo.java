package com.lc.klt.vo;

import com.lc.klt.vo.program.FileSource;


/**
 * 基础类
 */
public class CommonVo {
    /**
     * 0,			//非法类型
     * 1,			//声音
     * 2,			//图片
     * 3,			//视频
     * 4,			//单行文本
     * 5,			//多行文本
     * 6,			//Gif
     * 7,			//swf
     * 8,			//TV
     * 9,			//时钟
     * 10,			//流媒体
     * 11,			//Word
     * 12,			//Excel
     * 13,			//PowerPoint
     * 14,			//天气预报
     * 15,			//倒计时
     * 16,			//表格
     * 17,			//数据库
     * 20,			//有格式文本
     * 21,			//湿度
     * 22,			//温度
     * 23,			//噪声
     * 24,		//空气污染指数
     * 25,		//天敏系列卡支持SAA7134 SAA7130 视音频主芯片
     * 26,		//桌面区域
     * 27,		//网页
     * 28,		//烟雾
     * 29，       //亮度
     * 	65,			//外部程序
     * 99,			//体育比分
     * 100,		//内存图像
     * 101,		//多页中的一页，内部使用
     */
    public  String Type ; // 素材类型
    public  String BackColor ; // 素材背景颜色，值为ARGB值，格式：0xFF000000（00000000才为透明）
    public  FileSource FileSource; // 文件描述
    public  String Duration;//素材播放时长（毫秒）

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getBackColor() {
        return BackColor;
    }

    public void setBackColor(String backColor) {
        BackColor = backColor;
    }

    public FileSource getFileSource() {
        return FileSource;
    }

    public void setFileSource(FileSource fileSource) {
        this.FileSource = fileSource;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }
}
