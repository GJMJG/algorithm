package bit.edu.leecode.longestSubString.addBinary;

import java.util.BitSet;

class Solution2 {
    public String addBinary(String a, String b) {
        int x = Integer.parseInt(a, 2);
        int y = Integer.parseInt(b, 2);
        int ans = 0;
        while (y != 0) {
            ans = x ^ y;
            int over = (x & y) << 1;
            y = over;
            x = ans;
        }
        return Integer.toBinaryString(ans);
    }

    public static void main(String[] args) {
        new Solution2().addBinary(
                "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
                "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011");
    }
}