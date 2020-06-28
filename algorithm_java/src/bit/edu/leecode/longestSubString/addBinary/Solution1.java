package bit.edu.leecode.longestSubString.addBinary;

class Solution1 {
    public String addBinary(String a, String b) {
        int lenA = a.length() - 1;
        int lenB = b.length() - 1;
        int over = 0;
        int numA = 0, numB = 0;

        StringBuilder res = new StringBuilder();
        while (lenA >= 0 || lenB >= 0 || over != 0) {
            numA = lenA < 0 ? 0 : a.charAt(lenA) - '0';
            numB = lenB < 0 ? 0 : b.charAt(lenB) - '0';
            int num = numA + numB + over;
            over = num / 2;
            num %= 2;

            res.append(num);
            --lenA;
            --lenB;
        }

        res.reverse();
        return res.toString();
    }
}