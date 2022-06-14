public class AllSorts {
    // 1.1 冒泡排序 + 提前结束优化
    public void bubbleSort(int[] nums) {
        if (nums.length < 2)
            return nums;
        // n - 1轮次执行，当前 n - 1 个元素排好后，最后一个元素无需执行，故 i < nums.length - 1
        for (int i = 0; i < nums.length - 1; i++) {
            // 本轮执行是否有交换的标志，若无则false，若有则true
            boolean swapped = false;
            // 每轮循环，通过依次向右比较两个数，将本轮循环中最大的数放到最右
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j - 1, j);
                    swapped = true;
                }
            }
            // 若无交换，表示当前数组已完全排序，退出大循环
            if (!swapped)
                break;
        }
    }

    // 1.2 冒泡排序 + 提前结束优化 + 冒泡界优化
    public void bubbleSort2(int[] nums) {
        if (arr.length < 2)
            return arr;
        boolean swapped = true;
        int lastSwappedIdx = arr.length - 1;
        int swappedIdx = -1;
        // lastSwappedIdx表示前一轮交换的最终位置，即下标为lastSwappedIdx是未排序部分中的最后一个数的下标，
        // 因此for中的界是 i < lastSwappedIdx而不需要写成i <= lastSwappedIdx
        while (swapped) { // 当swapped = false时，排序完成
            // 本轮执行是否有交换的标志，若无则true，若有则false
            swapped = false;
            for (int i = 0; i < lastSwappedIdx; i++) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                    swapped = true;
                    swappedIdx = i;
                }
            }
            lastSwappedIdx = swappedIdx;
        }
    }

    // 2.1 单元选择排序
    public void selectSort(int[] nums) {
        if (nums.length < 2)
            return nums;
        // n - 1 轮次执行,当前 n - 1 个元素排好后，最后一个元素无需执行，故 i < arr.length - 1
        for (int i = 0; i < nums.length - 1; i++) {
            int minIdx = i;
            // 找到本轮执行中最小的元素，将最小值下标赋值给min
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIdx])
                    minIdx = j;
            }
            if (minIdx != i)
                swap(nums, i, minIdx);
        }
    }

    // 2.2 双元选择排序
    public void selectSort2(int[] nums) {
        if (nums.length < 2)
            return nums;
        int n = arr.length;
        // 每轮确定两个数字，因此界也会动态变化
        for (int i = 0; i < n - i; i++) {
            int minIdx = i, maxIdx = i;
            for (int j = i + 1; j < n - i; j++) {
                if (nums[j] < nums[minIdx])
                    minIdx = j;
                else if (nums[j] > nums[maxIdx])
                    maxIdx = j;
            }
            // 若本轮最大值等于最小值，说明未排序部分所有元素相等，无需再排序
            if (minIdx == maxIdx)
                break;
            // 若本轮第一个数字不是最小值，则交换位置（将最小值与本轮第一个数字交换位置）
            if (minIdx != i)
                swap(arr, i, minIdx);
            // 在交换i和minIdx时，有可能出现i即maxIdx的情况，此时需要修改maxIdx为minIdx
            if (maxIdx == i)
                maxIdx = minIdx;
            // 若本轮最后一个数字不是最大值，则交换位置（将最大值与本轮最后一个数字交换位置）
            if (maxIdx != n - 1 - i)
                swap(arr, n - 1 - i, maxIdx);
        }
    }

    // 3.1 简单插入排序
    public void insertionSort(int[] nums) {
        if (nums.length < 2)
            return nums;
        for (int i = 1; i < nums.length; i++) {
            int target = nums[i], j = i - 1;
            for (; j >= 0; j--) {
                if (target < nums[j])
                    nums[j + 1] = nums[j];
                else
                    break;
            }
            // j变动表示发生了移动，此时的插入对象数字 ≥ j位置的数字，故插入位置为j + 1
            if (j != i - 1)
                nums[j + 1] = target;
        }
    }

    // 3.2 折半插入排序
    public void insertionSort2(int[] nums) {
        if (nums.length < 2)
            return nums;
        for (int i = 1; i < nums.length; i++) {
            // 若当前插入对象大于等于前一个对象，无需插入
            if (nums[i - 1] <= nums[i])
                continue;
            int target = nums[i];
            // 折半查找
            int low = 0, high = i - 1;
            while (low <= high) {
                int center = low + (high - low) / 2;
                if (nums[center] < target)
                    low = center + 1;
                else
                    high = center - 1;
            }
            for (int j = i; j > low; j--) { // 移动
                arr[j] = arr[j - 1];
            }
            arr[low] = target; // 插入
        }
    }

    // 4. 希尔排序：采用Shell增量 N / 2^k
    public void shellSort(int[] nums) {
        if (nums.length < 2)
            return nums;
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) { // gap 初始为 n/2，缩小gap直到 1
            for (int start = 0; start < gap; start++) { // 步长增量是gap，当前增量下需要对gap组序列进行简单插入排序
                for (int i = start + gap; i < n; i += gap) { // 此for及下一个for对当前增量序列执行简单插入排序
                    int target = nums[i], j = i - gap;
                    for (; j >= 0; j -= gap) {
                        if (target < arr[j]) {
                            nums[j + gap] = arr[j];
                        } else
                            break;
                    }
                    if (j != i - gap)
                        arr[j + gap] = target;
                }
            }
        }
    }

    // 

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
