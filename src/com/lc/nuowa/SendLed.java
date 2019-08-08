package com.lc.nuowa;

import com.lc.vo.LedVo;
import com.lc.vo.ResultMsg;
import com.lc.vo.SysConfig;
import novj.platform.vxkit.common.bean.login.LoginResultBean;
import novj.platform.vxkit.common.bean.programinfo.*;
import novj.platform.vxkit.common.bean.search.SearchResult;
import novj.platform.vxkit.common.result.DefaultOnResultListener;
import novj.platform.vxkit.common.result.OnResultListenerN;
import novj.platform.vxkit.handy.api.ProgramSendManager;
import novj.platform.vxkit.handy.api.SearchManager;
import novj.publ.api.NovaOpt;
import novj.publ.api.actions.ProgramManager;
import novj.publ.api.beans.NormalTextBean;
import novj.publ.net.exception.ErrorDetail;
import novj.publ.net.svolley.Request.IRequestBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**  
* @ClassName: SendLed
* @Description: （发送节目到LED）
* @Author： ccy
* @CreateDate： 2019-07-05 11:37:35 
* @Update（更新记录）： userName do something(add/update/delete) at what time 
* @Company:LCKJ(乐程科技)
*/
public class SendLed {
    private static int pageId = 0;  // 节目页
    private static int widgetId_cc = 0; // 节目单元——车次
    private static int widgetId_bz = 0; //节目单元—— 编组

    public static void main(String[] args) {
        List<LedVo> list = new ArrayList<>();
        list.add(new LedVo());
        //SendLed.sendText(list,"K2211,12车厢");
        //SendLed.sendAnimation(list,"C:\\Users\\Administrator\\Desktop\\ledSendDemo\\gg.mp4");
        //SendLed.sendAnimation(list,"C:\\Users\\Administrator\\Desktop\\ledSendDemo\\20181102122054212.png");
        //SendLed.sendAnimation(list,"C:\\Users\\Administrator\\Desktop\\ledSendDemo\\11.gif");
        SendLed.sendAnimation(list,"C:\\Users\\Administrator\\Desktop\\ledSendDemo\\11.pdf");
    }

    /**
     * @Title:
     * @Description:  (初始化每个屏幕)
     * @param：
     * @param leds 屏幕的list
     * @return：
     * @throws：
     * @creatDate： 2019-07-05 11:03:15
     * @Author： ccy
     * @update（更新记录）： userName do something(add/update/delete) at what time
     */
    public static List<SearchResult> init(List<LedVo> leds){
        // 初始化PC端
        NovaOpt.GetInstance().initialize(2);
        List<SearchResult> resultList = new ArrayList<SearchResult>();
        try {
            for (int i = 0; i < leds.size(); i++) {
                LedVo myLed = leds.get(i);
                // 搜索指定Ip设备
                NovaOpt.GetInstance().searchScreen(new SearchManager.OnScreenSearchListener() {
                    @Override
                    public void onSuccess(SearchResult searchResult) {
                        // 连接设备
                        NovaOpt.GetInstance().connectDevice(searchResult, new DefaultOnResultListener() {

                            @Override
                            public void onSuccess(Integer response) {
                                //连接成功,登录设备
                                NovaOpt.GetInstance().login(searchResult.sn, myLed.getUsername(), myLed.getPassword(), new OnResultListenerN<LoginResultBean, ErrorDetail>() {
                                    @Override
                                    public void onSuccess(IRequestBase var1, LoginResultBean response) {
                                        resultList.add(searchResult); // 登录成功后，添加到list
                                        NovaOpt.GetInstance().createProgram(SysConfig.jmName, searchResult.width, searchResult.height); // 创建节目
                                    }
                                    @Override
                                    public void onError(IRequestBase var1, ErrorDetail error) {
                                        //登录失败
                                        System.err.println(error);
                                    }
                                });
                            }

                            @Override
                            public void onError(ErrorDetail error) {
                                //连接失败
                                System.err.println(error);
                            }
                        }, wrapper -> {
                            //连接断开
                        });
                    }

                    @Override
                    public void onError(ErrorDetail errorDetail) {//，详情参见errorDetail
                        System.err.println(errorDetail);
                    }
                },myLed.getIp(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    /**
     * @Title:
     * @Description:  (生成文字类的节目)
     * @param：
     * @return：
     * @throws：
     * @creatDate： 2019-07-05 11:10:10
     * @Author： ccy
     * @update（更新记录）： userName do something(add/update/delete) at what time
     */
    public static void createTextShow(String cc_bz){
        NovaOpt.GetInstance().deleteWidget(pageId, widgetId_cc); // 删除单元
        NovaOpt.GetInstance().deleteWidget(pageId, widgetId_bz);
        NovaOpt.GetInstance().deletePage(pageId); // 删除节目页

        pageId = NovaOpt.GetInstance().addPage(); //生成页面

        /**
         * 设置车次字体
         */
        int fontSize = 27 ; // 字体大小
        TextAttributes textAttributes_cc = new TextAttributes();
        textAttributes_cc.setBackgroundColor("#00000000");
        textAttributes_cc.setTextColor("#FFFF0000");
        Font font_cc = new Font();
        List<String> family = new ArrayList();
        family.add("Arial");
        font_cc.setSize(fontSize);
        font_cc.setStyle("NORMAL");
        font_cc.setUnderline(false);
        textAttributes_cc.setFont(font_cc);
        textAttributes_cc.setShadowEnable(false);
        textAttributes_cc.setShadowRadius(10);
        textAttributes_cc.setShadowDx(2);
        textAttributes_cc.setShadowDy(2);

        /**
         * 设置编组字体
         */
        TextAttributes textAttributes_bz = new TextAttributes();
        textAttributes_bz.setBackgroundColor("#00000000");
        textAttributes_bz.setTextColor("#FFFF0000");
        Font font_bz = new Font();
        List<String> family2 = new ArrayList();
        family2.add("Arial");
        font_bz.setSize(fontSize);
        font_bz.setStyle("NORMAL");
        font_bz.setUnderline(false);
        textAttributes_bz.setFont(font_bz);
        textAttributes_bz.setShadowEnable(false);
        textAttributes_bz.setShadowRadius(10);
        textAttributes_bz.setShadowDx(2);
        textAttributes_bz.setShadowDy(2);

        NormalTextBean ccVo = new NormalTextBean(cc_bz.split(",")[0].trim());
        ccVo.setTextAttributes(textAttributes_cc);

        NormalTextBean bzVo = new NormalTextBean(cc_bz.split(",")[1].trim());
        bzVo.setTextAttributes(textAttributes_bz);

        /**
         * 车次的样式
         */
        widgetId_cc = NovaOpt.GetInstance().addWidget(pageId, ProgramManager.WidgetMediaType.ARCH_TEXT, ccVo);
        Widget widget_cc = NovaOpt.GetInstance().getWidgetParam(pageId, widgetId_cc);
        // 分区
        widget_cc.setDisplayRatio("CUSTOM");
        widget_cc.setLayout(new Layout("0px", "0px", "85px", "128px"));

        // 垂直和水平居中
        MetaDataArchText metaDataArchText_cc = (MetaDataArchText) widget_cc.getMetadata();
        metaDataArchText_cc.getContent().getParagraphs().get(0).setHorizontalAlignment("CENTER");
        metaDataArchText_cc.getContent().getParagraphs().get(0).setVerticalAlignment("CENTER");
        metaDataArchText_cc.getContent().getDisplayStyle().setSingleLine(true);
        NovaOpt.GetInstance().setWidgetParam(pageId, widgetId_cc, widget_cc);


        /**
         * 编组的样式
         */
        widgetId_bz = NovaOpt.GetInstance().addWidget(pageId, ProgramManager.WidgetMediaType.ARCH_TEXT, bzVo);
        Widget widget_bz = NovaOpt.GetInstance().getWidgetParam(pageId, widgetId_bz);
        // 分区
        widget_bz.setDisplayRatio("CUSTOM");
        widget_bz.setLayout(new Layout("85px", "0", "106px", "128px"));

        // 垂直和水平居中
        MetaDataArchText metaDataArchText_bz = (MetaDataArchText) widget_bz.getMetadata();
        metaDataArchText_bz.getContent().getParagraphs().get(0).setHorizontalAlignment("CENTER");
        metaDataArchText_bz.getContent().getParagraphs().get(0).setVerticalAlignment("CENTER");
        metaDataArchText_bz.getContent().getDisplayStyle().setSingleLine(true);
        NovaOpt.GetInstance().setWidgetParam(pageId, widgetId_bz, widget_bz);


        // 生成节目
        NovaOpt.GetInstance().makeProgram(SysConfig.jmPath);
    }



    /**
     * @Title:
     * @Description:  (生成动画类的节目)
     * @param：
     * @param path 路径
     * @return：
     * @throws：
     * @creatDate： 2019-07-05 11:22:39
     * @Author： ccy
     * @update（更新记录）： userName do something(add/update/delete) at what time
     */
    public static void createPathShow(String path){
        // 删除 原本的
        NovaOpt.GetInstance().deleteWidget(pageId, widgetId_cc);
        NovaOpt.GetInstance().deleteWidget(pageId, widgetId_bz);
        NovaOpt.GetInstance().deletePage(pageId);

        pageId = NovaOpt.GetInstance().addPage();
        int pointIndex = path.lastIndexOf(".");
        String fileSuffix = path.substring(pointIndex).toLowerCase();//截取文件后缀
        if(".jpg".equals(fileSuffix) || ".png".equals(fileSuffix)){
            widgetId_cc = NovaOpt.GetInstance().addWidget(pageId, ProgramManager.WidgetMediaType.PICTURE, path);
        }else if(".avi".equals(fileSuffix) || ".mp4".equals(fileSuffix) || ".rmvb".equals(fileSuffix)){
            widgetId_cc = NovaOpt.GetInstance().addWidget(pageId, ProgramManager.WidgetMediaType.VIDEO, path);
        }else if(".gif".equals(fileSuffix)){
            widgetId_cc = NovaOpt.GetInstance().addWidget(pageId, ProgramManager.WidgetMediaType.GIF, path);
        }else if(".pdf".equals(fileSuffix)){
            widgetId_cc = NovaOpt.GetInstance().addWidget(pageId, ProgramManager.WidgetMediaType.ARCH_TEXT, path);
        }else{
            widgetId_cc = NovaOpt.GetInstance().addWidget(pageId, ProgramManager.WidgetMediaType.STREAM, path);
        }
        // 生成节目
        NovaOpt.GetInstance().makeProgram(SysConfig.jmPath);
    }



    /**
     * @Title:
     * @Description:  (发送文本)
     * @param： leds
     * @param cc_bz
     * @return： ResultMsg
     * @throws：
     * @creatDate： 2019-07-05 11:19:43
     * @Author： ccy
     * @update（更新记录）： userName do something(add/update/delete) at what time
     */
    public static ResultMsg sendText (List<LedVo> leds, String cc_bz){
        if ("".equals(cc_bz) || cc_bz == null) {
            return ResultMsg.errorWithData("不能发送空文本!");
        }
        if (cc_bz.indexOf(",") == -1) {
            return ResultMsg.errorWithData("请输入一组逗号隔开的字符串,比如: Z98,15车厢!");
        }
        if (cc_bz.length() > 10) {
            return ResultMsg.errorWithData("输入长度过长，10字符之内！");
        }
        // 所有查找到的LED屏幕
        List<SearchResult> resultList = init(leds);
        try {
            Thread.sleep(SysConfig.time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(resultList.size() != 0){
            // 生成节目清单
            createTextShow(cc_bz);
        }else{
            return ResultMsg.errorWithData("没有查找到可用的屏!");
        }
        try {
            // 发布节目
            for (int i = 0; i < resultList.size(); i++) {
                SearchResult myLed = resultList.get(i);

                NovaOpt.GetInstance().startTransfer(myLed.sn, SysConfig.jmPath, SysConfig.jmName, SysConfig.MAC, true, new ProgramSendManager.OnProgramTransferListener() {
                    Long start = 0L;
                    Long end = 0L;

                    @Override
                    public void onStarted() {
                        start = System.currentTimeMillis();
                    }

                    @Override
                    public void onTransfer(long l, long l1) {

                    }

                    @Override
                    public void onError(ErrorDetail errorDetail) {
                        System.err.println(myLed.ipAddress + "发送-接收失败!");
                    }

                    @Override
                    public void onCompleted() {
                        end = System.currentTimeMillis();
                        System.err.println(myLed.ipAddress + "发送-接收耗时(ms)：" + (end - start));
                    }

                    @Override
                    public void onAborted() {
                        System.err.println(myLed.ipAddress + "发送-接收丢失了");
                    }
                });
            }
            return ResultMsg.successWithData(null);
        } catch (IOException e1) {
            e1.printStackTrace();
            return ResultMsg.errorWithData("发送-接收失败!");
        }
    }




    /**
     * @Title:
     * @Description:  (发送动画类)
     * @param：
     * @param path  动画路径
     * @return： ResultMsg
     * @throws：
     * @creatDate： 2019-07-05 11:26:46
     * @Author： ccy
     * @update（更新记录）： userName do something(add/update/delete) at what time
     */
    public static ResultMsg sendAnimation (List<LedVo> leds,String path){
        if ("".equals(path) || path == null) {
            return ResultMsg.errorWithData("路径不能为空！");
        }
        // 所有查找到的LED屏幕
        List<SearchResult> resultList = init(leds);
        try {
            Thread.sleep(SysConfig.time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(resultList.size() != 0){
            // 生成节目清单
            createPathShow(path);
        }else{
            return ResultMsg.errorWithData("没有查找到可用的屏！");
        }

        try {
            // 发布节目
            for (int i = 0; i < resultList.size(); i++) {
                SearchResult myLed = resultList.get(i);

                NovaOpt.GetInstance().startTransfer(myLed.sn, SysConfig.jmPath, SysConfig.jmName, SysConfig.MAC, true, new ProgramSendManager.OnProgramTransferListener() {
                    Long start = 0L;
                    Long end = 0L;
                    @Override
                    public void onStarted() {
                        start = System.currentTimeMillis();
                    }

                    @Override
                    public void onTransfer(long l, long l1) {

                    }

                    @Override
                    public void onError(ErrorDetail errorDetail) {
                        System.err.println(myLed.ipAddress + "发送-接收失败!");
                    }

                    @Override
                    public void onCompleted() {
                        end = System.currentTimeMillis();
                        System.err.println(myLed.ipAddress + "发送-接收耗时(ms)：" + (end - start));
                    }

                    @Override
                    public void onAborted() {
                        System.err.println(myLed.ipAddress + "发送-接收丢失了");
                    }
                });
            }
            return ResultMsg.successWithData(null);
        } catch (IOException e1) {
            e1.printStackTrace();
            return ResultMsg.errorWithData("发送-接收失败");
        }
    }

}
