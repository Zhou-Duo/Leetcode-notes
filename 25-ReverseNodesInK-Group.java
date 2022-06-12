import java.util.Deque;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

// 1. 栈
class Solution1 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyListNode = new ListNode(-1);
        ListNode p = dummyListNode;
        Deque<ListNode> stack = new LinkedList<ListNode>();

        ListNode cur = head;
        while (true) {
            int count = 0;
            while (count < k && cur != null) {
                stack.push(cur);
                cur = cur.next;
                count += 1;
            }
            // 剩下的链表个数够不够 k 个时不用翻转
            if (count != k) {
                p.next = head;
                break;
            }
            while (!stack.isEmpty()) {
                ListNode newNode = stack.pop();
                p.next = newNode;
                p = p.next;
            }
            p.next = cur;
            head = cur;
        }
        return dummyListNode.next;
    }
}

// 2. 尾插法
class Solution2 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy, tail = dummy;
        while (true) {
            int count = k;
            while (count != 0 && tail != null) {
                count--;
                tail = tail.next;
            }
            if (tail == null)
                break;
            ListNode cur = pre.next, tmp = pre.next;
            while (cur != tail) {
                pre.next = cur.next;
                cur.next = tail.next;
                tail.next = cur;
                cur = pre.next;
            }
            pre = tmp;
            tail = tmp;
        }
        return dummy.next;
    }
}

// 3. 分段反转
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy, tail = dummy;
        while(tail.next != null) { //**注意此处是 tail.next 而非 tail
            for (int i = 0; i < k && tail != null; i++) {
                tail = tail.next;
            }
            if (tail == null)
                break;
            ListNode start = pre.next;
            ListNode successor = tail.next;
            tail.next = null;
            pre.next = reverseList(start);
            start.next = successor;
            pre = start;
            tail = start;
        }
        return dummy.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curNode = head;
        while (curNode != null) {
            ListNode nextNode = curNode.next;
            curNode.next = prev;
            prev = curNode;
            curNode = nextNode;
        }
        return prev;
    }
}