import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

// 哈希表
class Solution1 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> old2new = new HashMap<Node, Node>();
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node cur = dummyNode;
        while (cur != null) {
            old2new.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = dummyNode;
        while (cur != null) {
            old2new.get(cur).next = old2new.get(cur.next);
            old2new.get(cur).random = old2new.get(cur.random);
            cur = cur.next;
        }
        return old2new.get(dummyNode).next;
    }
}

// 回溯+哈希
class Solution2 {
    private Map<Node, Node> old2new = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        if (!old2new.containsKey(head)) {
            Node copyHead = new Node(head.val);
            old2new.put(head, copyHead);
            copyHead.next = copyRandomList(head.next);
            copyHead.random = copyRandomList(head.random);
        }
        return old2new.get(head);
    }
}

// 迭代 + 拆分节点
class Solution3 {
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Node cur = head;
        while (cur != null) {
            insertCopyNode(cur);
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {
            cur.next.random = (cur.random == null) ? null: cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        Node newHead = head.next;
        // 需要恢复原有链表
        while (cur != null) {
            Node tmp = cur.next;
            cur.next = cur.next.next;
            tmp.next = (tmp.next != null) ? tmp.next.next : null;
            cur = cur.next;
        }
        return newHead;
    }

    public void insertCopyNode(Node a) {
        Node copyA = new Node(a.val);
        copyA.next = a.next;
        a.next = copyA;
    }
}