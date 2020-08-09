package bit.edu.interview.genshuixue;

/**
 * 递归地反转括号中的字符串
 */
public class Solution {
    public static void main(String[] args) {

    }

    public String reverseParentheses(String s) {
        int length = s.length();
        int leftTheses = 0;
        int rightTheses = s.length() - 1;

        while (leftTheses < length && s.charAt(leftTheses) != '(') {
            ++leftTheses;
        }
        if (leftTheses == length) return reverse(s);

        while (rightTheses >= 0 && s.charAt(rightTheses) != ')') {
            --rightTheses;
        }

        String leftSubString = s.substring(0, leftTheses);
        String middleSubString = s.substring(leftTheses, rightTheses);
        String rightSubString = s.substring(rightTheses, length);

        StringBuilder result = new StringBuilder();
        result.append(reverseParentheses(rightSubString));
        result.append(reverseParentheses(middleSubString));
        result.append(reverseParentheses(leftSubString));

        String res = result.toString();
        //        res.replace('(', ' ');
        //        res.replaceAll(")" ,"");

        return res;
    }

    public String reverse(String s) {
        char[] charArray = s.toCharArray();
        int leftIndex = 0;
        int rightIndex = s.length() - 1;

        while (leftIndex < rightIndex) {
            char temp = charArray[leftIndex];
            charArray[leftIndex] = charArray[rightIndex];
            charArray[rightIndex] = temp;
        }

        return new String(charArray);
    }
}
