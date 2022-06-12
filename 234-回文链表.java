import java.util.Deque;
import java.util.LinkedList;

import javax.swing.text.html.HTML;

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
class Solution {
    public boolean isPalindrome(ListNode head) {
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode curr = head;
        for (int i = 0; i < len / 2; i++) {
            stack.push(curr);
            curr = curr.next;
        }
        // ** len是奇数时需要跳过最中心node
        curr = (len % 2 == 1) ? curr.next : curr;
        while (curr != null) {
            if (curr.val != stack.pop().val)
                return false;
            curr = curr.next;
        }
        return true;
    }
}

// 2. 快慢指针
/*
 * 快指针走到链表末尾，慢指针正好走到一半并反转前半部分链表
 */
class Solution1 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode slow = head, fast = head;
        ListNode prev = slow, prepre = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
            // 反转链表
            prev.next = prepre;
            prepre = prev;
        }
        // 若为奇数个node，slow需要跳过中心node
        if (fast != null)
            slow = slow.next;
        while (prev != null && slow != null) {
            if (prev.val != slow.val)
                return false;
            else {
                prev = prev.next;
                slow = slow.next;
            }
        }
        return true;
    }
}

// 3. 递归
class Solution2 {
    private ListNode frontNode;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode == null)
            return true;
        // 利用递归将数据压入栈的性质，达到倒序遍历链表
        if (!recursivelyCheck(currentNode.next))
            return false;
        if (currentNode.val != frontNode.val)
            return false;
        frontNode = frontNode.next;
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        frontNode = head;
        return recursivelyCheck(head);
    }
}