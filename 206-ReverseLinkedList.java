
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// 1. Recursion **利用原有的链表关系！
/* 
 * 复杂度分析
 * 时间复杂度：O(n)，其中 n 是链表的长度。需要对链表的每个节点进行反转操作。
 * 空间复杂度：O(n)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间，最多为 n 层。
 */
class Solution1 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

// 2. 迭代
/* 
 * 复杂度分析
 * 时间复杂度：O(n)，其中 n 是链表的长度。需要遍历链表一次。
 * 空间复杂度：O(1)。
 */
class Solution2 {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode currNode = head;
        while (currNode != null) {
            ListNode nextNode = currNode.next;
            currNode.next = prev;
            prev = currNode;
            currNode = nextNode;
        }
        return prev;
    }
}