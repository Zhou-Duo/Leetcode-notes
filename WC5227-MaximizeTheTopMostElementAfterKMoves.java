// 题意理解错误，不一定需要按顺序入栈
// https://leetcode-cn.com/problems/maximize-the-topmost-element-after-k-moves/
class Solution {
    public int maximumTop(int[] nums, int k) {
        // nums 为空，返回 -1
        // nums.length == 1 且 k % 2 == 1, 返回 -1
        // 若最大值在 nums[0] - nums[k-2], 第 k 次操作压回最大值
        // 若最大值操作为 nums[k],则 k 次操作用于删除 nums[0] - nums[k-1]
        if (nums.length == 0)
            return -1;
        if (k == 0)
            return nums[0];
        if (nums.length == 1 && k % 2 == 1)
            return -1;

        int res = -1;
        for (int i = 0; i < k - 1 && i < nums.length; i++) {
            res = Math.max(res, nums[i]);
        }
        if (k < nums.length)
            res = Math.max(res, nums[k]);
        return res;
    }
}