
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

// 1. 递归
class Solution1 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 0)
            return reverseNList(head, right);
        else {
            head.next = reverseBetween(head.next, m - 1, n - 1);
            return head;
        }
    }

    private ListNode successor = null;

    public ListNode reverseNList(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = reverseNList(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }

    public ListNode reverseNList2(ListNode head, int n) {
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; i < n; i++) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        head.next = curr;
        return prev;
    }
}

// 2. 迭代
class Solution2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode curr = dummy;
        // 将 curr 移动至 要反转的 ListNode 前一个
        for (int i = 0; i < left - 1; i++) {
            curr = curr.next;
        }
        // 反转 right - left + 1 个 ListNode
        ListNode a = curr.next, b = a.next;
        for (int i = 0; i < right - left; i++) {
            ListNode tmp = b.next;
            b.next = a;
            a = b;
            b = tmp;
        }
        curr.next.next = b;
        curr.next = a;
        return dummy.next;
    }
}