import java.util.ArrayList;
import java.util.List;

// 由于固定可以翻转一个0，可以统计每个0前后的1的数字，
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int curr = 0;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (num == 0) {
                list.add(curr);
                curr = 0;
            } else
                curr += 1;
        }
        list.add(curr);
        int res = list.get(0);
        for (int i = 0; i < list.size() - 1; i++) {
            res = Math.max(res, list.get(i) + list.get(i + 1));
        }
        return list.size() > 1 ? res + 1 : res;
    }
}

/*
 * 边界条件：
 * 1. 第一个元素为0，last为0，cur是后面的1的个数
 * 2. 最后一个元素为0，cur为0，last是前一段1的个数
 * 3. 最后一个元素为1，cur就是0后面的1的个数，cur+last+1就是最后一个0产生的子数组元素个数
 * 4. 不存在0时，val = cur，此时last应设置为-1
 * 5. 存在0时，last肯定会被cur重置，不会影响最后的结果
 */
class Solution2 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int last = -1;
        int cur = 0;
        int val = 0;
        for (int num : nums) {
            if (num == 1) {
                cur++;
            } else {
                last = cur;
                cur = 0;
            }
            // 不能放在num==0时计算，因为最后一个元素可能是0也可能是1
            val = Math.max(val, last + cur + 1);
        }
        return val;
    }
}

/*
 * 状态定义：
 * * dp[i][0]: 以 nums[i] 结尾不使用翻转操作的最长1序列长度
 * * dp[i][1]: 以 nums[i] 结尾使用过翻转操作的最长1序列长度
 */
class Solution3 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                dp[i][0] = 0;
                dp[i][1] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] + 1;
            }
            res = Math.max(res, Math.max(dp[i][1], dp[i][0]));
        }
        return res;
    }
}

class Solution4 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int dp1 = 0, dp0 = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                dp1 += 1;
                dp0 += 1;
            } else {
                dp1 = dp0 + 1;
                dp0 = 0;
            }
            res = Math.max(res, Math.max(dp1, dp0));
        }
        return res;
    }
}

// 滑动窗口
class Solution5 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0, right = 0;
        // window 内 0 的个数
        int numZero = 0;
        while (right < nums.length) {
            numZero += nums[right] == 0 ? 1 : 0;
            // 只要右边界不越界，而且 0 的个数小于等于 1 个，那么就扩充右边界
            if (numZero > 1) {
                numZero -= nums[left] == 0 ? 1 : 0;
                left++;
            }
            right++;
        }
        return right - left;
    }
}