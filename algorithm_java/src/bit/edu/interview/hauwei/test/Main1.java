package bit.edu.interview.hauwei.test;

import java.util.Scanner;

/**
 * 给出一个路径矩阵，问能否在指定步长下从左上角移动到右下角。
 * 通过率为 50%，可能存在数组越界问题，但是已经判断的都判断了。
 */
public class Main1 {
    private static int step;
    private static boolean has = false;

    public static void hasPath(short[][] path, boolean[][] visited, int x, int y) {
        if(has) return;
        int M = path.length;
        int N = path[0].length;
        if(x == M - 1 && y == N - 1){
            has = true;
            return;
        }

        if (x < 0 || x >= M) return;
        if (y < 0 || y >= N) return;
        if (visited[x][y]) return;
        if (path[x][y] == 0) return;

        visited[x][y] = true;
        if(!has) hasPath(path, visited, x, y - step);
        if(!has) hasPath(path, visited, x, y + step);
        if(!has) hasPath(path, visited, x - step, y);
        if(!has) hasPath(path, visited, x + step, y);
        visited[x][y] = false;
    }

    /**
     * 输入描述：
     * 第一行第一个数字为步长 step，接下来两个数字分别表示矩阵行列数 M, N
     * 接下来 M 行数字，每行有 N 个数字，1 表示可以走，0表示不可以
     * 例如：
     * 1 4 4
     * 1 0 0 0
     * 1 1 0 1
     * 0 1 1 0
     * 0 0 1 1
     * 返回值： 1 表示有路径， 0 表示无路径
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        step = sc.nextInt();
        int M = sc.nextInt();
        int N = sc.nextInt();
        if (M <= 0 || N <= 0) {
            System.out.print(0);
            return;
        }

        short[][] path = new short[M][N];
        boolean[][] visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                path[i][j] = sc.nextShort();
                visited[i][j] = false;
            }
        }

        hasPath(path, visited, 0, 0);
        System.out.println(has ? 1 : 0);
    }
}
