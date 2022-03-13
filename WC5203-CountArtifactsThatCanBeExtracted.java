// 注意：别忘了可以用新数组记录是否存在
// https://leetcode-cn.com/problems/count-artifacts-that-can-be-extracted/
class Solution {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        int[][] mark = new int[n][n];
        for (int[] digCell : dig) {
            mark[digCell[0]][digCell[1]] = 1;
        }
        int res = 0;
        for (int[] artifact : artifacts) {
            boolean check = true;
            for (int row = artifact[0]; row <= artifact[2]; row++) {
                for (int col = artifact[1]; col <= artifact[3]; col++) {
                    if (mark[row][col] != 1)
                        check = false;
                }
            }
            if (check == true)
                res++;
        }
        return res;
    }
}