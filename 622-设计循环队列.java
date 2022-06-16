// 1. 双指针实现
/* 
 * 时间复杂度：O(1)。该数据结构中，所有方法都具有恒定的时间复杂度。
 * 空间复杂度：O(N)，其中 N 是队列的预分配容量。循环队列的整个生命周期中，都持有该预分配的空间。
 */
class MyCircularQueue {

    // 用 数组 实现
    private int[] queue;
    private int start;
    private int end;
    private int size;
    private int capacity;

    public MyCircularQueue(int k) {
        queue = new int[k];
        start = 0;
        end = -1;
        size = 0;
        capacity = k;
    }

    public boolean enQueue(int value) {
        if (size >= capacity)
            return false;
        end = (end == capacity - 1) ? 0 : end + 1;
        queue[end] = value;
        size += 1;
        return true;
    }

    public boolean deQueue() {
        if (size <= 0)
            return false;
        start = (start == capacity - 1) ? 0 : start + 1;
        size -= 1;
        return true;
    }

    public int Front() {
        if (isEmpty())
            return -1;
        return queue[start];
    }

    public int Rear() {
        if (isEmpty())
            return -1;
        return queue[end];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == capacity);
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */