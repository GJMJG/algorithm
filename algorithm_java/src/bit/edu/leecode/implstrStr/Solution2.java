package bit.edu.leecode.implstrStr;

public class Solution2 {
    public int strStr(String haystack, String needle) {
        int L = haystack.length(), n = needle.length();

        for (int start = 0; start < L - n + 1; ++start) {
            if (haystack.substring(start, start + n).equals(needle)) {
                return start;
            }
        }
        return -1;
    }
}
