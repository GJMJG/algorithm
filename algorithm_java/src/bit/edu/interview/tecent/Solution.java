/**
 * 腾讯二面：旋转数组的二分查找
 * 789123456
 * 先判断哪部分是有序的，至少有一半是有序的，若左半部分有序，则 a[left] < a[mid] ，否则右半部分有序
 */
public class Solution {

    public static int find(int[] array, int target) {
        if (array == null || array.length == 0) return -1;
        int length = array.length;
        int left = 0, right = length - 1, mid = 0;
        int res = -1;

        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (array[mid] == target) {
                return mid;
            }
            //左边有序
            if (array[left] <= array[mid]) {
                if (target < array[mid] && target >= array[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {//右边有序
                if (target > array[mid] && target <= array[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] array = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        System.out.println(find(array, 1));
    }
}
