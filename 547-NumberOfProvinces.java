import java.util.LinkedList;
import java.util.Queue;

// 1. BFS
/* 
 * 复杂度分析
 * 时间复杂度：O(n^2)，其中 n 是城市的数量。需要遍历矩阵 isConnected 中的每个元素。
 * 空间复杂度：O(n)，其中 n 是城市的数量。需要使用数组 visited 记录每个城市是否被访问过，数组长度是 n，广度优先搜索使用的队列的元素个数不会超过 n。
 */
class Solution1 {
    public int findCircleNum(int[][] isConnected) {
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    count++;
                    findCircleNumBFS(isConnected,i);
                    break;
                }
            }    
        }  
        return count;
    }
    
    public void findCircleNumBFS(int[][]isConnected,int i) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(i);
        while (!queue.isEmpty()) {
            int row = queue.poll();
            for (int col = 0; col < isConnected.length; col++) {
                if (isConnected[row][col] == 1) {
                    queue.offer(col);
                    isConnected[row][col] = 0;
                    isConnected[col][row] = 0;
                }
            }
        }
    }
}

// 2. BFS
class Solution2 {
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int city = queue.poll();
                    visited[city] = true;
                    for (int j = 0; j < isConnected.length; j++) {
                        if (isConnected[city][j] == 1 && !visited[j])
                            queue.offer(j);
                    }
                }
                count++;
            }
        }
        return count;
    }
}

// 3. DFS
/* 
 * 复杂度分析
 * 时间复杂度：O(n^2)，其中 n 是城市的数量。需要遍历矩阵 n 中的每个元素。
 * 空间复杂度：O(n)，其中 n 是城市的数量。需要使用数组 visited 记录每个城市是否被访问过，数组长度是 n，递归调用栈的深度不会超过 n。
 */
class Solution3 {
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                count++;
                dfs(isConnected, visited, i);
            }
        }
        return count;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int i) {
        for (int k = 0; k < isConnected.length; k++) {
            if (isConnected[i][k] == 1 && !visited[k]) {
                visited[k] = true;
                dfs(isConnected, visited, k);
            }
        }
    }
}

// 4. Union & Find
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int[] parent = new int[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (parent[i] == i)
                count++;
        }
        return count;    
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index)
            parent[index] = find(parent, parent[index]);
        return parent[index];
    }
}