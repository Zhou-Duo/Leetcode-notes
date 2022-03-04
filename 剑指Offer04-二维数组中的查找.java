// 线性查找
/* 
 * 复杂度分析：
 * 时间复杂度 O(M+N) ：其中，N 和 M 分别为矩阵行数和列数，此算法最多循环 M+N 次。
 * 空间复杂度 O(1) : i, j 指针使用常数大小额外空间。
 */
class Solution {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 从 (n - 1, 0) 开始
        int n = matrix.length;
        if (n == 0)
            return false;
        int m = matrix[0].length;
        int row = n - 1, col = 0;
        while (0 <= row && col < m) {
            int num = matrix[row][col];
            if (num == target)
                return true;
            else if (num < target)
                col += 1;
            else row -= 1;
        }
        return false;
    }
}