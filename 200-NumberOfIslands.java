import java.util.LinkedList;
import java.util.Queue;

// 1. BFS
/* 
 * 复杂度分析
 * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
 * 空间复杂度：O(MN)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到 MN。
 */
class Solution1 {
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, 1, -1, 0 };

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    numIslandsBFS(grid, i, j);
                }
            }
        }
        return count;
    }

    public void numIslandsBFS(char[][] grid, int sr, int sc) {
        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        grid[sr][sc] = '0';
        queue.offer(new int[] { sr, sc });
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];
                if (mx >= 0 && mx < row && my >= 0 && my < col && grid[mx][my] == '1') {
                    grid[mx][my] = '0';
                    queue.offer(new int[] { mx, my });
                }
            }
        }
    }
}

// 2. DFS
class Solution {
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, 1, -1, 0 };

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    numIslandsDFS(grid, i, j);
                }
            }
        }
        return count;
    }

    public void numIslandsDFS(char[][] grid, int sr, int sc) {
        int row = grid.length;
        int col = grid[0].length;
        grid[sr][sc] = '0';
        for (int i = 0; i < 4; i++) {
            int mx = sr + dx[i];
            int my = sc + dy[i];
            if (mx >= 0 && mx < row && my >= 0 && my < col && grid[mx][my] == '1') {
                numIslandsDFS(grid, mx, my);
            }
        }
    }
}
