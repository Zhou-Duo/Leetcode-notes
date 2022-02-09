
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// 1. 双指针 - 我写的
class Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) 
            return head;
        // sentinel
        ListNode sentinel = new ListNode(0, head);

        // the last node before the sublist of duplicates
        ListNode prev = sentinel;

        // the first node after the sublist of duplicates
        ListNode last = head.next;

        while (last != null) {
            if (prev.next.val == last.val) 
                last = last.next;
            else {
                if (prev.next.next != last) {
                    prev.next = last;
                    last = last.next;
                }
                else {
                    prev = prev.next;
                    last = last.next;
                }
            }
        }
        if (prev.next.next != last)
            prev.next = last;
        return sentinel.next;   
    }
}

// 2. 递归
/* 
 * 时间复杂度：O(N)，每个节点访问了一次。
 * 空间复杂度：O(N)，递归调用的时候会用到了系统的栈。
 */
class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) 
            return head;
        
        if (head.val != head.next.val)
            head.next = deleteDuplicates(head.next);
        else {
            ListNode move = head.next;
            while (move != null && head.val == move.val)
                move = move.next;
            return deleteDuplicates(move);
        }
        return head;
    }
}