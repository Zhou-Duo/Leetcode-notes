import java.util.LinkedList;
import java.util.Queue;

// 多元 BFS 记录层数
/* 
 * 复杂度分析
 * 时间复杂度：O(nm)
 * 即进行一次广度优先搜索的时间，其中 n=grid.lengthn, m=grid[0].length。
 * 空间复杂度：O(nm)
 * 广度优先搜索中队列里存放的状态最多不会超过 nm 个，最多需要 O(nm) 的空间，所以最后的空间复杂度为 O(nm)。
 */
class Solution {
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, 1, -1, 0 };

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        // 对 rotten 和 fresh 计数，将 rotten 果子加入 queue
        int rotten = 0;
        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    rotten++;
                    queue.offer(new int[] { i, j });
                } else if (grid[i][j] == 1)
                    fresh++;
            }
        }

        // 如果水果已经全部 rotten，则直接结束
        if (fresh == 0)
            return 0;

        // 开始多元 BFS
        int minCount = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int[] currPoint = queue.poll();
                int currX = currPoint[0], currY = currPoint[1];
                for (int i = 0; i < 4; i++) {
                    int newX = currX + dx[i];
                    int newY = currY + dy[i];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        queue.offer(new int[] {newX, newY});
                        fresh--;
                    }
                }
            }
            minCount++;
        }

        // 验证是否所有水果都 rotten
        if (fresh == 0)
            return minCount;
        else
            return -1;
    }
}