// 1. 模拟
class Solution1 {
    public int[] findBall(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[] res = new int[cols];
        for (int i = 0; i < cols; i++) {
            res[i] = findSingleBall(grid, i);
        }
        return res;
    }

    public int findSingleBall(int[][] grid, int start) {
        int row = 0, col = start;
        int rows = grid.length, cols = grid[0].length;
        while (row >= 0 && col >= 0 && row < rows && col < cols) {
            int direction = grid[row][col];
            int nextGridCol = col + direction;
            if (nextGridCol >= 0 && nextGridCol < cols && direction != grid[row][nextGridCol])
                return -1;
            row += 1;
            col += direction;
        }
        if (row == rows && col < cols)
            return col;
        else
            return -1;
    }
}

class Solution2 {
    public int[] findBall(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[] res = new int[cols];
        for (int i = 0; i < cols; i++) {
            int col = i; // 球的初始列
            for (int[] row : grid) {
                int dir = row[col];
                col += dir; // 移动球
                if (col < 0 || col == cols || row[col] != dir) { // 到达侧边或 V 形
                    res[i] = -1;
                    break;
                }
            }
            res[i] = col; // col >= 0 为成功到达底部
        }
        return res;
    }
}

// 2. DFS
class Solution3 {
    int rows, cols;

    public int[] findBall(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int[] res = new int[cols];
        for (int i = 0; i < cols; i++) {
            res[i] = dfs(grid, 0, i);
        }
        return res;
    }

    public int dfs(int[][] grid, int row, int col) {
        if (row == rows)
            return col;
        int dir = grid[row][col];
        col += dir;
        if (col >= 0 && col < cols && grid[row][col] == dir)
            return dfs(grid, row + 1, col);
        else
            return -1;
    }
}
