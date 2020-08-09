package bit.edu.interview.wangyi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 给定一个长度为 m 的数组序列，求一个长度为 n 的数组，使得该数组满足以下条件：
 * <li>输入数组是该数组的子集</li>
 * <li>保证该数组的字典序列最小</li>
 * <p>第一行输入两个数，表示n 和 m，第二行输入 m 个数，表示输入数组中的元素，输出一行 n 个数，表示输出数组中的元素，注意元素之间的空格</p>
 * <p>因为要保证字典序最小，需要将小的数字尽可能排在前面，且该数字没有出现在原数组中，也没有已经排列过。相当于在每个输入的数组元素前，插入
 * 若干个尽可能小的数，并使用Set记录是否已经使用过该数字。具体操作可以使用双指针，一个指向输入数组，一个事项输出数组，不断为输出数组添加符合 条件的数字，更新指针。</p>
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Integer> table = new HashSet<>();
        int n = sc.nextInt(); // 输出数组长度
        int m = sc.nextInt(); //输入数组长度
        int[] inputArray = new int[m]; //输入数组
        int[] outputArray = new int[n]; //最终要求的数组
        int maxValue = 0;
        int preMax = 1; //为了减少循环

        for (int i = 0; i < m; i++) {
            inputArray[i] = sc.nextInt();
            table.add(inputArray[i]);
            maxValue = maxValue > inputArray[i] ? maxValue : inputArray[i];
        }

        int indexT = 0; //指向输入数组中的元素索引
        int indexS = 0; //指向输出数组中元素的索引
        while (indexS <= n - 1) {
            if (indexT == m) {
                outputArray[indexS++] = ++maxValue;
                continue;
            }

            for (int i = preMax; i <= inputArray[indexT]; i++) {
                // 如果输入数组和输出数组剩下的元素相等，无需循环，指针同时向后移动
                if (n - indexS == m - indexT) break;
                if (table.contains(i)) continue;
                outputArray[indexS++] = i;
                table.add(i);
                preMax = i + 1;
            }
            outputArray[indexS++] = inputArray[indexT++];
        }

        StringBuilder s = new StringBuilder();
        for (int i : outputArray) {
            s.append(i);
            s.append(" ");
        }
        System.out.println(s.toString());
    }
}
