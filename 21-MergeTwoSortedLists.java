class ListNode {
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
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}

/*
 * 复杂度分析
 * 如何计算递归的时间复杂度和空间复杂度呢？
 * 时间复杂度：
 * 给出一个递归算法，其时间复杂度 O(T) 通常是递归调用的数量（记作 R）和计算的时间复杂度的乘积（表示为 O(s)）的乘积：O(T)=R∗O(s)
 * 时间复杂度：O(m+n)。
 * m, n为 l1 和 l2 的元素个数。递归函数每次去掉一个元素，直到两个链表都为空，因此需要调用 R = O(m+n)次。
 * 而在递归函数中我们只进行了next指针的赋值操作，复杂度为 O(1),故递归的总时间复杂度为 O(T) = R * O(1) = O(m+n)。
 * 空间复杂度：O(m+n)。**
 * 对于递归调用 self.mergeTwoLists()，当它遇到终止条件准备回溯时，已经递归调用了 m+n 次，使用了 m+n
 * 个栈帧，故最后的空间复杂度为 O(m+n)。
 */

// 2. 迭代
class Solution2 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;       
                l1 = l1.next;
            }
            else {
                cur.next = l2;       
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2:l1;
        return dummy.next;
    }
}
