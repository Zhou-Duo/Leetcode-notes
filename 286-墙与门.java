import java.util.ArrayList;
import java.util.Queue;

// 1. BFS
class Solution1 {
    private int[] dx = { 1, 0, 0, -1 };
    private int[] dy = { 0, 1, -1, 0 };

    public void wallsAndGates(int[][] rooms) {
        int row = rooms.length;
        if (row == 0)
            return;
        int col = rooms[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    // 从这个门 BFS
                    // **多源BFS需要同时将多个起点加入队列，最好不对多起点分别BFS
                    queue.add(new int[] { i, j });
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] curRoom = queue.poll();
            int x = curRoom[0], y = curRoom[1];
            for (int m = 0; m < 4; m++) {
                int mx = x + dx[m];
                int my = y + dy[m];
                if (mx >= 0 && mx < row && my >= 0 && my < col && rooms[mx][my] == Integer.MAX_VALUE) {
                    rooms[mx][my] = Math.min(rooms[mx][my], rooms[x][y] + 1);
                    queue.offer(new int[] { mx, my });
                }
            }
        }
    }
}

// 2. DFS
class Solution {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    public void dfs(int[][] rooms, int row, int col, int dist) {
        if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length || rooms[row][col] < dist)
            return;
        // 保留从门开始DFS的情况
        else if (dist != 0 && rooms[row][col] <= dist) 
            return;
        rooms[row][col] = dist;
        dfs(rooms, row + 1, col, dist + 1);
        dfs(rooms, row - 1, col, dist + 1);
        dfs(rooms, row, col + 1, dist + 1);
        dfs(rooms, row, col - 1, dist + 1);
    }
}
