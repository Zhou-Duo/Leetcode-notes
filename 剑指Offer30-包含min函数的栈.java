import java.util.Deque;
import java.util.LinkedList;

/*
 * 复杂度分析：
 * 时间复杂度：O(1) ，push(), pop(), top(), min() 四个函数的时间复杂度均为常数级别。
 * 空间复杂度： 当共有 N 个待入栈元素时，辅助栈 BB 最差情况下存储 N 个元素，使用 O(N) 额外空间。
 */
class MinStack {
    /* 
     * 普通栈的 push() 和 pop() 函数的复杂度为 O(1)；
     * 而获取栈最小值 min() 函数需要遍历整个栈，复杂度为 O(N)
     */
    Deque<Integer> stack1, stack2;
    public MinStack() {
        stack1 =  new LinkedList<Integer>();
        stack2 =  new LinkedList<Integer>();
    }
    
    public void push(int x) {
        stack1.push(x);
        if (stack2.isEmpty() || x <= stack2.peek())
            stack2.push(x);
    }
    
    public void pop() {
        int popNum = stack1.pop();
        if (popNum == stack2.peek())
            stack2.pop();
    }
    
    public int top() {
        return stack1.peek();
    }
    
    public int min() {
        return stack2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */