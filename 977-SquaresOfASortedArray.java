
// Method 1 : 直接排序
/* 
 * 复杂度分析
 * 时间复杂度：O(nlogn)，其中 n 是数组 nums 的长度。
 * 空间复杂度：O(logn)。除了存储答案的数组以外，我们需要 O(logn) 的栈空间进行排序。
 */
import java.util.Arrays;

class Solution1 {
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i] * nums[i];
        }
        Arrays.sort(ans);
        return ans;
    }
}

// Method 2 : 双指针
