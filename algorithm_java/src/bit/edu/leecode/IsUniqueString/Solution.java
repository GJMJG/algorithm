package bit.edu.leecode.IsUniqueString;

import java.util.HashSet;

class Solution {
    public boolean isUnique(String astr) {
        HashSet<Character> set = new HashSet<>();
        for (char c : astr.toCharArray()) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isUnique(""));
    }
}