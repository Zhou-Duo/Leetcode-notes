import java.util.LinkedList;

class Solution {
    public int maximumSum(int[] nums) {
        int[][] maxSums = new int[82][2];
        for (int num : nums) {
            int digitSum = digitSum(num);
            int smallerIndex = maxSums[digitSum][0] <= maxSums[digitSum][1] ? 0 : 1;
            if (num > maxSums[digitSum][smallerIndex]) {
                int tmp = maxSums[digitSum][smallerIndex];
                maxSums[digitSum][smallerIndex] = num;
            }
        }
        int maxSum = 0;
        for (int i = 0; i < maxSums.length; i++) {
            if (maxSums[i][0] != 0 && maxSums[i][1] != 0)
                maxSum = Math.max(maxSum, maxSums[i][0] + maxSums[i][1]);
        }
        if (maxSum == 0)
            return -1;
        return maxSum;
    }

    public int digitSum(int num) {
        int digitSum = 0;
        while (num > 0) {
            digitSum += num % 10;
            num /= 10;
        }
        return digitSum;
    }
}

class Solution2 {
    public int maximumSum(int[] nums) {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int sum = 0;
            for (char c : ("" + num).toCharArray()) {
                sum += c - '0';
            }
            map.computeIfAbsent(sum, t -> new PriorityQueue<>()).offer(-num);
        }
        int max = -1;
        for (PriorityQueue<Integer> queue : map.values()) {
            max = Math.max(max, queue.size() > 1 ? -queue.poll() - queue.poll() : -1);
        }
        return max;
    }
}