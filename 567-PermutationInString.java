import java.util.Arrays;

// 1. 滑动窗口
/* 
 * 由于排列不会改变字符串中每个字符的个数，所以只有当两个字符串每个字符的个数均相等时，一个字符串才是另一个字符串的排列。
 * 根据这一性质，记 s_1的长度为 n，我们可以遍历 s_2中的每个长度为 n 的子串，判断子串和 s_1中每个字符的个数是否相等，若相等则说明该子串是 s_1的一个排列。
 * 使用两个数组 cnt1 和 cnt2，cnt1 统计 s1 中各个字符的个数，cnt2 统计当前遍历的子串中各个字符的个数。
 * 由于需要遍历的子串长度均为 n，我们可以使用一个固定长度为 n 的滑动窗口来维护 cnt2：滑动窗口每向右滑动一次，就多统计一次进入窗口的字符，少统计一次离开窗口的字符。
 * 然后，判断cnt1是否与cnt2相等，若相等则意味着s1 的排列之一是 s2 的子串。

 */

/* 
 * 复杂度分析
 * 时间复杂度：O(n+m+∣Σ∣)，其中 n 是字符串 s1的长度，m 是字符串 s2 的长度，Σ 是字符集, 这道题中的字符集是小写字母，∣Σ∣=26。
 * 空间复杂度：O(∣Σ∣)。
 */
class Solution1 {
    public boolean checkInclusion(String s1, String s2) {
        int targetLen = s1.length();
        int Len = s2.length();
        // 首先判断特殊情况，targetLen > Len
        if (targetLen > Len) {
            return false;
        }

        // 用两个数组 cnt1 和 cnt2 代表
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        // 建立滑动窗口，长度为 targetLen
        for (int i = 0; i < targetLen; i++) {
            ++cnt1[s1.charAt(i) - 'a']; // **** 将字母转化为数字
            ++cnt2[s2.charAt(i) - 'a'];
        }

        if (Arrays.equals(cnt1, cnt2)) // 将 count list 转化为 Array 比较
            return true;
        
        for (int i = targetLen; i < Len; i++) {
            --cnt2[s2.charAt(i - targetLen) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) // 将 count list 转化为 Array 比较
                return true;
        }
        return false;
    }
}

// 2. 双指针
/* 
 * 回顾方法一的思路，我们在保证区间长度为n的情况下，去考察是否存在一个区间使得cnt 的值全为0。
 * 反过来，还可以在保证cnt的值不为正的情况下，去考察是否存在一个区间，其长度恰好为n。
 * 初始时，仅统计s1中的字符，则cnt的值均不为正，且元素值之和为一n。
 * 然后用两个指针left和right 表示考察的区间[left, right]. right 每向右移动一次，就统计一次进入区间的字符x。
 * 为保证cnt的值不为正，若此时cnt[x] > 0，则向右移动左指针，减少离开区间的字符的cnt 值直到cnt【x】≤0。
 * 注意到【left， right】的长度每增加1，cnt 的元素值之和就增加1。当[left, right]的长度恰好为n时，就意味着cnt的元素值之和为0。
 * 由于cnt的值不为正，元素值之和为0就意味着所有元素均为0，这样我们就找到了一个目标子串。
 */
class Solution2 {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < m; ++right) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }
}
