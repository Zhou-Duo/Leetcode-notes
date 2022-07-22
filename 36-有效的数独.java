import java.util.HashMap;
import java.util.HashSet;

// 1. 每行、每列、每个小方块使用哈希集合查重
class Solution1 {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Integer>> row = new HashMap<>();
        Map<Integer, Set<Integer>> col = new HashMap<>();
        Map<Integer, Set<Integer>> subbox = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            subbox.put(i, new HashSet<>());
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;
                int num = c - '0';
                int subboxIdx = i / 3 * 3 + j / 3;
                if (!row.get(i).add(num) || !col.get(j).add(num) || !subbox.get(subboxIdx).add(num))
                    return false;
            }
        }
        return true;
    }
}

// 2. 数组
class Solution2 {
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] subboxes = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '0' - 1;
                    int subboxIdx = i / 3 * 3 + j / 3;
                    rows[i][num] += 1;
                    cols[j][num] += 1;
                    subboxes[subboxIdx][num] += 1;
                    if (rows[i][num] > 1 || cols[j][num] > 1 || subboxes[subboxIdx][num] > 1)
                        return false;
                }
            }
        }
        return true;
    }
}
