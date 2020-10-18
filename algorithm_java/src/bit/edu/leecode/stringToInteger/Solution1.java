package bit.edu.leecode.stringToInteger;

/**
 * 方法一：直接扫描
 */
public class Solution1 {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        long result = 0;
        int sign = 0;
        int length = str.length();
        // Remove the space at the front of str.
        int i = 0;
        while (str.charAt(i) == ' ') {
            ++i;
            if (i >= length) return 0;
        }

        if (isSign(str.charAt(i))) {
            sign = str.charAt(i) == '+' ? 1 : -1;
            ++i;
        } else if (!isNumber(str.charAt(i))) {
            return 0;
        }

        while (i < length && isNumber(str.charAt(i))) {
            long temp = result * 10 + str.charAt(i) - '0';
            result = sign == -1 ? Math.min(-(long) Integer.MIN_VALUE, temp) : Math.min(Integer.MAX_VALUE, temp);
            ++i;
        }
        return sign == -1 ? -(int) result : (int) result;
    }

    public boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public boolean isSign(char c) {
        return c == '+' || c == '-';
    }
}
