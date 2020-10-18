package bit.edu.leecode.zigZagConvesion;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法一：取余计算
 */
public class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        List<Character>[] table = new ArrayList[numRows];
        //Initial the list.
        for (int i = 0; i < numRows; i++) {
            table[i] = new ArrayList<>();
        }

        for (int i = 0; i < s.length(); i++) {
            int position = i % (2 * (numRows - 1));
            if (position >= numRows) {
                position = 2 * (numRows - 1) - position;
            }
            List<Character> rowList = table[position];
            rowList.add(s.charAt(i));
        }

        // Print the elements in every list.
        for (List<Character> list : table) {
            for (char c : list) {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        new Solution().convert("PAYPALISHIRING", 3);
    }
}
