import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// https://leetcode-cn.com/problems/minimum-deletions-to-make-array-beautiful/
// 模拟 + 贪心 （减去最左不满足情况的数位一定更优）
class Solution1 {
    public int minDeletion(int[] nums) {
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums)
            numsList.add(num);
        int count = 0;
        int startIndex = isBeautiful(numsList, 0);
        while (startIndex != -1) {
            count++;
            numsList = deleteFirstViolate(numsList, startIndex);
            startIndex = isBeautiful(numsList, startIndex);
        }
        return count;
    }

    public int isBeautiful(List<Integer> nums, int startIndex) {
        if (nums.size() % 2 != 0)
            return startIndex;
        for (int i = startIndex; i < nums.size() - 1; i++) {
            if (i % 2 == 0 && nums.get(i).equals(nums.get(i + 1)))
                return i;
        }
        return -1;
    }

    public List<Integer> deleteFirstViolate(List<Integer> nums, int startIndex) {
        for (int i = startIndex; i < nums.size() - 1; i++) {
            if (i % 2 == 0 && nums.get(i).equals(nums.get(i + 1))) {
                nums.remove(i);
                return nums;
            }
        }
        nums.remove(nums.size() - 1);
        return nums;
    }
}

// 贪心
class Solution2 {
    public int minDeletion(int[] nums) {
        int count = 0;
        for (int i = 0; i + 1 < nums.length;) {
            if (nums[i] == nums[i + 1]) {
                count += 1;
                i += 1; // 删去角标 i，以 i + 1 为下一个偶数对的起点
            } else {
                i += 2; // 跳到下一个偶数对
            }

        }
        // 删去后为奇数个，则再删去最后一个
        if ((nums.length - count) % 2 == 1)
            count += 1;
        return count;
    }
}

// 动态规划
/*
 * dp[i]表示以 i 为开头，删成满足题目条件的最小代价。根据nums[i]和nums[i+1]是否相等从后往前递推即可。
 * 假如nums[i]和nums[i+1]相等，那么必然不能以nums[i]开头，我们需要删掉nums[i]，也即dp[i] = dp[i+1] + 1，
 * 而如果不相等，可以不做处理，和以nums[i+2]开头删的次数一样，即dp[i] = dp[i+2]
 * 在初始化时，dp[len(nums)]本身是空数组，不需要删就满足条件，所以dp[-1] = 0,
 * 而dp[len(nums)-1]是奇数长度的，必须删掉自己，所以dp[-2] = 1
 */
class Solution3 {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        dp[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] != nums[i + 1])
                dp[i] = dp[i + 2];
            else {
                dp[i] = dp[i + 1] + 1;
            }
        }
        return dp[0];
    }
}

// 栈
class Solution4 {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        Deque<Integer> deque = new LinkedList<>();
        deque.push(nums[0]);
        for (int i = 1; i < n; i++) {
            // 对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
            // 若 deque 大小为偶数，可以直接添加
            if (deque.size() % 2 == 0) {
                deque.push(nums[i]);
            }
            // 当为奇数时，要判断是否与栈顶的元素相同，如果相同，则不能添加
            else {
                int val = deque.peek();
                // 当为奇数时，要判断是否与栈顶的元素相同，如果相同，则不能添加
                if (nums[i] == val)
                    continue;
                deque.push(nums[i]);
            }
        }
        if (deque.size() % 2 == 0)
            return n - deque.size();
        // 当栈的大小为奇数时，需要减去栈顶元素，所以删除的次数要加1
        return n - deque.size() + 1;
    }
}

// 双指针
// 只需要遍历一次数组，且在原数组进行修改，故时间复杂度O（n），空间复杂度O(1)
class Solution5 {
    public int minDeletion(int[] nums) {
        int n = nums.length, res = 0, left = 0, right = 1;
        while (right < n) {
            if ((left & 1) == 0 && nums[left] == nums[right]) {
                res++;
                right++;
            } else {
                nums[++left] = nums[right++];
            }
        }
        return (left & 1) == 0 ? res + 1 : res;
    }
}