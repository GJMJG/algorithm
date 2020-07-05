package bit.edu.leecode.longestCommonPrefix;

/**
 * 纵向扫描
 */
class Solution1 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) return "";
        int column = strs[0].length();
        for (int i = 0; i < column; ++i) {
            char currentChar = strs[0].charAt(i);
            for (int j = 0; j < strs.length; ++j) {
                if (i == strs[j].length() || strs[j].charAt(i) != currentChar) return strs[0].substring(0, i);
            }
        }
        // 如果执行到此，则strs[0] 本身就是最长公共前缀
        return strs[0];
    }
}