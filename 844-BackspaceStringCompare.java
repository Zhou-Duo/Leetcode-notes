import javax.sound.sampled.SourceDataLine;

// 1. 直接删除字母
/* 
 * 时间复杂度：O(m+n)
 * 空间复杂度：O(m+n)
 */
class Solution1 {
    public boolean backspaceCompare(String s, String t) {
        String s_a = backspace(s);
        String t_a = backspace(t);
        return s_a.equals(t_a);
    }

    public String backspace(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '#')
                res.append(c);
            else if (res.length() > 0)
                res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }
}

// 2. 双指针
class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        while (i >= 0 || j >= 0) {
            int move_s = 0;
            int move_t = 0;
            // S 循环
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    move_s++;
                    i--;
                } else if (move_s > 0) {
                    move_s--;
                    i--;
                } else
                    break;
            }
            // T 循环
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    move_t++;
                    j--;
                } else if (move_t > 0) {
                    move_t--;
                    j--;
                } else
                    break;
            }
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j))
                return false;
            else if (i >= 0 || j >= 0)
                return false;
            i--;
            j--;
        }
        return true;
    }
}