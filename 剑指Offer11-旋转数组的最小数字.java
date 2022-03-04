class Solution {
    public int minArray(int[] numbers) {
        int n = numbers.length;
        int left = 0, right = n - 1; // 左右双闭
        while (left <= right) { // 终止条件为 left = right + 1
            int mid = left + (right - left) / 2;
            int num = numbers[mid];
            if (num < numbers[right]) {
                // 6 7 1 (2) 2 3 4
                // 左半边无序，右半边有序
                right = mid;
            }
            else if (num > numbers[right]) {
                // 3 4 (5) 6 1 2
                // 左半边有序，右半边无序
                left = mid + 1;
            }
            else {
                // num == numbers[right]
                // 0 1 (2) 2 2
                // 2 2 (2) 0 1 2
                // 无论 numbers[right] 是不是最小值，都有一个它的「替代品」numbers[mid]，因此我们可以忽略二分查找区间的右端点
                right = right - 1;
            }
        }
        return numbers[left];
    }
}