package bit.edu.interview.hauwei.test;

import java.util.Collections;

/**
 * 3.字符串大X形变换 给定一个仅由大写字母组成的字符串以及一个指定的奇数列，按从左到右，从上到下的顺序将给定的字符串以大X字形排列指定的列数。
 */
public class Main2 {

    /**
     * @param array 输入数组
     * @param n     X 形的列数
     */
    static void printX(char[] array, int n) {
        String s = String.join("", Collections.nCopies(n, " "));
        int index = 0; // 指向 array 中元素的指针
        int L = 0, R = n - 1;
        boolean turn = true;
        for (int i = 0; i < n; i++) {
            char[] temp = s.toCharArray();
            temp[L] = array[index++];
            temp[R] = array[index++];
            System.out.println(new String(temp));
            if (turn) {
                ++L;
                --R;
                if (L == R) turn = false;
            } else {
                --L;
                ++R;
            }
        }
    }

    public static void main(String[] args) {
        new Main2().printX(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}, 5);
    }
}
