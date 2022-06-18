import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 1. BFS
class Solution1 {
    public int numSquares(int n) {
        // queue 中保存状态，不保存路径（例如此题，不需要列出具体是哪几个完全平方数）
        Queue<Integer> queue = new LinkedList<Integer>();
        // 通过不同路径达到的相同sum只保存一个，否则会超时
        Set<Integer> visited = new HashSet<Integer>();
        int step = 0;
        queue.add(0);
        while (!queue.isEmpty()) {
            step += 1;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int j = 0; j <= Math.sqrt(n); j++) {
                    int sum = cur + j * j;
                    if (sum == n)
                        return step;
                    if (sum > n)
                        break;
                    if (!visited.contains(sum)) {
                        queue.offer(sum);
                        visited.add(sum);
                    }
                }
            }
        }
        return -1;
    }
}

// 2. DP
class Solution2 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            int minDP = Integer.MAX_VALUE;
            for (int j = 1; j <= Math.sqrt(i); j++) {
                minDP = Math.min(minDP, dp[i - j * j]);
            }
            dp[i] = 1 + minDP;
        }
        return dp[n];
    }
}