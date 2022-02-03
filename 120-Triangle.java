import java.util.List;

/* 
 * 1. 动态规划
 * 复杂度分析
 * 时间复杂度：O(n^2)，其中 n 是三角形的行数。
 * 空间复杂度：O(n^2)。我们需要一个 n*n 的二维数组存放所有的状态。
 */
class Solution1 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size(); // n 为三角形层数
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i-1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i-1][j-1], f[i-1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i-1][i-1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }
}

/* 
 * 2. 动态规划 + 空间优化
 * f[i][j] 只与 f[i-1][..]有关，而与 f[i-2][..]及之前的状态无关，因此我们不必存储这些无关的状态。
 * 具体地，我们使用两个长度为 n 的一维数组进行转移，将 i 根据奇偶性映射到其中一个一维数组，那么 i−1 就映射到了另一个一维数组。
 * 这样我们使用这两个一维数组，交替地进行状态转移。
 * 复杂度分析
 * 时间复杂度：O(n^2)，其中 n 是三角形的行数。
 * 空间复杂度：O(n)。
 */
class Solution2 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size(); // n 为三角形层数
        int[][] f = new int[2][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            int curr = i % 2;
            int prev = 1 - curr;
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                f[curr][j] = Math.min(f[prev][j-1], f[prev][j]) + triangle.get(i).get(j);
            }
            f[curr][i] = f[prev][i-1] + triangle.get(i).get(i);
        }
        int minTotal = f[(n - 1) % 2][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[(n - 1) % 2][i]);
        }
        return minTotal;
    }
}

/* 
 * 3. 动态规划（改为自底向上的递推）
 * dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
 * dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
 * 时间复杂度：O(N^2)，N 为三角形的行数。
 * 空间复杂度：O(N^2)，N 为三角形的行数。
 */
class Solution3 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        int[][] dp = new int[n + 1][n + 1]; // 多 declare 一个以防超过边界
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}

/* 
 * 4. 动态规划（改为自底向上的递推）+ 空间优化
 * 在上述代码中，我们定义了一个 N 行 N 列 的 dp 数组（N 是三角形的行数）。
 * 但是在实际递推中我们发现，计算 p[i][j] 时，只用到了下一行的 dp[i+1][j] 和 dp[i+1][j+1]。
 * 因此 dp 数组不需要定义 N 行，只要定义 1 行就阔以啦。
 * 时间复杂度：O(N^2)，N 为三角形的行数。
 * 空间复杂度：O(N), N 为三角形的行数。
 */
class Solution4 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1]; // 多 declare 一个以防超过边界
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}