package bit.edu.test.tecent;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 求两个降序链表的公共节点
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }

        int m = in.nextInt();
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = in.nextInt();
        }

        ArrayList<Integer> res = getCommon(A, B);
        for (int a : res) {
            System.out.print(a + " ");
        }
    }

    public static ArrayList<Integer> getCommon(int[] A, int[] B) {
        int lenA = A.length, lenB = B.length;
        int indexA = 0, indexB = 0;
        ArrayList<Integer> res = new ArrayList<>();
        while (indexA < lenA && indexB < lenB) {
            if (A[indexA] == B[indexB]) {
                res.add(A[indexA]);
                indexA++;
                indexB++;
            } else if (A[indexA] < B[indexB]) {
                indexB++;
            } else {
                indexA++;
            }
        }
        return res;
    }
}
