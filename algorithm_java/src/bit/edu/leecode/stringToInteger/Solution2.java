package bit.edu.leecode.stringToInteger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 方法三：正则表达式
 */
public class Solution2 {
    public int myAtoi(String str) {
        String regex = "^[ ]*([ +-]?)(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("Gtoup1 : " + matcher.group(1));
            System.out.println("Group2 : " + matcher.group(2));
            return compose(matcher.group(1), matcher.group(2));
        }
        return 0;
    }

    public int compose(String sign, String number) {
        int flag = sign.equals("-") ? -1 : 1;
        int result = 0;
        int temp = flag == 1 ? 0 : 1;
        for (int i = 0; i < number.length(); i++) {
            if (result > (Integer.MAX_VALUE - (number.charAt(i) - '0')) / 10) {
                return flag == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            /*
if (flag > 0) {
    if (result > Integer.MAX_VALUE / 10) {
        return Integer.MAX_VALUE;
    }
    if (result == Integer.MAX_VALUE / 10 && number.charAt(i) - '0' > Integer.MAX_VALUE % 10) {
        return Integer.MAX_VALUE;
    }
} else if (flag < 0) {
    if (result > Integer.MAX_VALUE / 10) {
        return Integer.MIN_VALUE;
    }
    if (result == Integer.MAX_VALUE / 10 && number.charAt(i) - '0' > -(Integer.MIN_VALUE % 10)) {
        return Integer.MIN_VALUE;
    }
}*/
            result = result * 10 + number.charAt(i) - '0';
        }
        return flag * result;
    }

    public static void main(String[] args) {
        System.out.println("INT_MAX" + Integer.MAX_VALUE);
        System.out.println("INT_MIN" + -(Integer.MIN_VALUE % 10));
        System.out.println(new Solution2().myAtoi("2147483648"));
    }
}
