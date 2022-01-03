// 1. 双指针
// 时间复杂度: O(n), 空间复杂度: O(1)
class Solution1 {
    public void moveZeroes(int[] nums) {
        int currentPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                nums[currentPos++] = nums[i];
        }
        for (int i = currentPos; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

// 2. 快速排序
/*
* 参考快速排序思想，以 0 为 pivot，遍历元素，若元素不等于 0，则交换至左侧，若等于 0，则交换至右侧
* 时间复杂度: O(n), 空间复杂度: O(1)
*/
class Solution {
    public void moveZeroes(int[] nums) {
        int currentPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[currentPos];
                nums[currentPos++] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}