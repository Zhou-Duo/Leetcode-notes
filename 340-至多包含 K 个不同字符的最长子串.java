class Solution2 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        int left = 0, right = 0, max_len = 0;
        HashMap<Character, Integer> hashmap = new HashMap<>();
        while (right < n) {
            hashmap.put(s.charAt(right), right);
            if (hashmap.size() > k) {
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

class Solution1 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0, right = 0;
        // window 内的不同字符
        int[] count = new int[128];
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            count[s.charAt(right)- '!'] += 1;
            set.add(s.charAt(right));
            if (set.size() > k) {
                count[s.charAt(left) - '!'] -= 1;
                if (count[s.charAt(left) - '!'] == 0)
                    set.remove(s.charAt(left));
                left++;
            }
            right++;
        }
        return right - left;
    }
}