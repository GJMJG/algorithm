package bit.edu.leecode.implstrStr;

class Solution1 {
    public int strStr(String haystack, String needle) {
        if (needle.equals("") || needle == null) {
            return 0;
        }

        for (int i = 0; i < haystack.length() - needle.length() + 1; ++i) {
            if (isMatched(haystack, needle, i)) return i;
        }
        return -1;
    }

    public boolean isMatched(String haystack, String needle, int start) {
        int i = start;
        int j = 0;

        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                ++i;
                ++j;
            } else {
                break;
            }
        }
        return j == needle.length() ? true : false;
    }
}