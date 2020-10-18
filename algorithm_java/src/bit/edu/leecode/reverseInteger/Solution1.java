package bit.edu.leecode.reverseInteger;

class Solution1 {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int number = x % 10;
            if (x < 0 && result > (Integer.MAX_VALUE - number) / 10) return 0;
            if (x > 0 && result < (Integer.MIN_VALUE - number) / 10) return 0;
            result = 10 * result + number;
            x /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(2147483647));
    }
}