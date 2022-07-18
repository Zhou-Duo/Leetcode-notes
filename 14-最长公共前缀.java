import java.util.Arrays;

// 1. 竖向扫描
class Solution1 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || c != strs[j].charAt(i)) // 公共前缀判定结束
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}

// 2. 横向扫描
class Solution2 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        // 找出S1与Si间的最长公共字符串
        // indexOf：当存在串时返回>0；不存串时返回-1
        // 只要不存在串，就缩减串的规模，再进行查找
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty())
                    return "";
            }
        }
        return prefix;
    }
}

// 3. 比较最大和最小 string
class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs, (a, b) -> a.compareTo(b)); // 降序
        String first = strs[0], last = strs[strs.length - 1];
        int i, num = Math.min(first.length(), last.length());
        for (i = 0; i < num && first.charAt(i) == last.charAt(i); i++);
        return last.substring(0, i);
    }
}