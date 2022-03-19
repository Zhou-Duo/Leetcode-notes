// https://leetcode-cn.com/problems/minimum-white-tiles-after-covering-with-carpets/
// DP
// 为了快速统计使用一条地毯可以覆盖的白色砖块数量，所以需要使用前缀和，sum[i] 表示前 i 块砖块中的白色砖块数量。
// f[i][j] 表示截止至第 i 块砖块，使用了 j 条地毯可以覆盖的最多的白色砖块数量。状态注意有两种情况：
// 1. 在第 i 块砖块上不覆盖地毯，f[i - 1][j]
// 2. 在第 i 块砖块上向前覆盖一条地毯，f[last][j - 1] + sum[i] - sum[last]

class Solution {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        if (numCarpets * carpetLen > floor.length())
            return 0;
        // 求出前缀和
        int[] sum = new int[floor.length() + 1];
        sum[0] = 0;
        for (int i = 0; i < floor.length(); i++) {
            sum[i + 1] = sum[i] + Character.getNumericValue(floor.charAt(i));
        }
        // DP
        int res = 0;
        int[][] dp = new int[floor.length() + 1][numCarpets + 1];
        for (int i = 1; i < floor.length() + 1; i++) {
            int last = Math.max(i - carpetLen, 0);
            for (int j = 1; j < numCarpets + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[last][j - 1] + sum[i] - sum[last]);
                res = Math.max(res, dp[i][j]);
            }
        }
        return sum[floor.length()] - res;
    }
}