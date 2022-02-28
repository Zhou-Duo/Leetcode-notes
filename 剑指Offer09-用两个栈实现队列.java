import java.util.LinkedList;
import java.util.Deque;

/* 
复杂度分析：
时间复杂度：对于插入和删除操作，时间复杂度均为 O(1)。对于删除操作，虽然看起来是 O(n) 的时间复杂度，但是仔细考虑下每个元素只会「至多被插入和弹出 stack2 一次」，因此均摊下来每个元素被删除的时间复杂度仍为 O(1)。
空间复杂度：O(n)。需要使用两个栈存储已有的元素。
 */
class CQueue {
    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public CQueue() {
        // Stack 先进后出， Queue 先进先出
        // 维护两个栈 stack1 和 stack2，其中 stack1 支持插入操作，stack2 支持删除操作
        stack1 = new LinkedList<Integer>();
        stack2 = new LinkedList<Integer>();
    }
    
    public void appendTail(int value) {
        stack1.push(value);
    }
    
    public int deleteHead() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.poll());
        }
        if (stack2.isEmpty())
            return -1;
        else {
            return stack2.poll();
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */