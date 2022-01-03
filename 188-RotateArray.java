
// 1. 使用额外的数组
/* 
* 复杂度分析
* 时间复杂度： O(n)，其中 nn 为数组的长度。
* 空间复杂度： O(n)。 
*/

class Solution1 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }
}

// 2. 环状替换
/* 
* 复杂度分析
* 时间复杂度：O(n)，其中 n 为数组的长度。每个元素只会被遍历一次。
* 空间复杂度：O(1)。我们只需常数空间存放若干变量。 
*/
class Solution2 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        int startPos = 0;
        while (count < n) {
            int currentPos = startPos;
            int prev = nums[currentPos];
            do {
                int nextPos = (currentPos + k) % n;
                int temp = nums[nextPos];
                nums[nextPos] = prev;
                prev = temp;
                currentPos = nextPos;
                count ++;
            }
            while (currentPos != startPos);
            ++startPos;
        }
    }
}

// 3. 翻转数组
/* 
* 复杂度分析
* 时间复杂度：O(n)，其中 n 为数组的长度。每个元素被翻转两次，一共 n 个元素，因此总时间复杂度为 O(2n)=O(n)。
* 空间复杂度：O(1)。 
*/
class Solution3 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start ++;
            end --;
        }
    }
}
