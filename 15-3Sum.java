import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 双指针
class Solution {
    public List<List<Integer>> threeSum(int[] nums) { // 总时间复杂度：O(n^2)
        List<List<Integer>> ans = new ArrayList<>();
        // 排序
        Arrays.sort(nums); // O(NlogN)

        // 双指针
        for (int i = 0; i < nums.length; i++) { // O(n^2)

            if(nums[i] > 0) // 第一个数大于 0，后面的数都比它大，肯定不成立了
                return ans;

            if(i > 0 && nums[i] == nums[i-1]) // 去掉重复情况
                continue;

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // 此处需判断left，right变化后是否溢出边界
                    while (left < nums.length && nums[left] == nums[left - 1]) 
                        left++;
                    while (right > 0 && nums[right] == nums[right + 1])
                        right--;
                }
                else if (sum > 0)
                    right--;
                else left++;
            }             
        }
        return ans;
    }
}