package bit.edu.leecode.longestSubString;

import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * 1. 定义一个set存放当前滑动窗口中的数据data
     * 2. 定义三个变量分别表示窗口的起始位置、终止位置、结果（最长字串长度）
     * 3. 终止位置开始滑动，如果滑动窗口中没有，则将该数据加入到data中；否则滑动起始位置，并减少滑动窗口中的数据
     * 4. 循环上述过程，直到结尾
     */
    public int lengthOfLongestSubstring(String s) {
        //定义一个set存放子序列
        Set<Character> data = new HashSet();
        int len = s.length();
        int start, end, res = 0;
        for (int i = 0, j = 0; i < len && j < len; ) {
            if (data.contains(s.charAt(j))) {
                data.remove(s.charAt(i++));
                continue;
            }

            res = Math.max(res, j - i + 1);
            data.add(s.charAt(j++));
        }
        return res;
    }
}