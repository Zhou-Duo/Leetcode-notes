// 1. 2 次二分查找
/* 
 * 复杂度分析
 * 时间复杂度：O(logm+logn)=O(logmn)，其中 m 和 n 分别是矩阵的行数和列数。
 * 空间复杂度：O(1)。
 */
class Solution1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (m == 0 || n == 0)
            return false;
        // 先确定 row
        int left = 0;
        int right = m - 1;
        while (left < right) { // terminates when left = right
            int mid = left + (right - left + 1) / 2;
            if (target >= matrix[mid][0])
                left = mid;
            else right = mid - 1;
        }
        int row = left;

        // 在确定 col
        left = 0;
        right = n - 1;
        while (left <= right) { // terminates when left = right
            int mid = left + (right - left) / 2;
            if (target == matrix[row][mid])
                return true;
            else if (target > matrix[row][mid])
                left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}

// 2. 坐标轴法
/* 
 * 根据题意已知，二维数组从左往右递增，从上往下递增，所以得出以下结论：
 * 
 * 某列的某个数字，该数之上的数字，都比其小；
 * 某行的某个数字，该数右侧的数字，都比其大；
 * 
 * 所以，解题流程如下所示：
 * 以二维数组左下角为原点，建立直角坐标轴。
 * 若当前数字大于了查找数，查找往上移一位。
 * 若当前数字小于了查找数，查找往右移一位。
 */
// 定好起点！
class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length - 1;
        int n = 0;
        while (m >= 0 && n < matrix[0].length) {
            int num = matrix[m][n];
            if (num == target) {
                return true;
            } else if (num > target) {
                m--;
            } else {
                n++;
            }
        }
        return false;
    }
}