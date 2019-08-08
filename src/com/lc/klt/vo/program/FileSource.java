package com.lc.klt.vo.program;

/**
 * //文件描述
 */
public class FileSource {
    private String IsRelative = "1"; //是否为相对路径(0：否；1：是)
    private String FilePath; //文件路径


    public FileSource(){

    }

    public FileSource(String path){
        this.FilePath = path ;
    }


    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getIsRelative() {
        return IsRelative;
    }

    public void setIsRelative(String isRelative) {
        IsRelative = isRelative;
    }
}
