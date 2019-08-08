package com.lc.klt;

import com.google.gson.Gson;
import com.lc.klt.vo.*;
import com.lc.klt.vo.program.GcVo;
import com.lc.klt.vo.program.Program;
import com.lc.klt.vo.program.Rect;
import com.lc.commons.FtpUtils;
import com.lc.commons.WebTools;
import com.lc.vo.LedVo;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lc.klt.vo.program.Program.*;

public class ledMain {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Administrator\\Desktop\\ledSendDemo\\xuanchuanpian.mp4" ;
        String filePath1 = "C:\\Users\\Administrator\\Desktop\\11.jpg" ;
        String filePath2 = "C:\\Users\\Administrator\\Desktop\\20181102122054212.png" ;
        String filePath3 = "C:\\Users\\Administrator\\Desktop\\ledSendDemo\\22.gif" ;
        String clearUrl = "http://192.168.100.240/api/showToast/"; //清除刚显示时的路径信息
        String showToast = "http://192.168.100.240/api/showtoast.json" ; // 显示刚显示时的路径信息
        Map clearMap = new HashMap();
        clearMap.put("showToast","0");
        //HttpUtil.sendPostJson(clearUrl,new Gson().toJson(clearMap)); // 清除屏幕路径标示
        //System.out.println(HttpUtil.sendGet(showToast)); // 获取屏幕路径标示状态

        List ips = new ArrayList<>();
        ips.add(new LedVo());

        String[] picts = new String[1];
        picts[0] = filePath1;

        String[] videos = new String[1];
        videos[0] = filePath;

        String[] gifs = new String[1];
        gifs[0] = filePath3;

        String[] all = new String[3];
        all[0] = filePath1;
        all[1] = filePath2;
        all[2] = filePath;

        //sendProgramIfNotExist(ips,picts); // 发送图片
        //sendProgram(ips,gifs); // 发送动画图片
        //sendProgram(ips,videos); // 发送视频
        //sendProgram(ips,new ClockVo("0","24",null)); // 发送时钟
        //sendProgram(ips); // 发送分区文字
        //sendProgramIfExist(ips,new TextVo("K123,,我三车厢")); // 发送文字
         //sendProgramBeforUpload(ips,all); // 发全部
        //sendProgram(ips,all); // 发全部
        List<Map<String,String>> vos = new ArrayList<>();
        Map map = new HashMap();
        map.put(StaticCode.IP,"192.168.100.240");
        map.put(StaticCode.CC,"K1");
        map.put(StaticCode.QSZ,"京");
        map.put(StaticCode.ZDZ,"郑");
        map.put(StaticCode.DQCZ,"商");
        map.put(StaticCode.DDSK,"14:58");
        map.put(StaticCode.FCSK,"15:05");
        map.put(StaticCode.ZCX,"1");
        map.put(StaticCode.YCX,"8");
        map.put(StaticCode.DQCX,"4");

        vos.add(map);
        sendTextProgram(vos);

    }


    /**
     * 边上传，边发送非文字类节目  <br/>
     * 会检测文件是否存在
     * @param vo
     * @param filePath
     * @return 未成功的屏
     */
    public static List<LedVo> sendProgramIfNotExist(List<LedVo> ips,String[] filePath){
        List<LedVo> re_list = new ArrayList<>();
        String paramStr = getJsonByPages(new Pages((new Pages.Regions(getListCommonVoByFilePaths(filePath)))));
        for (int i = 0; i < ips.size(); i++) {
            LedVo ledVo = ips.get(i);
            String postUrl = StaticCode.HTTP + ledVo.getIp() + StaticCode.URL + StaticCode.FILENAME + ".vsn" ;
            boolean isUploadSuccess = true ; // 是否文件都上传成功
            if(filePath != null && filePath.length != 0){
                FTPClient ftpClient = FtpUtils.getFtpClient(ledVo.getIp(),21,"","") ;
                for (int j = 0; j < filePath.length; j++) {
                    boolean flag = FtpUtils.uploadFile(ftpClient,"program/"+StaticCode.FILENAME,filePath[j]);
                    if(!flag){
                        re_list.add(ledVo);
                        isUploadSuccess = false ;
                    }
                }
                if(isUploadSuccess){ // 如果 都上传成功，则发送节目
                    //String isSendSuccess = HttpUtil.sendPostJson(postUrl, paramStr); // 发送节目
                    boolean isSendSuccess =  isSuccess(WebTools.postJson(postUrl, paramStr)); // 发送节目
                    if(!isSendSuccess){
                        re_list.add(ledVo);
                    }
                }
                if(ftpClient.isConnected()){
                    try{
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return re_list ;
    }


    /**
     * 先上传文件，再发送非文字类节目  <br/>
     * 会检测文件是否存在
     * @param vo
     * @param filePath
     * @return 未成功的屏
     */
    public static List<LedVo> sendProgramIfNotExistFirstUpload(List<LedVo> ips,String[] filePath){
        List<LedVo> re_list = uploadFileToFtp(ips,filePath); // 未上传成功的集合
        Map<String, LedVo> map = re_list.stream().collect(Collectors.toMap(LedVo::getIp, a -> a,(k1, k2)->k1)); // 转map,key：ip,value:LedVo
        String paramStr = getJsonByPages(new Pages((new Pages.Regions(getListCommonVoByFilePaths(filePath)))));
        for (int i = 0; i < ips.size(); i++) {
            LedVo ledVo = ips.get(i);
            if(map.get(ledVo.getIp()) == null){ // 如果文件没有上传成功的话，就不用发送了
                String postUrl = StaticCode.HTTP + ledVo.getIp() + StaticCode.URL + StaticCode.FILENAME + ".vsn" ;
                boolean isSendSuccess = isSuccess(WebTools.postJson(postUrl, paramStr));
                if(!isSendSuccess){
                    re_list.add(ledVo);
                }
            }
        }
        return re_list ;
    }



    /**
     * 发送非文字类节目 <br/>
     * 前提 文件已存在 ，因为不会检测文件是否存在
     * @param vos IP集
     * @param textVo 发送的文字实体类
     * @return 未成功的屏
     */
    public static List<LedVo> sendProgram(List<LedVo> vos, String[] filePaths){
        List<LedVo> re_list = new ArrayList<>();
        List<CommonVo> pros = getListCommonVoByFilePaths(filePaths);
        String parmJson = getJsonByPages(new Pages(new Pages.Regions(pros))); // 不设置区域，默认是全屏，如果参数设置正确的话
        for (int i = 0; i < vos.size(); i++) {
            LedVo vo = vos.get(i);
            //根据每个屏幕尺寸
            //parmJson = getJsonByPages(new Program.Pages(new Program.Pages.Regions(new Rect("0","0",vo.getWidth()+"",vo.getHeight()+""),textVo)));
            String postUrl = StaticCode.HTTP + vo.getIp() + StaticCode.URL + StaticCode.FILENAME + ".vsn" ;
            //String isSuccess = HttpUtil.sendPostJson(postUrl, parmJson);
            Map resultMap = WebTools.postJson(postUrl, parmJson);
            if(!isSuccess(resultMap)){
                re_list.add(vo);
            }
        }
        return re_list;
    }

    /**
     * 发送所有common类节目  <br/>
     * 如果是视频或图片类，则前提是文件已存在,因为不会检测文件是否存在
     * @param vos IP集
     * @param textVo 发送的文字实体类
     * @return 未成功的屏
     */
    public static List<LedVo> sendProgram(List<LedVo> vos, CommonVo commonVo){
        List<LedVo> re_list = new ArrayList<>();
        String parmJson = getJsonByPages(new Pages(new Pages.Regions(commonVo))); // 不设置区域，默认是全屏，如果参数设置正确的话
        for (int i = 0; i < vos.size(); i++) {
            LedVo vo = vos.get(i);
            //根据每个屏幕尺寸
            //parmJson = getJsonByPages(new Program.Pages(new Program.Pages.Regions(new Rect("0","0",vo.getWidth()+"",vo.getHeight()+""),textVo)));
            String postUrl = StaticCode.HTTP + vo.getIp() + StaticCode.URL + StaticCode.FILENAME + ".vsn" ;
            //String isSuccess = HttpUtil.sendPostJson(postUrl, parmJson);
            Map resultMap = WebTools.postJson(postUrl, parmJson);
            if(!isSuccess(resultMap)){
                re_list.add(vo);
            }
        }
        return re_list;
    }


    /**
     * 发送分区型文字类节目 ，Map参数如下： <br/>
     * cc  车次 <br/>
     * qsz  起始车站 <br/>
     * zdz  终到站车站 <br/>
     * dqcz 当前车站 <br/>
     * ddsk 到达时刻 <br/>
     * fcsk 发车时刻 <br/>
     * zcx 左车厢 <br/>
     * ycx 右车厢 <br/>
     * dqcx 当前车厢 <br/>
     * ip  屏幕IP <br/>
     * @param vos 屏和文字 <br/>
     * @return 所有未发送成功的屏
     */
    public static List<Map<String,String>> sendTextProgram(List<Map<String,String>> vos){
        // 未发送成功的屏
        List<Map<String,String>> re_list = new ArrayList<>();
        // 生成模板
        List<List<Pages.Regions>> regions_ips = createTemplate(vos);

        for (int i = 0; i < vos.size(); i++) {
            String parmJson = getJsonByPages(new Pages(regions_ips.get(i)));
            Map vo = vos.get(i);
            //根据屏幕尺寸
            String postUrl = StaticCode.HTTP + vo.get("ip") + StaticCode.URL + StaticCode.FILENAME + ".vsn" ;
            boolean isSuccess = isSuccess(WebTools.postJson(postUrl, parmJson));
            if(!isSuccess){
                re_list.add(vo);
            }
        }
        return re_list;
    }



    /**
     *  根据 参数  生成 屏幕显示 模板
     * @param vos ip，车次，始发终到站，到达及发车时间，车厢 等信息
     * @return 节目的区域 数据
     */
    private static List<List<Pages.Regions>> createTemplate(List<Map<String, String>> vos) {
        // 所有屏的节目数据
        List<List<Pages.Regions>> regions_ips = new ArrayList<List<Pages.Regions>>();
        for (int i = 0; i < vos.size(); i++) {
            Map<String,String> vo = vos.get(i);
            // 当前屏的分区
            List<Pages.Regions> regions = new ArrayList<Pages.Regions>();

            // 车次
            Pages.Regions re_cc = new Pages.Regions();
            re_cc.setRect(new Rect("0", "0", "15", "16"));
            List<CommonVo> items_cc = new ArrayList<>();
            items_cc.add(new TextVo(vo.get(StaticCode.CC),StaticCode.COLOR_RED,StaticCode.FONT12));
            re_cc.setItems(items_cc);
            regions.add(re_cc);

            // 始发站 - 终到站
            Pages.Regions re_sfzd = new Pages.Regions();
            re_sfzd.setRect(new Rect("15", "0", "15", "16"));
            List<CommonVo> items_sfzd = new ArrayList<>();
            items_sfzd.add(new TextVo(vo.get(StaticCode.QSZ) + " - " + vo.get(StaticCode.ZDZ),StaticCode.COLOR_YELLOW,StaticCode.FONT10));
            re_sfzd.setItems(items_sfzd);
            regions.add(re_sfzd);


            // 当前站
            Pages.Regions re_dqz = new Pages.Regions();
            re_dqz.setRect(new Rect("30", "0", "15", "16"));
            List<CommonVo> items_dqz = new ArrayList<>();
            items_dqz.add(new TextVo(vo.get(StaticCode.DQCZ) + "站",StaticCode.COLOR_GREEN,StaticCode.FONT12));
            re_dqz.setItems(items_dqz);
            regions.add(re_dqz);

            // 到达时间
            Pages.Regions re_ddsj = new Pages.Regions();
            re_ddsj.setRect(new Rect("45", "0", "15", "16"));
            List<CommonVo> items_ddsj = new ArrayList<>();
            items_ddsj.add(new TextVo(vo.get(StaticCode.DDSK) + "到达",StaticCode.COLOR_RED,StaticCode.FONT12));
            re_ddsj.setItems(items_ddsj);
            regions.add(re_ddsj);

            // 发车时间
            Pages.Regions re_fcsj = new Pages.Regions();
            re_fcsj.setRect(new Rect("45", "16", "15", "16"));
            List<CommonVo> items_fcsj = new ArrayList<>();
            items_fcsj.add(new TextVo(vo.get(StaticCode.FCSK) + "发车",StaticCode.COLOR_YELLOW,StaticCode.FONT12));
            re_fcsj.setItems(items_fcsj);
            regions.add(re_fcsj);

            // 车厢顺序
            Pages.Regions re_cxsx = new Pages.Regions();
            re_cxsx.setRect(new Rect("0", "16", "30", "16"));
            List<CommonVo> items_cxsx = new ArrayList<>();
            items_cxsx.add(new TextVo(vo.get(StaticCode.ZCX) + " ←车厢→ " + vo.get(StaticCode.YCX),StaticCode.COLOR_RED,StaticCode.FONT10));
            re_cxsx.setItems(items_cxsx);
            regions.add(re_cxsx);

            // 当前车厢数
            Pages.Regions re_cxcount = new Pages.Regions();
            re_cxcount.setRect(new Rect("30", "16", "15", "16"));
            List<CommonVo> items_cxcount = new ArrayList<>();
            items_cxcount.add(new TextVo("当前"+vo.get(StaticCode.DQCX) + "车",StaticCode.COLOR_WHITE,StaticCode.FONT12));
            re_cxcount.setItems(items_cxcount);
            regions.add(re_cxcount);

            // 将当前屏的分区节目添加
            regions_ips.add(regions);
        }
        return regions_ips;
    }


    /**
     * 根据路径组 生成 commonVo
     * @param filePaths
     * @return
     */
    private static List<CommonVo> getListCommonVoByFilePaths(String[] filePaths){
        List<CommonVo> list = new ArrayList();
        for (int i = 0; i < filePaths.length; i++) {
            String filePath = filePaths[i];
            int pointIndex =  filePath.lastIndexOf(".");//点号的位置
            String fileSuffix = filePath.substring(pointIndex).toLowerCase();//截取文件后缀
            String proPath = ".\\"+ StaticCode.FILENAME + "\\"+ new File(filePath).getName() ;
            CommonVo commonVo  = null;
            if(".jpg".equals(fileSuffix) || ".png".equals(fileSuffix)){
                commonVo = new PictureVo(proPath) ;
            }else if(".mp4".equals(fileSuffix) || ".avi".equals(fileSuffix)|| ".flv".equals(fileSuffix)|| ".rmvb".equals(fileSuffix)|| ".mov".equals(fileSuffix)){
                commonVo = new VideoVo(proPath) ;
            }else if(".gif".equals(fileSuffix)){
                commonVo = new GifVo(proPath) ;
            }else{
                System.err.println(fileSuffix+"格式不支持！");
                continue;
            }
            list.add(commonVo);
        }
        return list;
    }




    /**
     * 根据传入的page生成节目JSON
     * @param vo
     * @return
     */
    private static String getJsonByPages(Pages page){
        GcVo ledVo = new GcVo(new Program(page));
        Map map = new HashMap();
        map.put("Programs",ledVo);
        String paramStr = new Gson().toJson(map);
        return paramStr ;
    }



    /**
     * 将文件上传到屏中
     * @param ips
     * @param filePath
     * @return 未上传成功的屏
     */
    private static List<LedVo> uploadFileToFtp(List<LedVo> ips,String[] filePath){
        List<LedVo> re_list = new ArrayList<>();
        for (int i = 0; i < ips.size(); i++) {
            LedVo ledVo = ips.get(i);
            boolean isSaveSuccess = false ;
            FTPClient ftpClient = FtpUtils.getFtpClient(ledVo.getIp(),21,"","") ;
            for (int j = 0; j < filePath.length; j++) {
                // 固定路径了，根目录下的program文件夹下
                isSaveSuccess = FtpUtils.uploadFile(ftpClient,"program/"+StaticCode.FILENAME,filePath[j]);
                if(!isSaveSuccess){
                    re_list.add(ledVo); // 未上传成功的
                }
            }
            // 断开连接
            if(ftpClient.isConnected()){
                try{
                    ftpClient.logout();
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

        }
        return re_list ;
    }

    /**
     *
     * @param map
     * @return 是否成功
     */
    private static boolean isSuccess(Map resultMap){
        System.out.println("code :" + resultMap.get(WebTools.KEY_STATUS_CODE));
        System.out.println("content :" + resultMap.get(WebTools.KEY_CONTENT));
        if(!"200".equals(resultMap.get(WebTools.KEY_STATUS_CODE))){
            return false ;
        }else{
            return true ;
        }
    }

}
