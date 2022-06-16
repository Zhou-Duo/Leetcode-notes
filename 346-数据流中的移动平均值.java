import java.util.LinkedList;
import java.util.Queue;

class MovingAverage {
    private Queue<Integer> queue;
    private int size;
    private int windowSum;

    public MovingAverage(int size) {
        queue = new LinkedList<Integer>();
        this.size = size;
        windowSum = 0;
    }

    public double next(int val) {
        if (queue.size() >= this.size)
            windowSum -= queue.poll();
        windowSum += val;
        queue.offer(val);
        return windowSum * 1.0 / Math.min(queue.size(), this.size);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */