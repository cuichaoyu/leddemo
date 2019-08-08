package com.lc.commons;


import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * util接口
 * 
 * @author rambo
 * 
 * @since Dec 22, 2014
 * 
 * yzp
 */
public class ToolUtils {
	/**
	 * 获得32位字符串，唯一标记
	 * @return
	 */
    public static String randomUUID32() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    /**
	 * 获得yyyy-MM-dd格式的当前日期
	 * @return
	 */
    public static String nowDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    /**
	 * 获得yyyy-MM格式的年月
	 * @return
	 */
    public static String nowMonth() {
        return new SimpleDateFormat("yyyy-MM").format(new Date());
    }
    /**
	 * 获得yyyy格式的年
	 * @return
	 */
    public static String nowYear() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }
	/**
	 * 获得yyyy-MM-dd HH:mm:ss格式的当前时间
	 * @return
	 */
    public static String nowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    /**
	 * 将时间转换成MM-dd HH:mm:ss格式的数据
	 * @param time
	 * @return
	 */
    public static String nowTime(long time) {
        return new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date(time));
    }
    /**
	 * 获得yyyy-MM-dd HH:mm:ss.SSS格式的当前时间
	 * @return
	 */
    public static String nowTimeFFF() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
    /**
	 * 获得yyyyMMddHHmmss格式的当前时间
	 * @return
	 */
    public static String nowTimeNum() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
    
    /**
     * 
     * @author king
     * @ceatetime 2017年2月9日 上午11:14:03
     * @description 	获得UTC时间，格式化，有分隔符
     * @return
     *
     */
    public static String nowUTCTime() {
    	
    	Calendar calendar = Calendar.getInstance();
    	
    	int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
    	int dstOffset = calendar.get(Calendar.DST_OFFSET);
    	
    	calendar.add(Calendar.MILLISECOND, -(zoneOffset+dstOffset));
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
        return sdf.format(calendar.getTime());
    }
    
    /**
     * 
     * @author king
     * @ceatetime 2017年2月9日 上午11:13:35
     * @description 	获得UTC时间戳 Long
     * @return
     *
     */
    public static long nowUTCTimeLong() {
    	
    	Calendar calendar = Calendar.getInstance();
    	
    	int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
    	int dstOffset = calendar.get(Calendar.DST_OFFSET);
    	
    	calendar.add(Calendar.MILLISECOND, -(zoneOffset+dstOffset));
    	
    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
        return calendar.getTimeInMillis();
    }
    
    /**
     * 
     * @author king
     * @ceatetime 2017年2月9日 上午11:13:01
     * @description 	获得UTC的时间戳字符串
     * @return
     *
     */
    public static String nowUTCTimeLongStr() {

		Calendar calendar = Calendar.getInstance();

		int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
		int dstOffset = calendar.get(Calendar.DST_OFFSET);

		calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		return calendar.getTimeInMillis()+"";
	}
    
    /**
     * 
     * @author king
     * @ceatetime 2017年2月13日 上午11:40:49
     * @description 	将传入的日期转换为UTC Long 类型
     * @param date		yyyy-MM-dd HH:mm:ss
     * @return
     *
     */
    public static String nowUTCTimeLongStr(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try{
			Date d = sdf.parse(date);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
			int dstOffset = calendar.get(Calendar.DST_OFFSET);

			calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
			
			return calendar.getTime()+"";
		}catch(Exception e){
		}
		return "";
	}
    
    /**
     * 
     * @author king
     * @ceatetime 2017年2月9日 上午11:12:31
     * @description 	获得几天之前的UTC时间
     * @param num		天数
     * @return
     *
     */
	public static String beforeUTCTimeLongStr(int num) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -num);

		int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
		int dstOffset = calendar.get(Calendar.DST_OFFSET);

		calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		return calendar.getTimeInMillis()+"";
	}
	
    /**
     * 
     * @author king
     * @ceatetime 2017年2月9日 上午11:11:29
     * @description 		获得Utc时间，没有分隔符
     * @return
     *
     */
    public static String nowUTCTimeNum() {
    	
    	Calendar calendar = Calendar.getInstance();
    	
    	int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
    	int dstOffset = calendar.get(Calendar.DST_OFFSET);
    	
    	calendar.add(Calendar.MILLISECOND, -(zoneOffset+dstOffset));
    	
        return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
    }
    /**
	 * 将对象转成String类型
	 * @param obj
	 * @return
	 */
    public static String getString(Object obj){
    	if(obj!=null){
    		return obj.toString();
    	}
    	return "";
    }
    /**
	 * 将对象转成int
	 * @param obj
	 * @return
	 */
    public static int getInt(Object obj){
    	if(obj!=null){
    		try{
    			return Integer.parseInt(obj.toString());
    		}catch(Exception e){
    			return 0;
    		}
    	}
    	return 0;
    }
    /**
	 * 将对象转成Long型数据
	 * @param obj
	 * @return
	 */
    public static long getLong(Object obj){
    	if(obj!=null){
    		try{
    			return Long.parseLong(obj.toString());
    		}catch(Exception e){
    			return 0;
    		}
    	}
    	return 0;
    }
    /**
	 * 将对象转成Float类型
	 * @param obj
	 * @return
	 */
    public static float getFloat(Object obj){
    	if(obj!=null){
    		try{
    			return Float.parseFloat(obj.toString());
    		}catch(Exception e){
    			return 0;
    		}
    	}
    	return 0;
    }
    /**
	 * 将对象转成Double型数据
	 * @param obj
	 * @return
	 */
    public static double getDoubal(Object obj){
    	if(obj!=null){
    		try{
    			return Double.parseDouble(obj.toString());
    		}catch(Exception e){
    			return 0;
    		}
    	}
    	return 0;
    }
    /**
     * 生成MD5码
     * 
     *@param s
     *@return
     *
     *@author king 
     *
     *@since 2015年6月3日  下午4:25:42
     *
     */
    public final static String MD5(String s) {
    	//如果为空，则直接返回
    	if(null==s){
    		return null;
    	}
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要12
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
	 * 返回字符串数据内容，主要是判断为null时，返回空字符串
	 * @param msg
	 * @return
	 */
    public static String convertString(String msg){
    	if(msg==null||msg.toLowerCase().equals("null")){
    		msg = "";
		}else{
			msg = msg.trim();
		}
    	return msg;
    }
    /**
	 * 将字符串转int，为空或者null时返回0
	 * @param msg
	 * @return
	 */
    public static int convertInt(String msg){
    	int result;
    	try{
    		result = Integer.parseInt(msg);
		}catch(Exception e){
			result = 0;
		}
    	return result;
    }
    /**
	 * 分页查询时，将字符串转成当前页，为空或者null时返回1
	 * @param msg
	 * @return
	 */
    public static int convertPage(String msg){
    	int result =1;
    	try{
    		result = Integer.parseInt(msg);
		}catch(Exception e){
			result = 1;
		}
    	return result;
    }
    /**
	 * 分页查询时，将字符串转成当前查询的行数，为空或者null时返回20，表示一次查询20条
	 * @param msg
	 * @return
	 */
    public static int convertRow(String msg){
    	int result = 20;
    	try{
    		result = Integer.parseInt(msg);
		}catch(Exception e){
			result = 20;
		}
    	return result;
    }
    
    /**
     * 
     * @author king
     * @ceatetime 2017年6月28日 上午9:44:34
     * @description 	生成随机验证码
     * @param num		验证码的位数
     * @return
     *
     */
    public static String getCheckCode(int num){
		String code = "";
		
		String[] alphanumber = new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z"};
		
		for(int i=0;i<num;i++){
			int mod = (int) ((Math.random()*1000)%34);
			code+=alphanumber[mod];
		}
		
		return code;
	}
    
    /**
     * 
     * @author king
     * @ceatetime 2017年6月28日 下午3:00:28
     * @description 		将密码转换大写后，再进行Md5加密
     * @param password
     * @return
     *
     */
    public static String getMd5Password(String password){
    	if(null!=password){
    		return MD5(password.toUpperCase());
    	}
    	
    	return "";
    }
    

    /**
	 * 获得报警数据
	 * @param data
	 * @return
	 */
    public static int getWarningData(String data){
    	
    	String wData = data.substring(6, 10);
		int x1 = 0;
		int x2 = 0;
		try{
			x1 = Integer.parseInt(wData.substring(0, 2))<<8;
		}catch(Exception e){
		}
		try{
			x2 = Integer.parseInt(wData.substring(2, 4));
		}catch(Exception e){
		}
    	
    	return x1+x2;
    }
    
    /**
	 * 获得CRC校验码
	 * @param str
	 * @return
	 */
	public static String getCrc16(String str) {

		int len = str.length() / 2;
		int[] arr_buff = new int[len];

		for (int i = 0; i < len; i++) {
			System.out.println(str.substring(i * 2, i * 2 + 2));
			arr_buff[i] = Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
		}

		// 预置 1 个 16 位的寄存器为十六进制FFFF, 称此寄存器为 CRC寄存器。
		int crc = 0xFFFF;
		int i, j;
		for (i = 0; i < len; i++) {
			// 把第一个 8 位二进制数据 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器
			crc = ((crc & 0xFF00) | (crc & 0x00FF) ^ (arr_buff[i] & 0xFF));
			for (j = 0; j < 8; j++) {
				// 把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位
				if ((crc & 0x0001) > 0) {
					// 如果移出位为 1, CRC寄存器与多项式A001进行异或
					crc = crc >> 1;
					crc = crc ^ 0xA001;
				} else
					// 如果移出位为 0,再次右移一位
					crc = crc >> 1;
			}
		}

		int hi = ((crc & 0xFF00) >> 8); // 高位置
		int lo = (crc & 0x00FF); // 低位置

		String hiStr = Integer.toHexString(hi);
		String loStr = Integer.toHexString(lo);

		if (hiStr.length() < 2) {
			hiStr = "0" + hiStr;
		}

		if (loStr.length() < 2) {
			loStr = "0" + loStr;
		}

		return (loStr + hiStr).toUpperCase();
	}


}
