public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/* 
 * 时间复杂度：O(n)，其中 n 是链表的节点数。需要遍历链表中的每个节点，并更新指针。
 * 空间复杂度：O(1)。只需要维护有限的指针。
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while(even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}