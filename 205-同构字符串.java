import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character a = s.charAt(i);
            Character b = t.charAt(i);
            if ((s2t.containsKey(a) && s2t.get(a) != b)||(t2s.containsKey(b)&&t2s.get(b)!=a))
                return false;
                s2t.put(a, b);
                t2s.put(b, a);
        }
        return true;
    }
}

// 2. 数组
import java.util.HashMap;
import java.util.Map;

class Solution2 {
    public boolean isIsomorphic(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int[] preIndexOfs = new int[256];
        int[] preIndexOft = new int[256];
        for (int i = 0; i < chars.length; i++) {
            // 如果满足双映射关系，相同位置字母的位置会同步更新
            if (preIndexOfs[chars[i]] != preIndexOft[chart[i]]) {
                return false;
            }
            // 记录个字母出现的位置
            preIndexOfs[chars[i]] = i + 1;
            preIndexOft[chart[i]] = i + 1;
        }
        return true;
    }
}