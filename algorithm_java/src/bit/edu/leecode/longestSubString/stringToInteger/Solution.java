package bit.edu.leecode.longestSubString.stringToInteger;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法一：自动机
 */
public class Solution {
    public int myAtoi(String str) {
        int length = str.length();
        if (str == null || str.length() == 0) {
            return 0;
        }

        Automaton automaton = new Automaton();
        for (int i = 0; i < length; i++) {
            if (automaton.isEnd) break;
            automaton.transition(str.charAt(i));
        }

        return automaton.sign * (int) automaton.result;
    }
}

class Automaton {
    // Record current state.
    String state = START;
    final static String START = "start", SIGNED = "signed", IN_NUMBER = "in_number", END = "end";
    Map<String, String[]> table;
    public int sign = 1;
    public long result = 0;
    public boolean isEnd = false;

    public Automaton() {
        table = new HashMap<>();
        table.put(START, new String[]{START, SIGNED, IN_NUMBER, END});
        table.put(SIGNED, new String[]{END, END, IN_NUMBER, END});
        table.put(IN_NUMBER, new String[]{END, END, IN_NUMBER, END});
        table.put(END, new String[]{END, END, END, END});
    }

    public void transition(char c) {
        // Transition
        state = table.get(state)[getNextTransIndex(c)];

        if (state.equals(SIGNED) && c == '-') sign = -1;
        if (state.equals(IN_NUMBER)) {
            result = result * 10 + (c - '0');
            result = sign == -1 ? Math.min(-(long) Integer.MIN_VALUE, result) : Math.min(result, Integer.MAX_VALUE);
        }
        if (state.equals(END)) isEnd = true;
    }

    int getNextTransIndex(char c) {
        if (c == ' ') return 0;
        if (c == '+' || c == '-') return 1;
        if (c >= '0' && c <= '9') return 2;
        return 3;
    }
}