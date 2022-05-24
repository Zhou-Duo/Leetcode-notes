// 要求时间复杂度为 O(n): 使用桶排序
class Solution {
    public void wiggleSort(int[] nums) {
        int[] count = new int[5001];
        for (int num : nums) {
            count[num]++;
        }
        int idx = 5000;
        // 注意此处：当题目要求较小数在前，要先放大的，以便使相同值的数字分隔开
        for (int i = 1; i < nums.length; i += 2) {
            while (count[idx] == 0)
                idx--;
            nums[i] = idx;
            count[idx]--;
        }
        for (int i = 0; i < nums.length; i += 2) {
            while (count[idx] == 0)
                idx--;
            nums[i] = idx;
            count[idx]--;
        }
    }
}