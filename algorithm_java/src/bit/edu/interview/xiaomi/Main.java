package interview.xiaomi;

import java.util.Scanner;

/**
 * 注册网站时，需要使用包含不同类型（数字、符号、大写字母、小写字母）的字符，和特定长度。检查一个密码内容同时包含以上 4 种类型的字符，并且长度在8-120 个字符之间。符合要求，返回 0；长度不符合返回 1；类型不符合要求返还 2。
 * <p>
 * 可以一次输入多组密码，以空格符间隔，空格符不作为密码。
 * <p>
 * <p>
 * <p>
 * 输入描述 需要验证的密码，多个密码以空格符间隔，空格符不作为密码的一部分
 * <p>
 * 输出描述 每个密码的检查结果，每个结果需要换行输出
 * <p>
 * 模拟的方法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] array = input.split(" ");

        for (String s : array) {
            System.out.println(varify(s));
        }
    }

    /**
     * 0表示正确 1 表示长度不符合 2 表示类型不符合
     */
    private static int varify(String s) {
        if (s.length() < 8 || s.length() > 120) return 1;
        boolean hasNumber = false, hasUpperChar = false, hasLowerChar = false, hasOtherChar = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!hasNumber && c >= '0' && c <= '9') hasNumber = true;
            if (!hasUpperChar && c >= 'A' && c <= 'Z') hasUpperChar = true;
            if (!hasLowerChar && c >= 'a' && c <= 'z') hasLowerChar = true;
            if (!hasOtherChar) hasOtherChar = true;
        }

        if (hasLowerChar && hasUpperChar && hasNumber && hasOtherChar) return 0;
        return 2;
    }
}
