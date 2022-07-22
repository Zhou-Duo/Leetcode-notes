class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int curr = 0, max = 0;
        for (int num : nums) {
            if (num == 1) {
                curr += 1;
                max = Math.max(curr, max);
            }
            else curr = 0;
        }
        return max;
    }
}