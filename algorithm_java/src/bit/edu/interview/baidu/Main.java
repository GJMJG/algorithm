package bit.edu.interview.baidu;

/**
 * 百度提前批面试算法题：

 * 对于一个回文数字，首尾数字去掉之后仍然是回文数字
 * 方法入参校验，如果小于0，返回false，如果整数只有一位，返回true。
 * 首先遍历整数的每一位数字，统计数字的长度。
 * 判断最高位数字和最低为数字是否相等，不等则返回 false
 * 去掉最高位和最低为，递归调用方法判断处理之后的数字是否为回文数字
 *
 * 这种方法有缺陷，字符串中包含形如 0x0 的数字，会被误判为 false
 */
public class Main {

    public boolean isPalindrome(int number) {
        if (number < 0) return false;
        if (number / 10 == 0 ) {
            return true;
        }

        int copy = number, count = 0;
        while (copy != 0) {
            copy /= 10;
            ++count;
        }

        int pow = 1;
        --count;
        while (count-- != 0) {
            pow *= 10;
        }

        if (number / pow != number % 10) {
            return false;
        }

        return isPalindrome(number % pow / 10);
    }

    public static void main(String[] args) {
        System.out.println(new Main().isPalindrome(20202));
    }
}
