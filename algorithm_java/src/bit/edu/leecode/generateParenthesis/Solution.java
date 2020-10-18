package bit.edu.leecode.generateParenthesis;

import java.util.ArrayList;
import java.util.List;

/**
 * 暴力方法穷举可能情况
 */
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateAll(new char[2 * n], 0, res);
        return res;
    }

    public void generateAll(char[] chars, int position, List<String> result) {
        if (position == chars.length) {
            if (validate(chars)) {
                result.add(chars.toString());
            }
        } else {
            chars[position] = '(';
            generateAll(chars, position + 1, result);
            chars[position] = ')';
            generateAll(chars, position + 1, result);
        }
    }

    public boolean validate(char[] chars) {
        int count = 0;
        int i = 0;
        while (i < chars.length) {
            if (chars[i] == '(') count++;
            if (chars[i] == ')') count--;
            if (count < 0) break;
            ++i;
        }
        return count == 0 ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(3));
    }
}
