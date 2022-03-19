import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode-cn.com/problems/minimum-operations-to-halve-array-sum/
class Solution {
    public int halveArray(int[] nums) {
        // ** 注意使用数据类型
        long initialSum = 0;
        // lamda用法！PriorityQueue数据类型不熟悉！
        Queue<Double> doubleNums = new PriorityQueue<>((d1, d2) -> Double.compare(d2, d1));
        for (int num : nums) {
            doubleNums.add((double) num);
            initialSum += num;
        }
        double adjustSum = 0;
        int count = 0;
        while (adjustSum < 0.5 * initialSum) {
            double largest = doubleNums.poll();
            adjustSum += 0.5 * largest;
            doubleNums.add(0.5 * largest);
            count++;
        }
        return count;
    }
}