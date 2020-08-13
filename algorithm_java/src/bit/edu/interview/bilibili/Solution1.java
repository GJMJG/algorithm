package bit.edu.interview.bilibili;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 括号配对，判断能否配成一对，使用栈
 */
public class Solution1 {
    /**
     * @param s string字符串
     * @return bool布尔型
     */
    public boolean IsValidExp(String s) {
        if (s == null) {
            return false;
        }
        if ("".equals(s)) return true;

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (!stack.isEmpty() && stack.peek() == map.get(c)) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }
}