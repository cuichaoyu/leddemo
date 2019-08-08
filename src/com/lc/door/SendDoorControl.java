package com.lc.door;

import com.lc.commons.Crc16Util;
import com.lc.commons.ThreadPoolUtil;
import com.lc.commons.WebTools;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import static java.lang.System.in;


/**
* @ClassName: SendDoorControl
* @Description:  TODO（控制门）
* @Author： ccy
* @CreateDate： 2019-08-01 12:02:20
* @Update（更新记录）： userName do something(add/update/delete) at what time
* @Company:LCKJ(乐程科技)
*/
public class SendDoorControl {
    public static void main(String[] args) {
        String host = "192.168.100.136";
        int port = 54321;
        sendDoorCommandByIp(host,port,12);
    }

   /**
    * @Title:
    * @Description: TODO (发送控制门的指令)有返回值
    * @param host    门的IP
    * @param port    门的端口
    * @param command   指令 0x0A（打开） | 0x0B（关闭） | 0x0C（查询）
    * @return ： 当前股道所有门的状态,1-20，false:关闭 ， true:打开
    * @throws：
    * @creatDate： 2019-08-01 11:51:47
    * @Author： ccy
    * @update（更新记录）： userName do something(add/update/delete) at what time
    */
    public static boolean[] sendDoorCommandByIp(String host,int port,int command) {
        try {
            Future<boolean[]> messageFuture = ThreadPoolUtil.submit(new Callable<boolean[]>() {
                @Override
                public boolean[] call() throws Exception {
                    byte[] datas =  new byte[6];  // 需要CRC校验的数据区
                    datas[0] = 0x24 ; // $
                    datas[1] = 0x44 ;// D
                    datas[2] = (byte)0xFF  ;
                    datas[3] = (byte) command ; // 0x0A（打开） | 0x0B（关闭） | 0X0C（查询）
                    datas[4] = (byte)0xFF ;
                    datas[5] = (byte)0xFF ;

                    // CRC校验码
                    byte[] n_data = Crc16Util.getCrc16(datas);

                    //结束位
                    byte[] ends = {0x0A,0x0D};

                    // 要发送的数据
                    byte[] sendByte = ArrayUtils.addAll(datas,n_data);
                    sendByte = ArrayUtils.addAll(sendByte,ends);

                    System.out.println("Socket发送数据："+Arrays.toString(sendByte));

                    boolean[]  resultDate = new boolean[20];
                    Socket s = null ;
                    try {
                        s = new Socket(host, port);
                        // 发送
                        s.getOutputStream().write(sendByte);
                        // 发送完毕
                        s.shutdownOutput();
                        InputStream in = s.getInputStream();

                        int r = -1;
                        byte[] data = new byte[26];
                        while ((r = in.read(data)) != -1) {

                        }
                        // 返回结果
                        resultDate = receiveDoorData(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (in != null){
                            in.close();
                        }
                        if(s != null){
                            s.close();
                        }
                    }
                    return resultDate;
                }
            });

            // 获取线程返回数据
            boolean[] doorState = messageFuture.get();
            System.out.println("线程返回数据："+ Arrays.toString(doorState));
            return doorState ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }



    /**
     * @Title:
     * @Description: TODO (发送控制门的指令，无返回值)
     * @param host    门的IP
     * @param port    门的端口
     * @param command   指令 0x0A（打开） | 0x0B（关闭） | 0x0C（查询）
     * @return ： 当前股道所有门的状态,1-20，false:关闭 ， true:打开
     * @throws：
     * @creatDate： 2019-08-01 11:51:47
     * @Author： ccy
     * @update（更新记录）： userName do something(add/update/delete) at what time
     */
    public static void sendDoorCommandByIpTemp(String host,int port,int command) {
        try {
            ThreadPoolUtil.execute(new Runnable() {
                @Override
                public void run() {
                    byte[] datas =  new byte[6];  // 需要CRC校验的数据区
                    datas[0] = 0x24 ; // $
                    datas[1] = 0x44 ;// D
                    datas[2] = (byte)0xFF  ;
                    datas[3] = (byte) command ; // 0x0A（打开） | 0x0B（关闭） | 0X0C（查询）
                    datas[4] = (byte)0xFF ;
                    datas[5] = (byte)0xFF ;

                    // CRC校验码
                    byte[] n_data = Crc16Util.getCrc16(datas);

                    //结束位
                    byte[] ends = {0x0A,0x0D};

                    // 要发送的数据
                    byte[] sendByte = ArrayUtils.addAll(datas,n_data);
                    sendByte = ArrayUtils.addAll(sendByte,ends);

                    System.out.println("Socket发送数据："+Arrays.toString(sendByte));

                    boolean[]  resultDate = new boolean[20];
                    Socket s = null ;
                    try {
                        s = new Socket(host, port);
                        // 发送
                        s.getOutputStream().write(sendByte);
                        // 发送完毕
                        s.shutdownOutput();
                        InputStream in = s.getInputStream();

                        int r = -1;
                        byte[] data = new byte[26];
                        while ((r = in.read(data)) != -1) {

                        }
                        // 返回结果
                        resultDate = receiveDoorData(data);

                        // TODO 通知结果

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (in != null){
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(s != null){
                            try {
                                s.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收 协议
     * @param bytes
     */
    public static boolean[] receiveDoorData(byte[] bytes) {
        // 门状态，false:关闭 ，true:打开
        boolean[] doorState  = new boolean[20];
        byte[] datas = ArrayUtils.subarray(bytes,0,22); // 除结束位和CRC校验码后的字节数据
        byte[] crcData = ArrayUtils.subarray(bytes,22,24); // CRC校验码
        byte[] endData = ArrayUtils.subarray(bytes,24,26); // 结束位

        if( (datas[0] != 0x24 && datas[1] != 0x44) || (endData[0] != 0x0A && endData[1] != 0x0D)){
            System.err.println("不是完整数据！");
            return null;
        }
        byte[] crcNavitedata = Crc16Util.getCrc16(datas); // 本地校验的CRC码
        if(crcNavitedata[0] != crcData[0] && crcNavitedata[1] != crcData[1]){
            System.err.println("本地校验CRC码错误！");
            return null;
        }
        // 从第三个开始，除起始位,保留门的状态
        for (int i = 2; i < datas.length; i++) {
            boolean[] v = getBitBooleanArrayFromByte(bytes[i]) ;
            doorState[i - 2] = v[v.length - 1] ;
        }

        System.out.print("socket接收数据: ");
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(intToHex(bytes[i]) +"  ");
        }
        System.out.println();
        System.out.println("转化后的接收数据："+Arrays.toString(doorState));
        return doorState;
    }




    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */
    public static byte[] getBitArrayFromByte(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte)(b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */
    public static boolean[] getBitBooleanArrayFromByte(byte b) {
        boolean[] array = new boolean[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte)(b & 1) == 0 ? false : true;
            b = (byte) (b >> 1);
        }
        return array;
    }

    /**
     * ASCII码 转 string
     * @param value
     * @return
     */
    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    public static String intToHex(int n) {
        //StringBuffer s = new StringBuffer();
        StringBuilder sb = new StringBuilder(8);
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            sb = sb.append(b[n%16]);
            n = n/16;
        }
        a = sb.reverse().toString();
        return a;
    }
}
