/* 
 * 过程：
 * 首先要注意题目条件，在题目描述中出现了 nums[-1] = nums[n] = -∞，这就代表着 只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值
 * 根据上述结论，我们就可以使用二分查找找到峰值
 * 查找时，左指针 l，右指针 r，以其保持左右顺序为循环条件
 * 根据左右指针计算中间位置 m，并比较 m 与 m+1 的值，如果 m 较大，则左侧存在峰值，r = m，如果 m + 1 较大，则右侧存在峰值，l = m + 1
 * 时间复杂度：O(logN)
 */
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1; //左右双闭
        while (left < right) { // terminate when left = right + 1
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) 
                right = mid;
            else left = mid + 1;
        }
        return left;
    }
}