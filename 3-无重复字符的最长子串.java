import java.util.HashSet;
import java.util.Set;

// 滑动窗口
/* 
 * 复杂度分析
 * 时间复杂度：O(N)，其中 N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
 * 空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。
 * 在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0,128) 内的字符，即 ∣Σ∣=128。
 * 我们需要用到哈希集合来存储出现过的字符，而字符最多有 ∣Σ∣ 个，因此空间复杂度为 O(∣Σ∣)。
 */

/* 
 * 使用一种数据结构来判断是否有重复的字符：
 * 常用的数据结构为哈希集合（即 C++ 中的 std::unordered_set，Java 中的 HashSet，Python 中的 set, JavaScript 中的 Set）。
 * 在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往哈希集合中添加一个字符。
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int right = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (right + 1 < n && !occ.contains(s.charAt(right + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(right + 1));
                ++right;
            }
            // 第 i 到 right 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, right - i + 1);
        }
        return ans;
    }
}

// 2. 不定长滑动窗口
class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, res = 1;
        while (right < s.length()) {
            while (left <= right && set.contains(s.charAt(right))) {
                // 可以直接用 remove，因为加入 s.charAt(right) 前区间为合法区间
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            right += 1;
            res = Math.max(res, right - left);
        }
        return res;
    }
}

// 3. 长度单调递增的滑动窗口
class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> repetitive = new HashSet<>();
        int left = 0, right = 0;
        int[] count = new int[128];
        while (right < s.length()) {
            count[s.charAt(right)] += 1;
            // 判断当前区间是否合法
            if (count[s.charAt(right)] > 1 || repetitive.size() > 0) {
                if (count[s.charAt(right)] > 1)
                    repetitive.add(s.charAt(right));
                count[s.charAt(left)] -= 1;
                if (count[s.charAt(left)] <= 1)
                    repetitive.remove(s.charAt(left));
                left++;
            }
            right += 1;
        }
        return right - left;
    }
}