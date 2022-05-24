class Solution {
    public void wiggleSort(int[] nums) {
        for (int index = 0; index < nums.length - 1; index++) {
            if (index % 2 == 0 && nums[index] > nums[index+1])
                swap(nums, index, index+1);
            else if (index % 2 == 1 && nums[index] < nums[index+1])
                swap(nums, index, index+1);
        }
    }
    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}