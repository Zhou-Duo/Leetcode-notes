import java.util.Deque;
import java.util.LinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// 辅助栈 （此例展示了递归与栈的先入后出的相似性）
class Solution {
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new LinkedList<Integer>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        // 注意此处不能使用 i < stack.size(), stack的长度在pop过程中实时变化
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
}