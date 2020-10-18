package test;

public class Finder {

    static public int partition(int[] a, int start, int end) {
        int small = start - 1;
        int random = (int) Math.random() * (end - start + 1) + start;
        swap(a, random, end);
        for (int i = start; i < end; ++i) {
            if (a[i] > a[end]) {
                ++small;
                if (small != i) {
                    swap(a, small, i);
                }
            }
        }
        ++small;
        swap(a, small, end);
        return small;
    }

    static public void swap(int[] a, int i, int j) {
        a[i] = a[i] + a[j] - (a[j] = a[i]);
    }

    public static void main(String[] args) {
        System.out.println(new Finder().findKth(new int[]{1332802, 1177178, 1514891, 871248, 753214, 123866, 1615405,
                328656, 1540395, 968891, 1884022, 252932, 1034406, 1455178, 821713, 486232, 860175, 1896237, 852300,
                566715, 1285209, 1845742, 883142, 259266, 520911, 1844960, 218188, 1528217, 332380, 261485, 1111670,
                16920, 1249664, 1199799, 1959818, 1546744, 1904944, 51047, 1176397, 190970, 48715, 349690, 673887,
                1648782, 1010556, 1165786, 937247, 986578, 798663}, 49, 24));
    }

    public int findKth(int[] a, int n, int K) {
        // write code here
        int i = partition(a, 0, n - 1);
        int left = 0, right = n - 1;
        while (i  != K- 1) {
            if (i > K - 1) {
                right = i - 1;
            } else {
                left = i + 1;
            }
            i = partition(a, left, right);
        }

        return a[i];
    }
}