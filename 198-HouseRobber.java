// 1. DP
class Solution1 {
    public int rob(int[] nums) {
        if (nums.length <= 0)
            return 0;
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= N; k++) {
            dp[k] = Math.max(dp[k - 1], nums[k - 1] + dp[k - 2]);
        }
        return dp[N];
    }
}

// 2. 优化空间
/* 
 * 复杂度分析
 * 时间复杂度：O(n)，其中 nn 是数组长度。只需要对数组遍历一次。
 * 空间复杂度：O(1)。使用滚动数组，可以只存储前两间房屋的最高总金额，而不需要存储整个数组的结果，因此空间复杂度是 O(1)。
 */
class Solution {
    public int rob(int[] nums) {
        if (nums.length <= 0)
            return 0;
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
        int N = nums.length;
        int a = 0, b = 0, c = nums[0];
        for (int k = 2; k <= N; k++) {
            a = b;
            b = c;
            c = Math.max(b, nums[k-1]+a);
        }
        return c;
    }
}
