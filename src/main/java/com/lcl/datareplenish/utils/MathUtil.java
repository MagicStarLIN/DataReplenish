package com.lcl.utils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: MathUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/10/28 3:26 下午
 */
public class MathUtil {
	private static Random random = new Random();

	public static int getValue(int step){
		return random.nextInt(step);
	}
	public static int positive(int number){
		number = number < 0 ? (number*(-1)) : number;
		return number;
	}

	public static int boolTypeRandom (){
		return new java.util.Random().nextBoolean() ? 1 : 0;
	}
	public static double getDoubleRandomNum() {
		return Math.random();
	}
	/**
	 * @Title: getRandomNum
	 * @Description: 获取随机数
	 * @return int    返回类型
	 * @date 2019年2月12日 下午5:16:39
	 * @param length  随机数的位数
	 * @return
	 */
	public static int getRandomNum(int length){
		return (int) ((Math.random() * 9 + 1) * (Math.pow(10,length-1)));
	}
	/**
	 * @Title hexStringToByte
	 * @Description 十六进制转二进制byte数组
	 * @Author liuchanglin
	 * @Date 2019/10/28 4:39 下午
	 * @Param [hex]
	 * @return byte[]
	 **/
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789abcdef".indexOf(c);
		return b;
	}

	/**
	 * @Title byteArrToHexStr
	 * @Description byte[]类型数组转为十六进制String
	 * @Author liuchanglin
	 * @Date 2019/10/28 4:39 下午
	 * @Param [arrB]
	 * @return java.lang.String
	 **/
	private static String byteArrToHexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
//        11111110 11101111 11110101
//       System.out.println(bytesToHexString(hexStringToByte("feeff5")));
		for (int i = 0; i < 20; i++) {
			System.err.println(getRandomMaxten());
		}
	}

	/**
	 * @Title hexStr2Byte
	 * @Description 字符串十六进制转byte二进制
	 * @Author liuchanglin
	 * @Date 2019/10/28 4:52 下午
	 * @Param [hex]
	 * @return byte[]
	 **/
	public static byte[] hexStr2Byte(String hex) {
		ByteBuffer bf = ByteBuffer.allocate(hex.length() / 2);
		for (int i = 0; i < hex.length(); i++) {
			String hexStr = hex.charAt(i) + "";
			i++;
			hexStr += hex.charAt(i);
			byte b = (byte) Integer.parseInt(hexStr, 16);
			bf.put(b);
		}
		return bf.array();
	}

	/**
	 * @Title bytesToHexString
	 * @Description byte数组转换十六进制str
	 * @Author liuchanglin
	 * @Date 2019/10/28 4:53 下午
	 * @Param [src]
	 * @return java.lang.String
	 **/
	public static String bytesToHexString(byte[] src){
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);

			stringBuilder.append(i + ":");

			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv + ";");
		}
		return stringBuilder.toString();
	}

	public static int getRandomMaxten() {
		return (int) Math.ceil(getDoubleRandomNum() * 9);
	}




}
