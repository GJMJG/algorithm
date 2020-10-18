package interview.zhongxin;

import java.util.HashSet;
import java.util.Set;

public class Solution1 {
    public static void main(String[] args) {
        System.out.println(new Solution1().removeDup("Hello Credit Card"));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可 旋转含有空格分隔符字符串，并去除字符串中的空格、重复字母
     *
     * @param str string字符串 待处理的字符串
     * @return string字符串
     */
    public String removeDup(String str) {
        // write code here
        StringBuilder sb = new StringBuilder(str).reverse();
        StringBuilder res = new StringBuilder();
        Set<Character> set = new HashSet<>(); // max size is 26.

        String[] strArr = sb.toString().split(" ");
        for (String st : strArr) {
            if (set.size() == 26) break;
            st.replaceAll(" ", "");
            StringBuilder subSb = new StringBuilder(st);
            char[] array = subSb.reverse().toString().toCharArray();
            for (char c : array) {
                if (set.contains(c)) continue;
                if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) continue;
                char cc;
                if (Character.isUpperCase(c)) {
                    cc = Character.toLowerCase(c);
                } else {
                    cc = c;
                }
                res.append(cc);
                set.add(cc);

            }
        }

        return res.toString();
    }
}