package bit.edu.leecode.longestPalindrome;

/**
 * <p>最长回文串的动态规划解法
 */
public class Solution2 {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (s.length() < 2) {
            return s;
        }

        // 记录从 i 到 j 子串是否为回文串
        boolean[][] dp = new boolean[length][length];
        char[] stringArray = s.toCharArray();
        int start = 0, end = 0;
        int longestConunt = 0;
        for (int j = 1; j < length; ++j) {
            for (int i = 0; i <= j; ++i) {
                // 最后的一个字符或者两个相邻的字符，子问题的边界
                if (i == j || j - i == 1) {
                    dp[i][j] = stringArray[i] == stringArray[j];
                } else {
                    if (stringArray[i] != stringArray[j]) {
                        dp[i][j] = false;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && longestConunt < j - i + 1) {
                    longestConunt = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }
}
