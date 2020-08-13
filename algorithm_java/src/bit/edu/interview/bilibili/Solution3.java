package bit.edu.interview.bilibili;

/**
 * 有1、4、16、64四种硬币，1024纸币，买东西 （1<n<1024），尽可能少地找出硬币，问最少多少
 */
public class Solution3 {
    public static void main(String[] args) {
        System.out.println(new Solution3().GetCoinCount(200));
    }

    /**
     * @param N int整型
     * @return int整型
     */
    public int GetCoinCount(int N) {
        if (N < 0 || N > 1024) {
            return 0;
        }
        N = 1024 - N;
        // write code here
        int res = 0;
        res += N / 64;
        N = N % 64;
        if (N != 0) {
            res += N / 16;
            N = N % 16;
        }
        if (N != 0) {
            res += N / 4;
            N = N % 4;
        }
        res += N;
        return res;
    }
}