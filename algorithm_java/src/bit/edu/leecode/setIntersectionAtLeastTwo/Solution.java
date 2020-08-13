package bit.edu.leecode.setIntersectionAtLeastTwo;

import java.util.Arrays;

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // 按照左区间排序，如果左区间相等，则按照右区间排序
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] todo = new int[intervals.length]; //记录每个区间还需要匹配的次数，初试为 2
        Arrays.fill(todo, 2);
        int result = 0;

        // 从最后一个区间开始，尽可能让左区间和别的区间重合
        for (int i = intervals.length - 1; i >= 0; i--) {
            int start = intervals[i][0];
            int cur = todo[i];
            for (int j = start; j < start + cur; j++) {
                // 更新其他区间的未匹配数目
                for (int k = 0; k <= i; k++) {
                    if (todo[k] > 0 && intervals[k][0] <= j && j <= intervals[k][1]) {
                        todo[k]--;
                    }
                }
                result++;
            }
        }

        return result;
    }
}