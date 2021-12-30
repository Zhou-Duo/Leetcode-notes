// 找最小的比target大的数
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num == target)
                right = right - 1;
            else if (num > target)
                right = right - 1;
            else if (num < target)
                left = left + 1;
        }
        return left;
    }
}