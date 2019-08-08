package com.lc.vo;

import com.lc.commons.LcUtil;

import java.io.File;
import java.io.IOException;

/**
 * 一些配置项
 */
public class SysConfig {
    public static final String jmPath = "D:\\ledSendDemo"; // 生成后的节目路径
    public static final String jmName = "车次编组演示"; // 节目名称
    public static final String MAC = LcUtil.getMacAddress(); //本地MAC地址
    public static final int time = 2000; //查找屏的时间

    static {
        // 如果本地没有这个文件夹，则生成
        File demoFile = new File(jmPath);
        if(!demoFile.exists()) {
            try {
                demoFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
