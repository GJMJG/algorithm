package bit.edu.leecode.longestSubString.letterPhoneNumber;

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<String> result = new ArrayList<String>();
    char[] mapping = new char[]{'a', 'd', 'g', 'j', 'm', 'p', 't', 'w'};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.equals("")) {
            return result;
        }

        combination("", digits, 0);
        return result;
    }

    // res 为截至当前字母的结果，digits 输入的字符串，index 为处理第几个位置
    public void combination(String res, String digits, int index) {
        if (index == digits.length()) {
            result.add(res);
            return;
        }

        int times = 3;
        char c = digits.charAt(index);
        if (c == '7' || c == '9') times = 4;

        for (int i = 0; i < times; i++) {
            char letter = (char) (mapping[c - '2'] + i);
            combination(res + String.valueOf(letter), digits, index + 1);
        }
    }
}