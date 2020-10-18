package bit.edu.leecode.containerMax;

/**
 * 暴力枚举
 */
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; ++i) {
            for (int j = i + 1; j < height.length; ++j) {
                int maxHeight = Math.min(height[i], height[j]);
                int area = maxHeight * (j - i);
                if (maxArea < area) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}