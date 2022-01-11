import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// 1. BFS
/* 
 * 复杂度分析
 * 时间复杂度：O(R×C)。其中 R 是给定网格中的行数，C 是列数。我们访问每个网格最多一次。
 * 空间复杂度：O(R×C)，队列中最多会存放所有的土地，土地的数量最多为 R×C 块，因此使用的空间为 O(R×C)。
 */
class Solution1 {
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, 1, -1, 0 };

    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int areaSize = maxAreaFromPointBFS(grid, i, j);
                    maxArea = areaSize > maxArea ? areaSize : maxArea;
                }
            }
        }
        return maxArea;
    }

    public int maxAreaFromPointBFS(int[][] grid, int sr, int sc) {
        if (grid[sr][sc] == 0)
            return 0;
        int count = 1;
        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        grid[sr][sc] = 0;
        queue.offer(new int[] { sr, sc });
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];
                if (mx >= 0 && mx < row && my >= 0 && my < col && grid[mx][my] == 1) {
                    grid[mx][my] = 0;
                    queue.offer(new int[] { mx, my });
                    count++;
                }
            }
        }
        return count;
    }
}

// 2. DFS
/*
 * 复杂度分析
 * 时间复杂度：O(R×C)。其中 R 是给定网格中的行数，C 是列数。我们访问每个网格最多一次。
 * 空间复杂度：O(R×C)，递归的深度最大可能是整个网格的大小，因此最大可能使用 O(R×C) 的栈空间。
 */
class Solution2 {
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, 1, -1, 0 };

    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int areaSize = maxAreaFromPointDFS(grid, i, j);
                    maxArea = areaSize > maxArea ? areaSize : maxArea;
                }
            }
        }
        return maxArea;
    }

    public int maxAreaFromPointDFS(int[][] grid, int sr, int sc) {
        if (grid[sr][sc] == 0)
            return 0;
        grid[sr][sc] = 0;
        int row = grid.length;
        int col = grid[0].length;
        int areaSize = 1;
        for (int i = 0; i < 4; i++) {
            int mx = sr + dx[i];
            int my = sc + dy[i];
            if (mx >= 0 && mx < row && my >= 0 && my < col) {
                areaSize = areaSize + maxAreaFromPointDFS(grid, mx, my);
            }
        }
        return areaSize;
    }
}

// 3. DFS + 栈
/*
 * 我们可以用栈来实现深度优先搜索算法。这种方法本质与 DFS 相同，唯一的区别是：
 * DFS 通过函数的调用来表示接下来想要遍历哪些土地，让下一层函数来访问这些土地。
 * 而这一方法把接下来想要遍历的土地放在栈里，然后在取出这些土地的时候访问它们。
 * 访问每一片土地时，我们将对围绕它四个方向进行探索，找到还未访问的土地，加入到栈 stack 中；
 * 另外，只要栈 stack 不为空，就说明我们还有土地待访问，那么就从栈中取出一个元素并访问。
 */
/* Queue是队列，只能一头进，另一头出。
 * 如果把条件放松一下，允许两头都进，两头都出，这种队列叫双端队列（Double Ended Queue），学名Deque。
 * Java集合提供了接口Deque来实现一个双端队列，它的功能是：
 * 既可以添加到队尾，也可以添加到队首；
 * 既可以从队首获取，又可以从队尾获取。
 */
class Solution {
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, 1, -1, 0 };

    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int areaSize = 1;
                    grid[i][j] = 0;
                    Deque<Integer> stacki = new LinkedList<Integer>();
                    Deque<Integer> stackj = new LinkedList<Integer>();
                    stacki.push(i);
                    stackj.push(j);
                    while (!stacki.isEmpty()) {
                        int cur_i = stacki.pop();
                        int cur_j = stackj.pop();
                        for (int k = 0; k < 4; k++) {
                            int next_i = cur_i + dx[k];
                            int next_j = cur_j + dy[k];
                            if (next_i >= 0 && next_i < row && next_j >= 0 && next_j < col
                                    && grid[next_i][next_j] == 1) {
                                grid[next_i][next_j] = 0;
                                areaSize++;
                                stacki.push(next_i);
                                stackj.push(next_j);
                            }
                        }
                    }
                    maxArea = areaSize > maxArea ? areaSize : maxArea;
                }
            }
        }
        return maxArea;
    }
}