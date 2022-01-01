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
/* 
* 方法一没有利用「数组 nums 已经按照升序排序」这个条件。
* 显然，如果数组 nums 中的所有数都是非负数，那么将每个数平方后，数组仍然保持升序；如果数组 nums 中的所有数都是负数，那么将每个数平方后，数组会保持降序。
* 
* 这样一来，如果我们能够找到数组 nums 中负数与非负数的分界线，那么就可以用类似「归并排序」的方法了。
* 具体地，我们设 neg 为数组 nums 中负数与非负数的分界线，也就是说，nums[0] 到 nums[neg] 均为负数，而 nums[neg+1] 到 nums[n−1] 均为非负数。
* 当我们将数组 nums 中的数平方后，那么 nums[0] 到 nums[neg] 单调递减，nums[neg+1] 到 nums[n−1] 单调递增。
* 
* 由于我们得到了两个已经有序的子数组，因此就可以使用归并的方法进行排序了。
* 具体地，使用两个指针分别指向位置 neg 和 neg+1，每次比较两个指针对应的数，选择较小的那个放入答案并移动指针。当某一指针移至边界时，将另一指针还未遍历到的数依次放入答案。
*/

class Solution2 {
    public int[] sortedSquares(int[] nums) {
        // 找到 neg
        int n = nums.length;
        int neg = -1;
        for (int i = 0; i < n; i++){
            if (nums[i] < 0)
                neg = i;
            else
                break;
        }

        // Merge Sort
        int[] ans = new int[n];
        int index = 0, i = neg, j = neg + 1;
        while (i >= 0 || j < n) {
            if (i == -1){
                ans[index] = nums[j] * nums[j];
                ++j;
            }
            else if (j == n) {
                ans[index] = nums[i] * nums[i];
                --i;
            }
            else if (nums[i] * nums[i] < nums[j] * nums[j]) {
                ans[index] = nums[i] * nums[i];
                --i;
            }
            else {
                ans[index] = nums[j] * nums[j];
                ++j;
            }
            ++index;
        }
        return ans;
    }
}

/*
* 时间复杂度：O(n)，其中 nn 是数组 nums 的长度。
* 空间复杂度：O(1)。除了存储答案的数组以外，我们只需要维护常量空间。
*/

// Method 3 : 双指针
/*
* 同样地，我们可以使用两个指针分别指向位置 00 和 n-1n−1，每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针。
* 这种方法无需处理某一指针移动至边界的情况
*/
class Solution3 {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            }
            else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;    
        }
        return ans;
    }
}
