package bit.edu.leecode.longestCommonPrefix;

/**
 * 橫向骚猫
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) return "";

        String res = strs[0];
        for (int i = 0; i < strs.length; ++i) {
            res = getLongestString(strs[i], res);
            if (res.length() == 0) break;
        }
        return res;
    }

    public String getLongestString(String inputSt, String commonSt) {
        int minLength = Math.min(inputSt.length(), commonSt.length());
        int index = 0;
        for (int i = 0; i < minLength; ++i) {
            if (inputSt.charAt(i) == commonSt.charAt(i)) ++index;
            else break;
        }
        return inputSt.substring(0, index);
    }
}