// 1. 动态规划
class Solution1 {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

// 2. 优化空间复杂度
class Solution2 {
    public int climbStairs(int n) {
        int a = 0, b = 0, c = 1;
        for (int i = 0; i < n; i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }
}