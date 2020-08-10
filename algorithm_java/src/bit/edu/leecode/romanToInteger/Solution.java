package bit.edu.leecode.romanToInteger;

/**
 * 暴力算法，效率不高，多次遍历
 */
class Solution {
    public int romanToInt(String s) {
        if (s == null || s.equals("")) return 0;

        String[] roman = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] value = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int result = 0, length = s.length();
        for (int i = 0; i < length; ) {
            int record = i;
            if (i + 2 <= length) {
                for (int index = 0; index < roman.length; ++index) {
                    if (roman[index].equals(s.substring(i, i + 2))) {
                        result += value[index];
                        i += 2;
                        break;
                    }
                }
            }

            if (record != i) continue;
            for (int index = 0; index < roman.length; ++index) {
                if (roman[index].equals(s.substring(i, i + 1))) {
                    result += value[index];
                    ++i;
                    break;
                }
            }
        }
        return result;
    }
}