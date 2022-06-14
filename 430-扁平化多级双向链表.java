import java.util.LinkedList;

// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

// 1. 递归
/* 
 * 时间复杂度：最坏情况下，每个节点会被访问 h 次（h 为递归深度，最坏情况下 h = n）。整体复杂度为 O(n^2)
 * 空间复杂度：最坏情况下所有节点都分布在 child 中，此时递归深度为 n。复杂度为 O(n)
 */
class Solution {
    public Node flatten(Node head) {
        // base case
        if (head == null || (head.next == null && head.child == null))
            return head;
        // recursion
        Node cur = head;
        while (cur != null) {
            if (cur.child == null) {
                cur = cur.next;
            }
            else {
                Node successor = cur.next;
                Node insertNode = flatten(cur.child);
                // **连接双向链表，取消child
                cur.next = insertNode;
                insertNode.prev = cur;
                cur.child = null; // 记得取消 child
                while (cur.next != null)
                    cur = cur.next;
                cur.next = successor;
                if (successor != null)
                    successor.prev = cur;
                cur = successor;
            }
        }
        return head;
    }
}