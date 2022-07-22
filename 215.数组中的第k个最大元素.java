
// 1. 堆排序
class Solution1 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heapq = new PriorityQueue<>();
        for (int num : nums) {
            if (heapq.size() >= k) {
                if (!heapq.isEmpty() && num > heapq.peek()) {
                    heapq.poll();
                    heapq.add(num);
                }
            } else
                heapq.add(num);
        }
        return heapq.poll();
    }
}

