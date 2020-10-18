package bit.edu.interview.zhongxin.test;

import java.util.HashMap;

/**
 * 求出n 到 m 之间所有的素数和，包括 n 和 m
 */
public class Solution {
    static HashMap<Integer, Boolean> cash = new HashMap<>(); // isPrime -> true

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可 primeSum函数返回素数和
     *
     * @param n int整型 n为正整数，且n>1
     * @param m int整型 m为正整数，且m>n
     * @return int整型
     */
    public int primeSum(int n, int m) {
        // write code here
        int sum = 0;
        for (int i = n; i <= m; i++) {
            if (i != 2 && i % 2 == 0) continue;
            if (cash.containsKey(i)) {
                if (cash.get(i)) sum += i;
            } else if (isPrime(i)) sum += i;
        }
        return sum;
    }

    public boolean isPrime(int a) {
        boolean res = true;
        if (a == 2) {
            cash.put(a, true);
            return true;
        }
        int limit = (int) Math.sqrt(a);
        for (int i = 3; i < limit; i = i + 2) {
            if (a % i == 0) {
                cash.put(a, false);
                return false;
            }
        }
        cash.put(a, true);
        return res;
    }
}