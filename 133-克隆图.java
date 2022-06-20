import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

/*
 * 1. DFS
 * 时间复杂度：O(N)，其中 N 表示节点数量。深度优先搜索遍历图的过程中每个节点只会被访问一次。
 * 空间复杂度：O(N)。存储克隆节点和原节点的哈希表需要 O(N) 的空间，递归调用栈需要 O(H) 的空间.
 * 其中 H 是图的深度，经过放缩可以得到 O(H) = O(N)，因此总体空间复杂度为 O(N)。
 */
class Solution1 {
    private HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null)
            return node;
        if (visited.containsKey(node))
            return visited.get(node);
        Node newNode = new Node(node.val, new ArrayList<Node>());
        visited.put(node, newNode);
        for (Node neighbor : node.neighbors)
            newNode.neighbors.add(cloneGraph(neighbor));
        return newNode;
    }
}

// 2. BFS
class Solution {
    private HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null)
            return node;
        // 将题目给定的节点添加到队列
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, new Node(node.val, new ArrayList<Node>()));

        // BFS
        while (!queue.isEmpty()) {
            // 取出队列的头节点
            Node cur = queue.poll();
            // 遍历该节点的邻居
            for (Node neighbor : cur.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<Node>()));
                    // 将邻居节点加入队列中
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                visited.get(cur).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }
}
