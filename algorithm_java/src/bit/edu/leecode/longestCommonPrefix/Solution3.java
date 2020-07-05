package bit.edu.leecode.longestCommonPrefix;

/**
 * 二分查找
 */
class Solution3 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) return "";
        int minLength = Integer.MAX_VALUE;
        for (String st : strs) {
            minLength = Math.min(minLength, st.length());
        }

        int left = 0, right = minLength, mid = Integer.MAX_VALUE;
        while (left < right) {
            mid = left + (right - left + 1) / 2;
            if (isCommonPrefix(strs, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }

        }

        return strs[0].substring(0, left);
    }

    /*public boolean isCommonPrefix(String strs[], int position) {
        String prefix = strs[0].substring(0, position);
        for (String st : strs) {
            if (!st.substring(0, position).equals(prefix)) return false;
        }

        return true;
    }*/

    public boolean isCommonPrefix(String[] strs, int position) {
        String prefix = strs[0].substring(0, position);
        for (String st : strs) {
            for (int i = 0; i < position; ++i) {
                if (st.charAt(i) != prefix.charAt(i)) return false;
            }
        }
        return true;
    }
}