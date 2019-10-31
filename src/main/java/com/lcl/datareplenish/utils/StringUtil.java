package com.lcl.datareplenish.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

public class StringUtil {

    /**
     * @Title trimToEmpty
     * @Description 去掉字符串两端的控制符(control characters, char <= 32) 如果变为null或""，则返回""
     * @Author liuchanglin
     * @Date 2019/10/31 10:30 上午
     * @Param [str]
     * @return java.lang.String
     **/
    public static String trimToEmpty(String str){
        return str == null ? "" : str.trim();
    }

    /**
     * @Title getRandomString
     * @Description 生成随机字符串
     * @Author liuchanglin
     * @Date 2019/10/31 10:30 上午
     * @Param [lenght]
     * @return java.lang.String
     **/
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
     * @Title getEncodeStr
     * @Description 汉字转编码
     * @Author liuchanglin
     * @Date 2019/10/31 10:29 上午
     * @Param [value]
     * @return java.lang.String
     **/
    public static String getEncodeStr(String value) {
        try {
            return URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * @Title isChineseChar
     * @Description
     * 判断一个字符是否是汉字
     * PS：中文汉字的编码范围：[\u4e00-\u9fa5]
     * @Author liuchanglin
     * @Date 2019/10/31 10:29 上午
     * @Param [c]
     * @return boolean
     **/
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    /**
     * @Title getReplaceString
     * @Description 指定位置替换字符串
     * @Author liuchanglin
     * @Date 2019/10/31 10:30 上午
     * @Param [str, replaceStr, startIndex, endIndex]
     * @return java.lang.String
     **/
    public static String getReplaceString(String str,String replaceStr,int startIndex,int endIndex){
        StringBuffer stringBuffer=new StringBuffer(str);
        stringBuffer.replace(startIndex,endIndex,replaceStr);
        return  stringBuffer.toString();
    }

}
