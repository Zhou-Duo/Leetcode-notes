// 1. 模拟
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int k = A[0].length;
        int n = B[0].length;
        int[][] ans = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int p = 0; p < k; p++) {
                    ans[i][j] += A[i][p] * B[p][j]
                }
            }
        }
        return ans;
    }
}

// 2. 针对稀疏矩阵稍微改进
class Solution2 {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int k = A[0].length;
        int n = B[0].length;
        int[][] ans = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int p = 0; p < k; p++) { // 每一项需要累加的个数
                if (A[i][p] == 0) { // 即: 矩阵A中第C个0元素项目, 没有必要参与后面的累加了
                    continue; // p++, 跳到下一个不为零的 a[i][c]上
                }
                for (int j = 0; j < n; j++) {
                    ans[i][j] += A[i][p] * B[p][j];
                }
            }
        }
        return ans;
    }
}
