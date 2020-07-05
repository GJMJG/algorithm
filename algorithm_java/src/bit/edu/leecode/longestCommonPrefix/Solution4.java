package bit.edu.leecode.longestCommonPrefix;

import java.util.Arrays;

/**
 * 使用库函数中的排序
 */
public class Solution4 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs);

        StringBuilder res = new StringBuilder();
        int length = strs[0].length();
        for (int i = 0; i < length; ++i) {
            if (strs[0].charAt(i) != strs[strs.length - 1].charAt(i)) break;
            res.append(strs[0].charAt(i));
        }
        return res.toString();
    }
}
