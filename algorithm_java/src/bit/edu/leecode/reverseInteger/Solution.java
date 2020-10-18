package bit.edu.leecode.reverseInteger;

/**
 * 复杂的解法，将负数转换成整数之后再处理，其实不用这么麻烦，直接对负数比较即可。好的解法参考 {@link Solution1}。
 */
class Solution {
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
            return 0;
        }
        int result = 0;
        int sign = x >= 0 ? 1 : -1;
        x *= sign;
        while (x != 0) {
            int number = x % 10;
            x /= 10;
            if (sign == 1 && result > (Integer.MAX_VALUE - number) / 10) {
                return 0;
            }
            if (sign == -1 && -result < (Integer.MIN_VALUE + number) / 10) {
                return 0;
            }
            result = result * 10 + number;
        }
        return sign * result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(-123));
    }
}