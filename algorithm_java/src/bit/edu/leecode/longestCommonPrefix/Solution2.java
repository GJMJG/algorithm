package bit.edu.leecode.longestCommonPrefix;

/**
 * 分治算法
 */
class Solution2 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) return strs[start];
        else {
            String a = longestCommonPrefix(strs, start, (start + end) >> 1);
            String b = longestCommonPrefix(strs, (start + end >> 1) + 1, end);
            return commonPrefix(a, b);
        }
    }

    public String commonPrefix(String a, String b) {
        int length = Math.min(a.length(), b.length());
        int index = 0;
        while (index < length && a.charAt(index) == b.charAt(index)) {
            ++index;
        }
        return a.substring(0, index);
    }
}