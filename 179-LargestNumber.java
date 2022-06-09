import java.util.Arrays;
import java.util.PriorityQueue;

// 贪心 + 数字排序
class Solution1 {
    public String largestNumber(int[] nums) {
        Integer[] numsArr = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsArr[i] = nums[i];
        }
        Arrays.sort(numsArr, (x, y) -> {
            long x_bit = 10, y_bit = 10;
            while (x_bit <= x)
                x_bit *= 10;
            while (y_bit <= y)
                y_bit *= 10;
            return (int) -(y_bit * x + y - x_bit * y - x);
        });
        if (numsArr[0] == 0)
            return "0";
        StringBuilder res = new StringBuilder();
        for (int num : numsArr) {
            res.append(num);
        }
        return res.toString();
    }
}

// 贪心 + 字符串排序
class Solution2 {
    public String largestNumber(int[] nums) {
        PriorityQueue<String> heap = new PriorityQueue<>((x, y) -> (y + x).compareTo(x + y));
        for (int num : nums) {
            heap.offer(String.valueOf(num));
        }
        String res = "";
        while (!heap.isEmpty())
            res += heap.poll();
        if (res.charAt(0) == '0')
            return "0";
        return res;
    }
}

// 快速排序
class Solution3 {
    public String largestNumber(int[] nums) {
        String[] array = new String[nums.length];
        for (int i = 0; i < nums.length ; i++) {
            array[i] = String.valueOf(nums[i]);
        }
        quickSort(array, 0, nums.length - 1);
        if (array[0].equals("0")) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        for (String str : array) {
            ans.append(str);
        }
        return ans.toString();
    }

    private void quickSort(String[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        String pivot = nums[start];
        int partition = start;
        for (int i = start + 1; i <= end; i++) {
            if ((nums[i] + pivot).compareTo(pivot + nums[i]) > 0) {
                partition += 1;
                swap(nums, partition, i);
            }
        }
        swap(nums, partition, start);
        quickSort(nums, start, partition - 1);
        quickSort(nums, partition + 1, end);
    }

    private void swap(String[] nums, int l, int r) {
        String temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
