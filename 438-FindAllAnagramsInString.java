import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1. 滑动窗口
/* 
 * 时间复杂度：O(m+(n−m)×Σ)，其中 n 为字符串 s 的长度，m 为字符串 p 的长度，Σ 为所有可能的字符数。
 * 我们需要 O(m) 来统计字符串 p 中每种字母的数量；需要 O(m) 来初始化滑动窗口；
 * 需要判断 n−m+1 个滑动窗口中每种字母的数量是否与字符串 p 中每种字母的数量相同，每次判断需要 O(Σ) ，Σ=26。
 * 空间复杂度：O(Σ)。用于存储字符串 p 和滑动窗口中每种字母的数量。
 */
class Solution1 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();

        if (s.length() < p.length()) 
            return res;
        
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(sCount, pCount))
            res.add(0);

        for (int i = 0; i < s.length() - p.length(); i++) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + p.length()) - 'a'];

            if (Arrays.equals(sCount, pCount)) {
                res.add(i + 1);
            }
        }
        return res;
    }
}

// 2. 双指针
/*
 * 时间复杂度：O(n)
 * 空间复杂度：O(1) 
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();

        if (s.length() < p.length()) 
            return res;

        int[] count = new int[26];
        for (int i = 0; i < p.length(); i++) {
            count[p.charAt(i) - 'a']--;
        }

        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'a']++;
            while (count[s.charAt(right) - 'a'] > 0) { //terminate when count[s.charAt(right) - 'a'] == 0, 即右指针指向的字母现在的计数正确
                /* 
                 * 理解为什么只需要判断有指针所在位置？
                 * 每次都保证了右指针所指向的字母count=0
                 * 而之后只有右移作指针的时候，会对count减少，而无增加操作
                 * 因此在最后，substring长度正确，则count总数为0，且所有字母的count<=0
                 * 即所有字母count=0，匹配正确
                 */
                // left指针右移
                count[s.charAt(left) - 'a']--;
                left++;
            }
            if (right - left + 1 == p.length())
                res.add(left);
        }
        return res;
    }
}