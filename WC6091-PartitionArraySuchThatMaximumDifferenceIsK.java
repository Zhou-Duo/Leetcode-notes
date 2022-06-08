import java.util.Arrays;

// https://leetcode.cn/problems/partition-array-such-that-maximum-difference-is-k/
class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 1;
        int firstElement = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - firstElement > k) {
                count += 1;
                firstElement = nums[i];
            }
        }
        return count;
    }
}
