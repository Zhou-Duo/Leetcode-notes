class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int row = grid.length;
        int col = grid[0].length;
        int[] leastMoveCost = new int[row * col];
        // 最后一行填充
        for (int j = 0; j < col; j++) {
            leastMoveCost[grid[row - 1][j]] = grid[row - 1][j];
        }
        // 除最后一行外
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                leastMoveCost[grid[i][j]] += grid[i][j];
                int leastSOC = Integer.MAX_VALUE;
                for (int k = 0; k < col; k++) {
                    int SOC = leastMoveCost[grid[i + 1][k]] + moveCost[grid[i][j]][k];
                    leastSOC = SOC < leastSOC ? SOC : leastSOC;
                }
                leastMoveCost[grid[i][j]] += leastSOC;
            }
        }
        // 选出第一行最小的moveCost
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < col; j++) {
            res = leastMoveCost[grid[0][j]] < res ? leastMoveCost[grid[0][j]] : res;
        }
        return res;
    }
}