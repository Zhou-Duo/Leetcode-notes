// https://leetcode-cn.com/problems/count-equal-and-divisible-pairs-in-an-array/

// 模拟
/* 
 * 复杂度分析
 * 时间复杂度：O(n^2)，其中 n 为 nums 数组的长度。即为遍历数对并统计符合要求个数的时间复杂度。
 * 空间复杂度：O(1)。
 */
class Solution {
    public int countPairs(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j] && i * j % k == 0)
                    res++;
            }
        }
        return res;
    }
}