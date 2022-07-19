import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 1. 双指针
class Solution {
    public int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int left = 0, right = 0;
        // 区间内重复字符出现次数的历史最大值
        int maxRep = 0;
        while (right < s.length()) {
            num[s.charAt(right) - 'A'] += 1;
            /*
             * 不需要在每个子串种计算最多字符的出现次数，只需要在区间变大时更新区间内重复字符出现次数的历史最大值就可以。
             * 当max_count和k一定时，区间最大长度也就定了。
             * 当我们找到一个max_count之后，我们就能说我们找到了一个长度为d=max_count+k的合法区间，所以最终答案一定不小于d。
             * 所以，当发现继续向右扩展right不合法的时候，我们不需要不断地右移left，只需要保持区间长度为d向右滑动即可。
             * 如果有某个合法区间大于d，一定在某个时刻存在count[t]+1>max_count，这时再去更新max_count即可。
             */
            maxRep = Math.max(maxRep, num[s.charAt(right) - 'A']);
            if (right - left + 1 > k + maxRep) {
                num[s.charAt(left) - 'A'] -= 1;
                left += 1;
            }
            right++;
        }
        return right - left;
    }
}