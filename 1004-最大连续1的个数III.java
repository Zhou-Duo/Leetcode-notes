class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, res = 0;
        // window 内 0 的个数
        int numZero = 0;
        while (right < nums.length) {
            numZero += nums[right] == 0 ? 1 : 0;
            // 只要右边界不越界，而且 0 的个数小于等于 1 个，那么就扩充右边界
            if (numZero > k) {
                numZero -= nums[left] == 0 ? 1 : 0;
                left++;
            }
            right++;
        }
        return right - left;
    }
}