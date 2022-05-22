import java.util.List;

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

// Merge Sort递归，将 array 分为两半分别排序后归并
/*
 * 复杂度分析
 * 时间复杂度：O(nlogn)，其中 n 是链表的长度。
 * 空间复杂度：O(logn)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间。
 */
class Solution1 {
    public ListNode sortList(ListNode head) {
        // base case
        if (head == null || head.next == null)
            return head;
        // 寻找链表中点：使用 fast,slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点 (如果 fast, slow
        // 起点相同，则偶数个节点找到中心右边的节点)
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmpHead = slow.next;
        slow.next = null;
        // 分别对两个 half 链表进行排序
        ListNode left = sortList(head);
        ListNode right = sortList(tmpHead);
        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {
        // 建立辅助ListNode h 作为头部
        ListNode h = new ListNode(0);
        ListNode res = h;
        // 合并两个排好序的链表
        while (left != null && right != null) {
            if (left.val <= right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        // 当一个链表加入完成后，直接加入另一半链表所有剩余nodes
        h.next = left != null ? left : right;
        return res.next;
    }
}

// Merge sort 自底向上 （不递归）
class Solution2 {
    public ListNode sortList(ListNode head) {
        // base case
        if (head == null || head.next == null)
            return head;
        // 统计链表长度
        int length = 0;
        ListNode count = head;
        while (count != null) {
            length++;
            count = count.next;
        }
        // 建立 dummy head
        ListNode dummy = new ListNode(0, head);
        // 从 subLength = 1 开始获取子链
        for (int subLength = 1; subLength < length; subLength *= 2) {
            ListNode prev = dummy, cur = dummy.next;
            while (cur != null) {
                // 子链 1
                ListNode head_1 = cur;
                for (int i = 1; i < subLength && cur.next != null; i++) {
                    cur = cur.next;
                }
                // 子链 2
                ListNode head_2 = cur.next;
                cur.next = null;
                cur = head_2;
                for (int i = 1; i < subLength && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }
                // 除掉两个子链的剩余部分
                ListNode remainder = null;
                if (cur != null) {
                    remainder = cur.next;
                    cur.next = null;
                }
                // 合并前两条子链
                ListNode merged = merge(head_1, head_2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                cur = remainder;
            }
        }
        return dummy.next;
    }

    public ListNode merge(ListNode left, ListNode right) {
        // 建立辅助 ListNode h 作为头部
        ListNode h = new ListNode(0);
        ListNode res = h;
        // 合并两个排好序的链表
        while (left != null && right != null) {
            if (left.val <= right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        // 当一个链表加入完成后，直接加入另一半链表所有剩余nodes
        h.next = left != null ? left : right;
        ListNode Print = res.next;
        // while(Print != null && Print.next != null) {
        // System.out.print(Print.val+"->");
        // Print = Print.next;
        // }
        // System.out.println(Print.val);
        return res.next;
    }
}