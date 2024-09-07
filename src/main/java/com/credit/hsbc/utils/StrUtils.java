package com.credit.hsbc.utils;

import org.springframework.stereotype.Component;

@Component
public class StrUtils {

    /**
     * 统计不同字符个数
     * @param str
     * @return
     */
    public static int countDistinctChar(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        boolean[] charSet = new boolean[256];
        int count = 0;
        for (char c : str.toCharArray()) {
            if (!charSet[c]) {
                charSet[c] = true;
                count++;
            }
        }
        return count;
    }

}
