import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// 1. 普通滑动窗口
class Solution1 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0, right = 0;
        // window 内的不同字符
        int[] count = new int[58];
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            count[s.charAt(right)- 'A'] += 1;
            set.add(s.charAt(right));
            if (set.size() > 2) {
                count[s.charAt(left) - 'A'] -= 1;
                if (count[s.charAt(left) - 'A'] == 0)
                    set.remove(s.charAt(left));
                left++;
            }
            right++;
        }
        return right - left;
    }
}

// 2. 滑动窗口（利用 HashMap，把字符串里的字符都当做键，记录该字符在窗口中的最右位置作为值
class Solution2 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // 如果 s 的长度小于 3 ，返回 n
        int n = s.length();
        if (n < 3)
            return n;
        int left = 0, right = 0, max_len = 0;
        HashMap<Character, Integer> hashmap = new HashMap<>();
        while (right < n) {
            hashmap.put(s.charAt(right), right);
            if (hashmap.size() >= 3) {
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // move left pointer of the sliding window
                left = del_idx + 1;
            }
            right++;
            max_len = Math.max(max_len, right - left);
        }
        return max_len; 
    }
}