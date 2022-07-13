import java.util.Arrays;

// 1. DFS回溯
class Solution1 {
    public int findTargetSumWays(int[] nums, int target) {
        int count = 0;
        int len = nums.length;
        if (len == 0)
            return count;
        if (len == 1) {
            if (nums[0] == target)
                count += 1;
            if (-nums[0] == target)
                count += 1;
            return count;
        }
        count += findTargetSumWays(Arrays.copyOfRange(nums, 1, len), target - nums[0]);
        count += findTargetSumWays(Arrays.copyOfRange(nums, 1, len), target + nums[0]);
        return count;
    }
}

// 2. 动态规划
/*
 * f[i][j] 代表考虑前 i 个数，当前计算结果为 j 的方案数，令 nums 下标从 1 开始。
 * f[i][j] = f[i - 1][j - nums[i - 1]] + f[i - 1][j - nums[i + 1]];
 * f[n][target] 为最终答案，f[0][0] = 1 为初始条件：代表不考虑任何数，凑出计算结果为 0 的方案数为 1 种。
 */
class Solution2 {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (Math.abs(target) > sum)
            return 0;
        int[][] dp = new int[n + 1][2 * sum + 1];
        dp[0][0 + sum] = 1;
        for (int i = 1; i < n + 1; i++) {
            int x = nums[i - 1];
            for (int j = -sum; j <= sum; j++) {
                if ((j - x) + sum >= 0)
                    dp[i][j + sum] += dp[i - 1][(j - x) + sum];
                if ((j + x) + sum <= 2 * sum)
                    dp[i][j + sum] += dp[i - 1][(j + x) + sum];
            }
        }
        return dp[n][target + sum];
    }
}

// 3. 优化动态规划（背包问题）
/*
 * 划分为「负值部分」&「非负值部分」，令「负值部分」的绝对值总和为 m，即可得：
 * (s - m) - m = s - 2 * m = target
 * 变形得：m = \frac{s - target}{2}
 * 问题转换为：只使用 + 运算符，从 nums 凑出 m 的方案数。
 * 定义 f[i][j] 为从 nums 凑出总和「恰好」为 j 的方案数。
 * 最终答案为 f[n][m]，f[0][0] = 1 为起始条件：代表不考虑任何数，凑出计算结果为 0 的方案数为 1 种。
 * 每个数值有「选」和「不选」两种决策，转移方程为：
 * f[i][j] = f[i - 1][j] + f[i - 1][j - nums[i - 1]]
 */
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (Math.abs(target) > sum || (sum - target) % 2 != 0)
            return 0;
        int m = (sum - target) / 2;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] += dp[i - 1][j];
                if (j >= nums[i - 1])
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[n][m];
    }
}
