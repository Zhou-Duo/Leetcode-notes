// 有序 且 时间复杂度要求为 O(logN) => binary search
class Solution {
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1; // 左右双闭
        while (left <= right) { // terminate when left = right + 1
            int mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;
            else if (nums[mid] >= nums[0]) { // 左段有序
                if (target >= nums[0] && target <= nums[mid]) // target 在 [nums[0],nums[mid]]
                    right = mid - 1;
                else left = mid + 1;
            }
            else { // 右段有序
                if (target >= nums[mid] && target <= nums[length-1]) // target 在 [nums[mid],nums[length-1]]
                    left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }
}