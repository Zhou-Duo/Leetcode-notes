import java.util.Arrays;

// 对连续字数组： 1. 滑动窗口 2. 前缀和 
// 1。 滑动窗口
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0; // 左右双闭
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right];
            while (sum > target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

// 2. 前缀和+二分法
/* 我们申请一个临时数组 sums，其中 sums[i] 表示的是原数组 nums 前 i 个元素的和.
 * 题中说了 “给定一个含有 n 个 正整数 的数组”，既然是正整数，那么相加的和会越来越大，也就是sums数组中的元素是递增的。
 * 我们只需要找到 sums[k]-sums[j]>=s，那么 k-j 就是满足的连续子数组，但不一定是最小的，所以我们要继续找，直到找到最小的为止。
 * 我们可以换种思路，求 sums[k]-sums[j]>=s 我们可以求 sums[j]+s<=sums[k]，那这样就好办了，因为数组sums中的元素是递增的，也就是排序的，我们只需要求出 sum[j]+s 的值，然后使用二分法查找即可找到这个 k。
 */
class Solution2 {
    public int minSubArrayLen(int s, int[] nums) {
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        int[] sums = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 0; i <= length; i++) {
            int target = s + sums[i];
            int index = Arrays.binarySearch(sums, target);//如果找到就会返回值的下标，如果没找到就会返回一个负数，这个负数取反之后就是查找的值应该在数组中的位置
            if (index < 0) 
                index = -index;
            if (index <= length) {
                min = Math.min(min, index - i);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
