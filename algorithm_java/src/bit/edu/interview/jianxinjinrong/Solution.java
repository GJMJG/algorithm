package interview.jianxinjinrong;

/**
 * 给定一个 N * N 有 0、1组成的数组 M[][], M[i][j] 表示 i 和 j 是朋友，已知朋友具有传递性，数组朋友圈的个数
 * 给定的数组中 M[i][i] = 1, M[i][j] = M[j][i]
 * 例如输入
 * <pre><code>
 *  *     [
 *  *     [1 1 0],
 *  *     [1 1 0],
 *  *     [0 0 1]]
 *  *     输出 2，因为 0 和 1是朋友，2没有其他朋友
 *  *     [
 *  *     [1 1 0],
 *  *     [1 1 1],
 *  *     [0 1 1]]
 *      输出1，0 和 1 是朋友，1 和 2 是朋友
 *  * </code></pre>
 */
public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可 寻找朋友总数
     */
    // 并查集 + dfs，从第一个人开始，寻找直接和间接的所有朋友，如果已经找过朋友的，直接跳过
    public int findFriendNum(int[][] M) {
        // write code here
        int len = M.length;
        boolean[] hasFriend = new boolean[len]; //记录是否已经找过朋友
        int res = 0;
        // 从编号为 0 的学生找，dfs找到所有的朋友
        for (int i = 0; i < len; i++) {
            if (!hasFriend[i]) {
                dfs(hasFriend, M, i); // 第 i 个同学找自己的朋友
                res++;
            }
        }
        return res;
    }

    public void dfs(boolean[] hasFriend, int[][] M, int number) {
        if(hasFriend[number] == true) return; // 如果已经找到过朋友，不用继续寻找了
        hasFriend[number] = true; // 自己和自己是朋友，可能组成一个朋友圈
        // 继续寻找其他朋友
        for (int i = 0; i < M.length; i++) {
            if (i == number) continue;
            if (M[number][i] == 1) {
                dfs(hasFriend, M, i);
            }
        }
    }
}
