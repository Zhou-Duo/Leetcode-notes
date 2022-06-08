// https://leetcode.cn/problems/replace-elements-in-an-array/
class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        int[] delta = new int[1000001];
        for (int i = 0; i < nums.length; i++) {
            delta[nums[i]] = i;
        }
        for (int[] operation : operations) {
            delta[operation[1]] = delta[operation[0]];
            nums[delta[operation[1]]] = operation[1];
        }
        return nums;
    }
}
