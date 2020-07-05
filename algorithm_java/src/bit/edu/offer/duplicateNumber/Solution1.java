package bit.edu.offer.duplicateNumber;

import java.util.HashSet;
import java.util.Set;

class Solution1 {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> table = new HashSet<Integer>();
        for (int num : nums) {
            if (!table.add(num)) {
                return num;
            }
        }
        return -1;
    }
}