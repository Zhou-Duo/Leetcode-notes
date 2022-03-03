// 1. 遍历数组
/*
 * 空间复杂度: O(n)
 * 时间复杂度: O(n)
 */

// 2. 二分法 （排序数组）
class Solution {
    public int search(int[] nums, int target) {
        int low = searchLowBoundary(nums, target);
        int high = searchHighBoundary(nums, target);
        if (low == -1)
            return 0;
        return (high - low + 1);
    }

    public int searchLowBoundary(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 左闭右开
        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num < target) {
                left = mid + 1;
            }
            else if (num > target) {
                right = mid;
            }
            else {
                // num == target
                right = mid;
            }
        }
        if (left == nums.length)
            return -1;
        else return nums[left] == target ? left : -1;
    }

    public int searchHighBoundary(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 左闭右开
        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num < target) {
                left = mid + 1;
            }
            else if (num > target) {
                right = mid;
            }
            else {
                // num == target
                left = mid + 1;
            }
        }
        if (left == 0)
            return -1;
        else return nums[left-1] == target ? left - 1 : -1;
    }
}