import java.util.Deque;
import java.util.LinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val;}
    ListNode(int val, ListNode next) { this.val = val; this.next = next;}
}

/* 
 * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），
 * 它的 next 指针指向链表的头节点。这样一来，我们就不需要对头节点进行特殊的判断了。
 * 例如，在本题中，如果我们要删除节点 y，我们需要知道节点 y 的前驱节点 x，并将 x 的指针指向 y 的后继节点。
 * 但由于头节点不存在前驱节点，因此我们需要在删除头节点时进行特殊判断。
 * 但如果我们添加了哑节点，那么头节点的前驱节点就是哑节点本身，此时我们就只需要考虑通用的情况即可。
 */

// 1. 计算链表长度
/* 
 * 复杂度分析
 * 时间复杂度：O(L)，其中 L 是链表的长度。
 * 空间复杂度：O(1)。
 */
class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); //**注意！dummy head
        int len = getLength(head);
        ListNode current = dummy;
        for (int i = 0; i < len - n; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        return dummy.next;
    }

    public int getLength(ListNode head) {
        int len = 0;
        ListNode current = head;
        while (current != null) {
            len++;
            current = current.next;   
        }
        return len;
    }
}

// 2. 双指针
/* 
 * 双指针
 * 1. 不同方向遍历
 * 2. 两个指针之前速度差值恒定（用于找 mid， quartile 等）
 * 3. 两个指针距离差值恒定（用于找 nth）
 */

/* 
 * 复杂度分析
 * 时间复杂度：O(L)，其中 L 是链表的长度。
 * 空间复杂度：O(1)。
 */
class Solution2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); //**注意！dummy head
        ListNode p = dummy;
        ListNode q = dummy;
        for (int i = 0; i < n + 1; i++) {
            q = q.next;
        }
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;
        return dummy.next;
    }
}

// 3. 栈
/* 
 * 我们也可以在遍历链表的同时将所有节点依次入栈。
 * 根据栈「先进后出」的原则，我们弹出栈的第 n 个节点就是需要删除的节点，
 * 并且目前栈顶的节点就是待删除节点的前驱节点。这样一来，删除操作就变得十分方便了。
 * ***常用语 last nth
 */
/* 
 * 复杂度分析
 * 时间复杂度：O(L)，其中 L 是链表的长度。
 * 空间复杂度：O(L)，其中 L 是链表的长度。主要为栈的开销。
 */
class Solution3 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); 
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}