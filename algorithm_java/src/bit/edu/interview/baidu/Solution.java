package bit.edu.interview.baidu;

public class Solution {

    public boolean isPalindrome(int number) {
        if (number < 0) {
            return false;
        }

        int reversedNumber = reverse(number);
        return reversedNumber == number;
    }

    public int reverse(int number) {
        int reversedNumber = 0;
        while (number != 0) {
            reversedNumber = reversedNumber * 10 + (number % 10);
            number /= 10;
        }
        return reversedNumber;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome(20202));
    }
}
