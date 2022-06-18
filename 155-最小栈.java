import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 1. 用额外栈保存目前为止最小值
class MinStack1 {

    Deque<Integer> minTillCur;
    Deque<Integer> minStack;

    public MinStack() {
        minTillCur = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
    }

    public void push(int val) {
        minStack.push(val);
        minTillCur.push(minTillCur.isEmpty() ? val : Math.min(val, minTillCur.peek()));
    }

    public void pop() {
        minStack.pop();
        minTillCur.pop();
    }

    public int top() {
        return minStack.peek();
    }

    public int getMin() {
        return minTillCur.peek();
    }
}

// 2. 不使用额外空间，保存差值
/*
 * 入栈 3，存入 3 - 3 = 0
 * | | min = 3
 * | |
 * |_0_|
 * stack
 * 
 * 入栈 5，存入 5 - 3 = 2
 * | | min = 3
 * | 2 |
 * |_0_|
 * stack
 * 
 * 入栈 2，因为出现了更小的数，所以我们会存入一个负数，这里很关键
 * 也就是存入 2 - 3 = -1, 并且更新 min = 2
 * 对于之前的 min 值 3, 我们只需要用更新后的 min - 栈顶元素 -1 就可以得到
 * | -1| min = 2
 * | 5 |
 * |_3_|
 * stack
 * 
 * 入栈 6，存入 6 - 2 = 4
 * | 4 | min = 2
 * | -1|
 * | 5 |
 * |_3_|
 * stack
 * 
 * 出栈，返回的值就是栈顶元素 4 加上 min，就是 6
 * | | min = 2
 * | -1|
 * | 5 |
 * |_3_|
 * stack
 * 
 * 出栈，此时栈顶元素是负数，说明之前对 min 值进行了更新。
 * 入栈元素 - min = 栈顶元素，入栈元素其实就是当前的 min 值 2
 * 所以更新前的 min 就等于入栈元素 2 - 栈顶元素(-1) = 3
 * | | min = 3
 * | 5 |
 * |_3_|
 * stack
 */
// 保存差值，可能溢出，因此采用 long
class MinStack2 {
    Deque<Long> minStack;
    long min_value;

    public MinStack() {
        minStack = new LinkedList<Long>();
    }

    public void push(int val) {
        if (minStack.isEmpty()) {
            min_value = val;
            minStack.push(val - min_value);
        } else {
            minStack.push(val - min_value);
            if (val < min_value)
                min_value = val;
        }
    }

    public void pop() {
        if (minStack.isEmpty())
            return;
        long pop = minStack.pop();
        if (pop < 0)
            min_value -= pop;
    }

    public int top() {
        long top = minStack.peek();
        if (top < 0)
            return (int) min_value;
        else
            return (int) (top + min_value);
    }

    public int getMin() {
        return (int) min_value;
    }
}

// 3. 将最小值也保存在栈里
class MinStack {
    int min;
    Stack<Integer> stack;

    public MinStack() {
        stack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
        stack.push(min);
    }
    
    public void push(int val) {
        if (stack.isEmpty()) 
            min = val;
        // 当前值更小时，将前一个最小值保存在栈里
        else if (val <= min) {
            // 注意这里是 <= ,否则无法处理相同元素重复的情况
            stack.push(min);
            min = val;
        }
        stack.push(val);
    }
    
    public void pop() {
        //如果弹出的值是最小值，那么将下一个元素更新为最小值
        if (stack.pop() == min)
            min = stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}