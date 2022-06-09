public void quickSort(int[] nums, int lo, int hi) {
    if (hi <= lo)
        return;
    int pivot = nums[hi];
    int less = lo, great = hi;
    int i = lo;
    while (less <= great) {
        // 增序排列
        if (nums[i] < pivot) {
            swap(nums, i, less);
            less++;
            i++;
        }
        else if (nums[i] > pivot) {
            swap(nums, i, great);
            great--;
        }
        else {
            i++;
        }
    }
    quickSort(nums, lo, less - 1);
    quickSort(nums, great + 1, hi);
}