package com.qlm.datareplenish.utils;

import java.util.Random;

public class MathUtil {

	private static Random random = new Random();
	
	public static int getValue(int step){
		return random.nextInt(step);
	}
	public static int positive(int number){
		number = number < 0 ? (number*(-1)) : number;
		return number;
	}
	
	public static void main(String[] args) {
		int d = -1251880588;
		d = positive(d);
		System.out.println(d);
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
}
