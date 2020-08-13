package bit.edu.interview.bilibili;

/**
 * 24点游戏，给定4个数字，判断能否经过加减乘除算出24点
 */
public class Solution {
    /**
     * @param arr int整型一维数组
     * @return bool布尔型
     */
    public boolean Game24Points(int[] arr) {
        return game(arr, 0, 0);
    }

    public boolean game(int[] arr, int index, int lastResult) {
        if (index == 4 && lastResult == 24) return true;
        if (index == 4) return false;
        return game(arr, index + 1, arr[index] + lastResult) ||
                game(arr, index + 1, lastResult -arr[index] ) ||
                game(arr, index + 1, arr[index] * lastResult) ||
                game(arr, index + 1, lastResult /arr[index] );
    }
}