/* 
 * 1. 规律 + DP
 * 对于含有正数的序列而言,最大子序列肯定是正数,所以头尾肯定都是正数.
 * 我们可以从第一个正数开始算起,每往后加一个数便更新一次和的最大值;
 * 当当前和成为负数时,则表明此前序列无法为后面提供最大子序列和,因此必须重新确定序列首项.
 */
class Solution1 {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0)
                sum += num;
            else
                sum = num;
        }
        res = Math.max(res, sum);
        return res;
    }
}

/* 
 * 2. 动态规划
 * 思路和算法
 * 假设 nums 数组的长度是 n，下标从 0 到 n−1。
 * 我们用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」
 * 我们只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。
 * 那么我们如何求 f(i) 呢？我们可以考虑 ums[i] 单独成为一段还是加入 f(i−1) 对应的那一段。
 * 这取决于 nums[i] 和 f(i−1)+nums[i] 的大小，我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
 * f(i)=max{f(i−1)+nums[i],nums[i]}
 * 时间复杂度： O(n)
 * 空间复杂度 O(1)
 * 考虑到 f(i) 只和 f(i−1) 相关，于是我们可以只用一个变量 pre 来维护对于当前 f(i) 的 f(i−1) 的值是多少，从而让空间复杂度降低到 O(1)，这有点类似「滚动数组」的思想。
 */
class Solution2 {
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}
