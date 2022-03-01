class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// 1. 双指针
class Solution1 {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = null;
        ListNode p = dummy;
        ListNode q = head;
        while (q != null) {
            ListNode tmp = q.next;
            q.next = p;
            p = q;
            q = tmp;
        }
        return p;
    }
}

// 2. 递归
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}