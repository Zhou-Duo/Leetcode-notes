class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val;}
    ListNode(int val, ListNode next) { this.val = val; this.next = next;}
}

// 1. 单指针
/* 
 * 对链表进行两次遍历。
 * 第一次遍历时，我们统计链表中的元素个数 N；
 * 第二次遍历时，我们遍历到第 N/2 个元素（链表的首节点为第 0 个元素）时，将该元素返回即可。
 */

/* 
 * 复杂度分析
 * 时间复杂度：O(N)，其中 N 是给定链表的结点数目。
 * 空间复杂度：O(1)，只需要常数空间存放变量和指针。
 */
class Solution1 {
    public ListNode middleNode(ListNode head) {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }

        int mid = count / 2;
        ListNode ans = head;
        for (int i = 0; i < mid; i++) {
            ans = ans.next;
        }
        return ans;
    }
}

// 2. 快慢指针法
/* 
 * 复杂度分析
 * 时间复杂度：O(N)，其中 N 是给定链表的结点数目。
 * 空间复杂度：O(1)，只需要常数空间存放 slow 和 fast 两个指针。 
 */
class Solution2 {
    public ListNode middleNode(ListNode head) {
        int stepCount = 0;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            if (stepCount % 2 == 0)
                slow = slow.next;
            stepCount++;
            fast = fast.next;
        }
        return slow;
    }
}

class Solution3 {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

/* 
 * 解决链表的问题常见技巧：
 * 1. 使用递归函数，避免复杂的更改指针变量指向操作，使得求解问题变得简单。
 * 2. 设置「虚拟头结点」，避免对链表第 1 个结点做单独讨论，这个思想在数组里我们见过，叫「哨兵」.
 * 3. 使用「快慢指针」；
 * 4. 为链表编写测试函数，进行调试（在下面的参考代码中有），主要是：
 * 4.1 从数组得到一个链表；
 * 4.2 根据当前结点打印当前结点以及后面的结点。
 * 这两个方法可以非常方便地帮助我们调试关于链表的程序。
 */