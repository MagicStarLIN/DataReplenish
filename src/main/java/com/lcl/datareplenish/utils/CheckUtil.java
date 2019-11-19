package com.lcl.datareplenish.utils;

import java.util.regex.Pattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: CheckUtil
 * @Description: 检验util
 * @date 2019/10/31 9:29 上午
 */
public class CheckUtil {
    /**
     * @Title checkBankCard
     * @Description luhm算法检验银行卡号
     * @Author liuchanglin
     * @Date 2019/10/31 9:30 上午
     * @Param [cardId]
     * @return boolean
     **/
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        return cardId.charAt(cardId.length() - 1) == bit;
    }
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        int cardLenth = nonCheckCodeCardId.trim().length();
        if (nonCheckCodeCardId == null || cardLenth == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            throw new IllegalArgumentException("不是银行卡的卡号!");
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * @Title regexMatch
     * @Description 正则表达式检验
     * @Author liuchanglin
     * @Date 2019/10/31 9:31 上午
     * @Param [regex, str]
     * @return boolean
     **/
    public static boolean regexMatch(String regex, String str) {
        return Pattern.compile(regex).matcher(str).matches();
    }
}
