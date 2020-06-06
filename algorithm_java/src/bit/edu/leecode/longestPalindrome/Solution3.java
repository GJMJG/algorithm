package bit.edu.leecode.longestPalindrome;

/**
 * 中心扩散算法
 */
public class Solution3 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 初始化longestNum 值为 1，单个字符的字符串也是回文串
        int start = 0, longestNum = 1;
        for (int i = 0; i < s.length(); ++i) {
            //枚举所有回文中心，并分别计算回文长度
            int case1 = extendEdge(s, i, i);
            int case2 = extendEdge(s, i, i + 1);
            int len = Math.max(case1, case2);
            if (len > longestNum) {
                // 不论是奇数偶数，都可以这样计算
                start = i - (len - 1) / 2;
                // 使用长度而不是 end 索引记录的好处是，这样不用计算 end 值
                longestNum = len;
            }
        }
        //s.subString(start, end +1) 如果采用 start、end索引，这里需要注意
        return s.substring(start, start + longestNum);
    }

    public int extendEdge(String s, int left, int right) {
        int L = left, R = right;
        if (s.length() == 0 || L > R || L < 0 || R >= s.length()) {
            return 0;
        }
        // 注意先判断是否越界，不能先判断 s.charAt(L) == s.charAt(R)，因为上次循环体中 --L之后，可能已经越界了
        while (L <= R && L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            --L;
            ++R;
        }
        //在退出循环后，L、R 已经多减了一次，length =(R-1) - (L+1) +1 = R - L -1
        return R - L - 1;
    }

    public static void main(String[] args) {
        String s = new Solution3().longestPalindrome("babad");
        System.out.println(s);
    }
}
