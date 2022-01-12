import java.util.LinkedList;
import java.util.Queue;

// 1. 多元 BFS
/* 
 * 思路：
 * 对于 「Tree 的 BFS」 （典型的「单源 BFS」） 大家都已经轻车熟路了：
 *      首先把 root 节点入队，再一层一层无脑遍历就行了。
 * 对于 「图 的 BFS」 （「多源 BFS」） 做法其实也是一样滴～，与 「Tree 的 BFS」的区别注意以下两条就 ok 辣～
 * 1. Tree 只有 1 个 root，而图可以有多个源点，所以首先需要把多个源点都入队；
 * 2. Tree 是有向的因此不需要标识是否访问过，而对于无向图来说，必须得标志是否访问过哦！并且为了防止某个节点多次入队，需要在其入队之前就将其设置成已访问！【 看见很多人说自己的 BFS 超时了，坑就在这里哈哈哈
 * 做法：
 * 根据上述思路，本题怎么做就很简单了：
 * 首先把每个源点 0 入队，然后从各个 0 同时开始一圈一圈的向 1 扩散（每个 1 都是被离它最近的 0 扩散到的 ）.
 * 扩散的时候可以设置 int[][] dist 来记录距离（即扩散的层次）并同时标志是否访问过。对于本题是可以直接修改原数组 int[][] matrix 来记录距离和标志是否访问的.
 * 这里要注意先把 matrix 数组中 1 的位置设置成 -1 （设成 Integer.MAX_VALUE 啦，m * n啦，10000啦都行，只要是个无效的距离值来标志这个位置的 1 没有被访问过就行辣~）
 * 复杂度分析：
 * 每个点入队出队一次，所以时间复杂度：O(n * m)
 * 虽然我们是直接原地修改的原输入数组来存储结果，但最差的情况下即全都是 0 时，需要把 m * n 个 0 都入队，因此空间复杂度是 O(n * m)
 */
class Solution1 {
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, 1, -1, 0 };

    public int[][] updateMatrix(int[][] mat) {
        // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是 未被访问过的 1
        int rows = mat.length;
        int cols = mat[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0)
                    queue.offer(new int[] { i, j });
                else
                    mat[i][j] = -1;
            }
        }

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int curX = point[0], curY = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = curX + dx[i];
                int newY = curY + dy[i];
                // 如果四邻域的点是 -1，表示这个点是未被访问过的 1
                // 所以这个点到 0 的距离就可以更新成 matrix[x][y] + 1。
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && mat[newX][newY] == -1) {
                    mat[newX][newY] = mat[curX][curY] + 1;
                    queue.offer(new int[] { newX, newY });
                }
            }
        }
        return mat;
    }
}

// 2. 动态规划
class Solution {
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, 1, -1, 0 };

    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];

        // 构建 dp[i][j] 来表示该位置距离最近的 0 的距离。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = matrix[i][j] == 0 ? 0 : 10000;
            }
        }

        // 从左上角开始
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }

        // 从右下角开始
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i + 1 < m) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                }
                if (j + 1 < n) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                }
            }
        }
        return dp;
    }
}

/*
 * Queue 用 LinkedList 和 Array 实现对比
 * 时间：
 * LinkedList是链表结构，多了Node对象的创建、销毁、link/unlink等开销，并且链表内存不连续，无法利用cpu的局部缓存来加速；
 * ArrayDeque 相对 LinkedList
 * 来说省掉了Node对象的创建、销毁、link/unlink等昂贵的开销，并且列表的内存是连续的，顺序遍历的时候会利用cpu缓存来加速。
 * 看起来好像是 ArrayDeque
 * 会好很多哦，但是要注意的一点就是如果元素太多，那么列表会频繁扩容和复制，这里开销还是很大的，所以需要你提前预置合适的初始容量。
 * 空间：
 * LinkedList要创建Node对象，维护前继、后继指针等，比ArrayDeque差。
 * 在数据量小的情况下（比如做题 / 一般工程场景），两者的性能其实差不多，这里唯一要注意的一点就是 LinkedList 允许有
 * null，而ArrayDeque不可以，所以看你的需要来选择～
 * （工程中还需要注意 ArrayDeque 没有实现 List接口，别栽老坑里）
 * 在数据量大的情况下，ArrayDeque 会比 LinkedList
 * 快很多，如果你手头上有合适的场景的话可以尝试一下这个优化点哦！（当然你需要预估出合适的容量，来避免 ArrayDeque 频繁扩容和复制的开销）
 */