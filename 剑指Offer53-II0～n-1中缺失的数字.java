class Solution {
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 左开右闭
            // 终止条件为 left == right + 1
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num == mid) {
                left = left + 1;
            }
            else if (num > mid) {
                right = right - 1;
            }
            
        }
        return left;
    }
}