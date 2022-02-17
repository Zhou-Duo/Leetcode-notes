import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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

// 1， 利用已有的上一层 next 指针
class Solution1 {
    public Node connect(Node root) {
        if (root == null)
            return root;
        // 把每一层视作一个链表， 利用当前层链表完成下一层链表结构
        Node cur = root;
        while (cur != null){
            Node dummy = new Node(0);
            Node pre = dummy;
            // 遍历当前层
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
            // 继续遍历下一层
            cur = dummy.next;
        }
        return root;
    }
}

// 2. 层级遍历 BFS
class Solution {
    public Node connect(Node root) {
        if (root == null)
            return root;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                // 若不是本层最后一个 node，则设置next
                if (i < size - 1) {
                    cur.next = queue.peek();
                }
                // 加入下一层 node
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
        }
        return root;
    }
}