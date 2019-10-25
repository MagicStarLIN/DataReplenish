package com.qlm.datareplenish.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

public class StringUtil {

    /**
     * 去掉字符串两端的控制符(control characters, char <= 32) 如果变为null或""，则返回""
     * @param str
     * @return String 过滤后的字符串
     */
    public static String trimToEmpty(String str){
        return str == null ? "" : str.trim();
    }

    /**
     * @Description: 生成随机字符串
     * @return
     */
    public static String getRandomString(int lenght){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<lenght;i++){
            int num=random.nextInt(62);
            sb.append(str.charAt(num));
        }
        return sb.toString();
    }

    /**
     * @Title: getEncodeStr
     * @Description: 汉字转编码
     * @return String    返回类型
     * @date 2019年3月1日 下午2:03:19
     * @param value
     * @return
     */
    public static String getEncodeStr(String value) {
        try {
            return URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 判断一个字符是否是汉字
     * PS：中文汉字的编码范围：[\u4e00-\u9fa5]
     * @param c 需要判断的字符
     * @return 是汉字(true), 不是汉字(false)
     */
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    /**
     * 指定位置替换字符串
     * @param str
     * @param replaceStr
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static String getReplaceString(String str,String replaceStr,int startIndex,int endIndex){
        StringBuffer stringBuffer=new StringBuffer(str);
        stringBuffer.replace(startIndex,endIndex,replaceStr);
        return  stringBuffer.toString();
    }

}
