import java.util.ArrayList;
import java.util.List;

// 动态规划
class Solution1 {
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        String ans = s.substring(0, 1);
        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i < s.length() - len + 1; i++) {
                int j = i + len - 1;
                if ((j < i + 2|| dp[i + 1][j - 1] == true) && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    ans = s.substring(i, j + 1);
                } else
                    dp[i][j] = false;
            }
        }
        return ans;
    }
}

// Manacher 算法
/* 
 * 专门用于查找最长回文子串的算法，时间复杂度 O(n)
 * 将原始字符串进行预处理，在预处理字符串上执行「动态规划」和「中心扩散」方法。
 * 1. 为了将奇、偶数回文串的性质统一表示，将原始字符串进行预处理，用不在输入字符串中的字符隔开，eg‘#’，处理完成后字符串长度一定为偶数
 * 2. 计算出对每个i，以i为中心的最长回文子串的长度 n，再转化为对应原始字符串中的最长回文子串的长度 (n-1)/2
 * 优化：
 * 第 2 步：用空间换时间，使用动态规划的思想
 * maxRight = 当前使用中心扩散法能够走到的最右边下标;
 * center = 与maxRight对应的回文中心的下标;
 * mirror = 2 * center - 1;
 * 情况 1：当 i >= maxRight的时候，只能使用中心扩散法逐个匹配
 * 情况 2：当 i < maxRight时：
 *  ①当 p[mirror] < maxRight-i 时，有 p[i] = p[mirror]。
 *  ②当 p[mirror] == maxRight-i 时，p[i] 至少等于 maxRight - i,需要从 maxRight 开始扩散。
 *  ③当 p[mirror] > maxRight-i 时，有 p[i] = maxRight - i。
 * 综合以上三种情况，p[i] = min( p[mirror], maxRight-i)，然后尝试中心扩散。
 */
class Solution2 {
    public String longestPalindrome(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        // 插入‘#’，处理完成后字符串长度一定为偶数
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        // arm_len 维持每个位置 i，以 i 为中心的最长回文子串的长度 n
        List<Integer> arm_len = new ArrayList<Integer>();
        // 初始 maxRight = -1，center = -1
        int right = -1, j = -1;
        // 遍历位置 i
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                // p[i] = min( p[mirror], maxRight-i)，然后尝试中心扩散
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                // 当 i >= maxRight的时候，只能使用中心扩散法逐个匹配
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            // 更新 right,它是遍历过的 i 的 i + arm_len[i] 的最大者
            // 更新 j, 它是与 right 对应的回文中心的下标;
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            // 更新最大回文串的起始点 start 和终点 end
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }
        //还原 string，去掉 ‘#’
        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }
}
