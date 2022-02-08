// 1. 二分查找 + 记录
/* 
 * 时间复杂度：时间复杂度为 O(logn)，其中 n 是数组 nums 的长度。
 * 在二分查找的过程中，每一步会忽略一半的区间，因此时间复杂度为 O(logn)。
 * 空间复杂度：O(1)。
 */
class Solution1 {
    public int findMin(int[] nums) {
        int length = nums.length;
        if (length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1; // 左右双闭
        int min = nums[0];
        while (left <= right) { // terminate when left = right + 1
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];
            if (midNum >= nums[left]) { // 左段有序，左段最小为 nums[left]
                min = nums[left] < min ? nums[left] : min;
                // 继续寻找右段最小
                left = left + 1;
            }
            else if (midNum < nums[left]) { // 右段有序，右段最小为 nums[mid]
                min = nums[mid] < min ? nums[mid] : min;
                // 继续寻找左段最小
                right = right - 1;
            }
        }
        return min;
    }
}

// 2. 二分查找
class Solution2 {
    public int findMin(int[] nums) {
        int length = nums.length;
        if (length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1; // 左右双闭
        while (left <= right) { // terminate when left = right + 1
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];
            if (midNum >= nums[left]) { // 左段有序，左段最小为 nums[left]
                if (nums[left] >= nums[right]) // rotate
                    left = mid + 1;
                else return nums[left];
            }
            else if (midNum < nums[left]) { // 右段有序，右段最小为 nums[mid]
                right = right - 1;
            }
        }
        return nums[right];
    }
}