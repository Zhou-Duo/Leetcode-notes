import java.util.LinkedList;
import java.util.Queue;

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

// 1. Mine: 递归之缝衣服 （左右两棵树分别连完成后，再逐层连接中间的跨树 node）
/* 
 * 复杂度分析
 * 时间复杂度：O(N)，每个节点只访问一次。
 * 空间复杂度：O(1)，不需要存储额外的节点。
 */
class Solution1 {
    public Node connect(Node root) {
        if (root == null || root.left == null) {
            return root;
        }
        Node currLeft = root.left;
        Node currRight = root.right;
        currLeft.next = currRight;
        currRight.next = null;
        connect(currLeft);
        connect(currRight);
        while (currLeft.right != null) {
            currLeft = currLeft.right;
            currRight = currRight.left;
            currLeft.next = currRight;
        }
        return root;
    }
}

// 2. 递归之分层连接，从上往下 (牛！)
/* 
 * 复杂度分析
 * 时间复杂度：O(N)，每个节点只访问一次。
 * 空间复杂度：O(1)，不需要存储额外的节点。
 */
class Solution2 {
    public Node connect(Node root) {
        if (root == null || root.left == null) {
            return root;
        }
        root.left.next = root.right;
        if (root.next != null)
            root.right.next = root.next.left;
        connect(root.left);
        connect(root.right);
        return root;
    }
}

// 3. 类似 BFS 的层次遍历
/* 
 * 思路阻塞点 ： 都放在 queue 里，如何阻止跨层连接？ ans: 每层遍历前记录 size，超出 size 不遍历
 */
/* 
 * 复杂度分析
 * 时间复杂度：O(N)。每个节点会被访问一次且只会被访问一次，即从队列中弹出，并建立 next 指针。
 * 空间复杂度：O(N)。这是一棵完美二叉树，它的最后一个层级包含 N/2 个节点。广度优先遍历的复杂度取决于一个层级上的最大元素数量。这种情况下空间复杂度为 O(N)。
 */
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<Node>(); 
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(); // 此时 queue 中一定有且只有一层的所以 nodes
            // 遍历这一层的 node （用 size 保证没有跨层连接）
            for (int i = 0; i < size; i++) {
                Node currNode = queue.poll();
                // 若 currNode 不是本层最后一个 Node
                if (i < size - 1) {
                    currNode.next = queue.peek();
                }
                // 加入下一层 node
                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                } 
            }
        }
        return root;
    }
}