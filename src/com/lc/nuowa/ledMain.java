package com.lc.nuowa;


import com.lc.commons.LcUtil;
import novj.platform.vxkit.common.bean.login.LoginResultBean;
import novj.platform.vxkit.common.bean.programinfo.Font;
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ledMain {

    String username = "admin";
    String password = "123456";
    JFrame frame;
    JButton sendtextButten;// 发送文本按钮
    JButton sendVideoButten;// 发送视频按钮
    JTextArea ccText;
    JComboBox cb;
    SearchResult myLed = null;
    String jmPath = "C:\\Users\\Administrator\\Desktop\\ledSendDemo";
    String jmName = "itemTest"; // 节目名称
    int pageId = 0;
    int widgetId_cc = 0;
    int widgetId_bz = 0;
    String MAC = LcUtil.getMacAddress();

    public static void main(String[] args) {
        ledMain main = new ledMain();
        main.keyView();// 界面初始化
    }

    /**
     * 界面初始化
     */
    public void keyView() {
        frame = new JFrame("LED控制器");// 主窗口
        frame.setSize(500, 300);// 主窗口宽高
        frame.setLayout(new FlowLayout());// 主窗口布局
        frame.setResizable(false);// 窗体大小不可变化
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int show_x = (screen.width - frame.getWidth()) / 2;
        int show_y = (screen.height - frame.getHeight()) / 2;
        frame.setLocation(show_x, show_y);// 程序启动显示在桌面中心位置
        frame.setLayout(null);// 布局器采用绝对定位

        JLabel lName1 = new JLabel("广告视频：");// 输入框标题
        String[] heros = new String[]{"铁路宣传片", "广告"};
        cb = new JComboBox(heros);

        lName1.setBounds(20, 12, 80, 30);// X,Y,宽,高
        cb.setBounds(85, 10, 250, 30);// X,Y,宽,高

        sendVideoButten = new JButton("发送");// 按钮
        sendVideoButten.setBounds(390, 10, 70, 30);// X,Y,宽,高


        JLabel lName2 = new JLabel("文本：");// 输入框标题
        lName2.setBounds(20, 100, 60, 30);// X,Y,宽,高

        ccText = new JTextArea();// 文本域
        ccText.setBounds(85, 100, 250, 140);// X,Y,宽,高
        ccText.setLineWrap(true);// 文本域自动换行
        ccText.setText("K1789,5车厢");

        sendtextButten = new JButton("发送");// 按钮
        sendtextButten.setBounds(390, 100, 70, 30);// X,Y,宽,高


        // 初始化PC端
        NovaOpt.GetInstance().initialize(2);

        // 搜索设备
        NovaOpt.GetInstance().searchScreen(new SearchManager.OnScreenSearchListener() {

            @Override
            public void onSuccess(SearchResult searchResult) {
                //查找到设备，详情参见searchResult
                myLed = searchResult;

                // 连接设备
                NovaOpt.GetInstance().connectDevice(myLed, new DefaultOnResultListener() {

                    @Override
                    public void onSuccess(Integer response) {
                        //连接成功

                        // 登录设备
                        NovaOpt.GetInstance().login(myLed.sn, username, password, new OnResultListenerN<LoginResultBean, ErrorDetail>() {

                            @Override
                            public void onSuccess(IRequestBase var1, LoginResultBean response) {
                                //登录成功
                                /*NovaOpt.GetInstance().setScreenPowerState(searchResult.sn, true, new OnResultListenerN<Integer, ErrorDetail>() {
                                    @Override
                                    public void onSuccess(IRequestBase requestBase, Integer response) {
                                        System.out.println("设置成功");
                                    }

                                    @Override
                                    public void onError(IRequestBase requestBase, ErrorDetail error) {
                                        System.out.println(error.description);
                                    }
                                });*/

                                NovaOpt.GetInstance().createProgram(jmName, 192, 128); // 创建节目
                            }

                            @Override
                            public void onError(IRequestBase var1, ErrorDetail error) {
                                //登录失败
                                JOptionPane.showMessageDialog(frame, "登录失败!");
                                System.err.println(error);
                            }
                        });
                    }

                    @Override
                    public void onError(ErrorDetail error) {
                        //连接失败
                        JOptionPane.showMessageDialog(frame, error.description);
                        System.err.println(error);
                    }
                }, wrapper -> {
                    //连接断开
                    JOptionPane.showMessageDialog(frame, "连接被断开了");
                });
            }

            @Override
            public void onError(ErrorDetail errorDetail) {//，详情参见errorDetail
                JOptionPane.showMessageDialog(frame, errorDetail.description);
                System.err.println(errorDetail);
            }
        });

        sendVideoButten.addActionListener(new ActionListener() {// 按钮监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) cb.getSelectedItem();
                String videoPath = "C:\\Users\\Administrator\\Desktop\\ledSendDemo\\xuanchuanpian.mp4";
                if ("广告".equals(item)) {
                    videoPath = "C:\\Users\\Administrator\\Desktop\\ledSendDemo\\gg.mp4";
                }

                NovaOpt.GetInstance().deleteWidget(pageId, widgetId_cc);
                NovaOpt.GetInstance().deleteWidget(pageId, widgetId_bz);
                NovaOpt.GetInstance().deletePage(pageId);
                pageId = NovaOpt.GetInstance().addPage();
                widgetId_cc = NovaOpt.GetInstance().addWidget(pageId, ProgramManager.WidgetMediaType.VIDEO, videoPath);

                NovaOpt.GetInstance().makeProgram(jmPath);

                try {
                    NovaOpt.GetInstance().startTransfer(myLed.sn, jmPath, jmName, MAC, true, new ProgramSendManager.OnProgramTransferListener() {
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

                        }

                        @Override
                        public void onCompleted() {
                            end = System.currentTimeMillis();
                            JOptionPane.showMessageDialog(frame, "发送-接收耗时(ms)：" + (end - start));
                        }

                        @Override
                        public void onAborted() {

                        }
                    });
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        sendtextButten.addActionListener(new ActionListener() {// 按钮监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                String cc = ccText.getText();
                if ("".equals(cc) || cc == null) {
                    JOptionPane.showMessageDialog(frame, "不能发送空文本!");
                    return;
                }
                if (cc.indexOf(",") == -1) {
                    JOptionPane.showMessageDialog(frame, "请输入一组逗号隔开的字符串,比如: Z98,15车厢");
                    return;
                }
                NovaOpt.GetInstance().deleteWidget(pageId, widgetId_cc);
                NovaOpt.GetInstance().deleteWidget(pageId, widgetId_bz);
                NovaOpt.GetInstance().deletePage(pageId);
                pageId = NovaOpt.GetInstance().addPage();

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

                NormalTextBean ccVo = new NormalTextBean(cc.split(",")[0].trim());
                ccVo.setTextAttributes(textAttributes_cc);

                NormalTextBean bzVo = new NormalTextBean(cc.split(",")[1].trim());
                bzVo.setTextAttributes(textAttributes_bz);

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
                NovaOpt.GetInstance().makeProgram(jmPath);

                try {
                    // 发布节目
                    NovaOpt.GetInstance().startTransfer(myLed.sn, jmPath, jmName, MAC, true, new ProgramSendManager.OnProgramTransferListener() {
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
                            JOptionPane.showMessageDialog(frame, "发送-接收失败");
                        }

                        @Override
                        public void onCompleted() {
                            end = System.currentTimeMillis();
                            JOptionPane.showMessageDialog(frame, "发送-接收耗时(ms)：" + (end - start));
                        }

                        @Override
                        public void onAborted() {
                            JOptionPane.showMessageDialog(frame, "发送-接收丢失了");
                        }
                    });
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        frame.add(lName1);
        frame.add(cb);
        frame.add(lName2);
        frame.add(ccText);
        frame.add(sendtextButten);
        frame.add(sendVideoButten);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}