package bit.edu.interview.ali;

import java.util.Scanner;

/**
 * 阿里实习面试题：第一题
 * <p>
 * 题目大概是这样的。在x，y坐标系内有n个村庄要修水渠，水渠平行于y轴，如何修能够保证水渠到每个村庄的垂直距离和最短？
 * <p>
 * <b>分析：</b> 最短垂直距离和y值无关 2. 水渠的位置应该在x方向上中间的位置。
 * <p>
 * <b>推导：</b>如果只有两点，则在两点间任何一个位置，垂直距离和都相等。存在多个点时，首末两端两两为一对，
 * 要使每一对的距离和都最小，水渠必然在每一对中间。因此水渠必然在所有点的中间位置。
 * <ol>
 * <li/>输入的数据中剔除y坐标，保留x坐标
 * <li/>对x坐标数组排序
 * <li/>找到中间索引对应的坐标
 * <li/>遍历数组计算绝对距离和
 * </ol>
 *
 * @author GuoJM
 */
public class ShortestPathSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[] position = new int[n];
        for (int i = 0; i < n; i++) {
            position[i] = sc.nextInt();
            sc.nextInt();
        }

        int result = new ShortestPathSum().getShortestPath(position);
        System.out.println(result);
    }

    int getShortestPath(int[] inputPositions) {
        int[] sortedPosition = sort(inputPositions);
        int pathPosition = (sortedPosition.length + 1) / 2 - 1;

        int result = 0;
        for (int distence : sortedPosition) {
            result += Math.abs((distence - sortedPosition[pathPosition]));
        }
        return result;
    }

    int[] sort(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array.length - i - 1; ++j) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
