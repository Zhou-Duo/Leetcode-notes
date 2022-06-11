class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

// 快慢指针
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head, fast = head;
        do {
            if (fast == null || fast.next == null)
                return null;
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        ListNode ptr = head;
        while (ptr != slow) {
            slow = slow.next;
            ptr = ptr.next;
        }
        return ptr;
    }
}