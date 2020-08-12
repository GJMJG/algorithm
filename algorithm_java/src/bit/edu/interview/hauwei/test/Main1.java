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
        int M = path.length;
        int N = path[0].length;
        if (visited[M - 1][N - 1] == true) {
            has = true;
            return;
        }

        if (x < 0 || x >= M) return;
        if (y < 0 || y > N) return;
        if (visited[x][y]) return;
        if (path[x][y] == 0) return;

        visited[x][y] = true;
        hasPath(path, visited, x, y - step);
        hasPath(path, visited, x, y + step);
        hasPath(path, visited, x - step, y);
        hasPath(path, visited, x + step, y);
    }

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

        //        visited[0][0] = true;
        hasPath(path, visited, 0, 0);
        System.out.println(has ? 1 : 0);
    }
}
