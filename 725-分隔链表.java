
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

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        // 遍历一次获得链表长度
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length += 1;
            curr = curr.next;
        }
        // avg # of nodes in each list
        int cntNode = length / k;
        // # of lists that has 1 more nodes
        int cntLongerLists = length % k;
        // split LinkedList
        curr = head;
        ListNode[] res = new ListNode[k];
        for (int i = 0; i < k && curr != null; i++) {
            res[i] = curr;
            int partSize = cntNode + (i < cntLongerLists ? 1 : 0);
            // node 数量经过计算，不需要在此判断 curr != null
            for (int j = 0; j < partSize - 1; j++) {
                curr = curr.next;
            }
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        return res;
    }
}