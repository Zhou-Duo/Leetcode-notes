import java.util.HashMap;

// Definition for a Node.
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
        HashMap<Node,Node> map = new HashMap<>();
        Node curr = head;
        // 复制链表各个节点
        while (curr != null) {
            Node currCopy = new Node(curr.val);
            map.put(curr, currCopy);
            curr = curr.next;
        }
        curr = head;
        // 构建新链表的指向关系
        while (curr != null) {
            Node currCopy = map.get(curr);
            currCopy.next = map.get(curr.next);
            currCopy.random = curr.random == null ? null: map.get(curr.random);
        }
        return map.get(head);
    }
}

// 回溯 + 哈希表
class Solution2 {
    HashMap<Node,Node> map = new HashMap<>();
    
    public Node copyRandomList(Node head) {
        if (head == null)
            return head;
        if (!map.containsKey(head)) {
            Node currCopy = new Node(head.val);
            map.put(head, currCopy);
            currCopy.next = copyRandomList(head.next);
            currCopy.random = copyRandomList(head.random);
        }
        return map.get(head);
    }
}

// 节点拆分
/* 
 * 注意到方法一需要使用哈希表记录每一个节点对应新节点的创建情况，而我们可以使用一个小技巧来省去哈希表的空间。
 * 我们首先将该链表中每一个节点拆分为两个相连的节点，例如对于链表 A→B→C，我们可以将其拆分为 A→A‘→B→B′→C→C’。
 * 对于任意一个原节点 S，其拷贝节点 S'即为其后继节点。
 * 这样，我们可以直接找到每一个拷贝节点 S'的随机指针应当指向的节点，即为其原节点 S 的随机指针指向的节点 T 的后继节点 T'。需要注意原节点的随机指针可能为空，我们需要特别判断这种情况。
 * 当我们完成了拷贝节点的随机指针的赋值，我们只需要将这个链表按照原节点与拷贝节点的种类进行拆分即可，只需要遍历一次。同样需要注意最后一个拷贝节点的后继节点为空，我们需要特别判断这种情况。
 */
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        // 插入复制的后继节点
        for (Node cur = head; cur != null; cur = cur.next.next) {
            Node curCopy = new Node(cur.val);
            curCopy.next = cur.next;
            cur.next = curCopy;
        }

        // 重新设置指针 random
        for (Node cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null)
                cur.next.random = cur.random.next;   
        }

        // 重新设置指针 next
        Node newHead = head.next;
        for (Node cur = head; cur != null; cur = cur.next) {
            Node curCopy = cur.next;
            cur.next = cur.next.next; // cur.next 不为null
            curCopy.next = cur.next != null ? cur.next.next : null;      
        }
        return newHead;
    }
}