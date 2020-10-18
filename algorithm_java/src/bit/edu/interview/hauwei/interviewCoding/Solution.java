package bit.edu.interview.hauwei.interviewCoding;

/**
 * 华为二面笔试题：
 * <p>给一个整数，属于2 到 58，该整数可以由多个数字相加得到，使得这几个数字乘积最大，并输出最大乘积。</p>
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().breakInteger(10));
        System.out.println(new Solution().breakInteger(2));
        System.out.println(new Solution().breakInteger(5));
        System.out.println(new Solution().breakInteger(11));
    }

    public int breakInteger(int a) {
        // 贪心算法 + 动态规划
        if (a < 2) return -1;
        if (a < 4) {
            return a - 1;
        }
        int[] dp = new int[59];
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        for (int i = 5; i < dp.length; i++) {
            dp[i] = dp[i - 3] * dp[3];
        }

        return dp[a];
    }
}
