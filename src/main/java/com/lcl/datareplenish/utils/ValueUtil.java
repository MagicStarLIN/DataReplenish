package com.lcl.datareplenish.utils;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ValueUtil
 * @Description: 线性求值
 * @date 2019/10/28 4:39 下午
 */
public class ValueUtil {
    /**
     * @Title LinearEvaluation
     * @Description 向量线性求值
     * @Author liuchanglin
     * @Date 2019/10/28 4:58 下午
     * @Param [vector1, vector2, lambda]
     * @return int[]
     **/
    public static int[] LinearEvaluation(int[] vector1,int[] vector2,double lambda) {
        int[] result = new int[vector1.length];
        for (int i : vector1) {

            i = (int) Math.round (i * lambda);
        }
        for (int i : vector2) {
            i = (int) Math.round(i * (1-lambda));
        }
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] + vector2[2];
        }
        return result;
    }
    /**
     * @Title LinearEvaluation
     * @Description 单一值向量线性求值
     * @Author liuchanglin
     * @Date 2019/10/28 5:29 下午
     * @Param [value1, value2, lambda]
     * @return int
     **/
    public static int SinggleLinearEvaluation(int value1, int value2, float lambda) {
        return Math.round (lambda * value1 + (1 - lambda) * value2);
    }

}
