package bit.edu.leecode.longestSubString.intToRoman;

/**
 * 贪心算法
 */
class Solution {
    public String intToRoman(int num) {
        int[] value = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] sign = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int current = num, index = 0;
        StringBuilder res = new StringBuilder();

        while (current != 0 && index < value.length) {
            if (value[index] > current) {
                ++index;
            } else {
                current -= value[index];
                res.append(sign[index]);
            }
        }
        return res.toString();
    }
}