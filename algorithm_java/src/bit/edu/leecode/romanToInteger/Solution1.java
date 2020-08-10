package bit.edu.leecode.romanToInteger;

class Solution1 {
    public int romanToInt(String s) {
        if (s == null || s.equals("")) return 0;

        int result = 0, length = s.length(), current = getCharValue(s.charAt(0));
        for (int i = 1; i < length; ++i) {
            int next = getCharValue(s.charAt(i));
            if (next > current) {
                result -= current;
            } else {
                result += current;
            }
            current = next;
        }

        result += current;
        return result;
    }

    public int getCharValue(char c) {
        int value = 0;
        switch (c) {
            case 'M':
                value = 1000;
                break;
            case 'D':
                value = 500;
                break;
            case 'C':
                value = 100;
                break;
            case 'L':
                value = 50;
                break;
            case 'X':
                value = 10;
                break;
            case 'V':
                value = 5;
                break;
            case 'I':
                value = 1;
                break;
        }

        return value;
    }
}
