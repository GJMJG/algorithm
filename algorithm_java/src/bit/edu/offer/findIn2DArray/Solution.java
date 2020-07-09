package bit.edu.offer.findIn2DArray;

/**
 * 二维数组中查找元素：控制一个方向上的数字变化，以右上角为例
 */
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        int row = matrix.length;
        int column = matrix[0].length;
        int x = 0, y = column - 1;

        while (x < row && y >= 0) {
            if (matrix[x][y] > target) {
                --y;
            } else if (matrix[x][y] < target) {
                ++x;
            } else {
                return true;
            }
        }
        return false;
    }
}
