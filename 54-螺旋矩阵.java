import java.util.List;

// 1. 按层模拟
class Solution1 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int circleNum = (int) Math.ceil((Math.min(m, n) / 2.0));
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < circleNum; i++) {
            res.addAll(spiralOrderOuter(matrix, i));
        }
        return res;
    }

    // i 代表第几圈，最外圈为第 0 圈
    public List<Integer> spiralOrderOuter(int[][] matrix, int i) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new LinkedList<>();
        // 上
        for (int col = i; col < n - i - 1; col++)
            res.add(matrix[i][col]);
        // 右
        for (int row = i; row < m - i; row++)
            res.add(matrix[row][n - i - 1]);
        if (2 * i == m - 1 || 2 * i == n - 1)
            return res;
        // 下
        for (int col = n - i - 2; col >= i; col--)
            res.add(matrix[m - i - 1][col]);
        // 左
        for (int row = m - i - 2; row >= i + 1; row--)
            res.add(matrix[row][i]);
        return res;
    }
}

// 2. 转向模拟
class Solution2 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;
        int rows = matrix.length;
        int cols = matrix[0].length;

        int total = rows * cols;
        int row = 0, col = 0;
        boolean[][] visited = new boolean[rows][columns];
        // 右 - 下 - 左 - 上
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            res.add(matrix[row][col]);
            visited[row][col] = true;
            int nextRow = row + directions[directionIndex][0];
            int nextCol = col + directions[directionIndex][1];
            if (nextRow < 0 || nextCol < 0 || nextRow >= rows || nextCol >= cols || visited[nextRow][nextCol])
                directionIndex = (directionIndex + 1) % 4;
            row += directions[directionIndex][0];
            col += directions[directionIndex][1];
        }
        return res;
    }
}