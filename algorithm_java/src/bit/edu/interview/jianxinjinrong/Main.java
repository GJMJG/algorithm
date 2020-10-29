package bit.edu.interview.jianxinjinrong;

/**
 * <p>建信金科一面，回文字符串，过滤空格</p>
 * 双指针
 * <p>时间复杂度：O(N)，遍历一次数组</p>
 * <p>空间复杂度：O(1)，无需额外空间</p>
 */
public class Main {
    static public boolean isSymmetry(char[] str) {
        int len = str.length;
        if (str == null || len < 1) {
            throw new RuntimeException("输入数组不合法");
        }
        int left = 0, right = len - 1; //left 指向左边字符，right指向右边字符
        // 有效字符为偶数，终止条件 left + 1 == right
        // 有效字符为奇数，终止条件 left == right
        while (left != right && left + 1 != right) {
            // 过滤空格
            while (left <= right && str[left] == ' ') {
                ++left;
            }
            while (left <= right && str[right] == ' ') {
                --right;
            }
            if (str[left] != str[right]) return false;
            ++left;
            --right;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isSymmetry(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'd'}));
        System.out.println(isSymmetry(new char[]{' ', 'a', ' ', 'b', 'c', 'd', ' ', 'c', 'b', 'a'}));
    }
}
