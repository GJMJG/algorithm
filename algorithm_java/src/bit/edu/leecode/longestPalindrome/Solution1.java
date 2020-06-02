package bit.edu.leecode.longestPalindrome;

import java.util.HashMap;
import java.util.Map;

/**
 * 暴力匹配算法
 */
public class Solution1 {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        String longestPalindromString = "";
        int longestConunt = 0;
        //将最长回文串对应的起始索引记录下来，不断更新索引，最终才返回对应的串
        int start = 0, end = 0;
        //s.charAt()  每次检查下标是否越界，因此将字符串转换为字符数组提高效率
        char[] stringArray = s.toCharArray();
        for (int i = 0; i < stringArray.length; ++i) {
            for (int j = i + 1; j < stringArray.length; ++j) {
                // 求最长的回文串，如果当前的索引间距小于目前的回文串长度，那么不必继续判断是否为回文串
                if (longestConunt < j - i && isPalindrom(stringArray, i, j)) {
                    longestConunt = j - i;
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * 判断输入的字符串是否为回文串。头尾两个指针相对运动，逐个比较指针对应的位置上字符是否相同
     */
    public boolean isPalindrom(char[] arrays, int start, int end) {
        while (start <= end) {
            if (arrays[start] != arrays[end]) {
                return false;
            }
            ++start;
            --end;
        }
        return true;
    }
}
