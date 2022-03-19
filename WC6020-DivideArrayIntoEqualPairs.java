// https://leetcode-cn.com/problems/divide-array-into-equal-pairs/
class Solution {
    public boolean divideArray(int[] nums) {
        int[] counts = new int[500];
        for (int num : nums) {
            counts[num - 1]++;
        }
        for (int count : counts) {
            if (count % 2 != 0)
                return false;

        }
        return true;
    }
}