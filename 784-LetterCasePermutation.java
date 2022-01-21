import java.util.ArrayList;
import java.util.List;

// First Attempt : Recursion
/* 
 * 复杂度分析
 * 时间复杂度：O(2^{N} * N)，其中 NN 是 S 的长度。
 * 空间复杂度：O(2^N * N)。
 */
class Solution1 {
    public List<String> letterCasePermutation(String s) {
        List<String> newCasedStringList = new ArrayList<String>();
        // 空 string
        if (s.length() == 0)
            return newCasedStringList;

        // 只有 1 个 char
        if (s.length() == 1) {
            if (Character.isDigit(s.charAt(0))) {
                newCasedStringList.add(s);
            } else {
                newCasedStringList.add(s.toUpperCase());
                newCasedStringList.add(s.toLowerCase());
            }
            return newCasedStringList;
        }

        // normal case
        List<String> casedStringList = letterCasePermutation(s.substring(0, s.length() - 1));
        Character lastChar = s.charAt(s.length() - 1);
        if (Character.isDigit(lastChar)) {
            for (String string : casedStringList) {
                StringBuilder casedStringBuilder = new StringBuilder(string.toString());
                casedStringBuilder.append(lastChar);
                newCasedStringList.add(casedStringBuilder.toString());
            }
        } else {
            for (String string : casedStringList) {
                StringBuilder casedStringBuilderUpper = new StringBuilder(string.toString());
                StringBuilder casedStringBuilderLower = new StringBuilder(string.toString());
                casedStringBuilderUpper.append(Character.toUpperCase(lastChar));
                casedStringBuilderLower.append(Character.toLowerCase(lastChar));
                newCasedStringList.add(casedStringBuilderUpper.toString());
                newCasedStringList.add(casedStringBuilderLower.toString());
            }
        }
        return newCasedStringList;
    }
}

// Second Attempt: 
/* 
 * 如果下一个字符 c 是字母，将当前已遍历过的字符串全排列复制两份，在第一份的每个字符串末尾添加 lowercase(c)，在第二份的每个字符串末尾添加 uppercase(c)。
 * 如果下一个字符 c 是数字，将 c 直接添加到每个字符串的末尾。
 */
class Solution2 {
    public List<String> letterCasePermutation(String s) {
        List<StringBuilder> ans = new ArrayList<StringBuilder>();
        ans.add(new StringBuilder());

        for (char ch : s.toCharArray()) {
            int n = ans.size();
            if (Character.isLetter(ch)) {
                for (int i = 0; i < n; i++) {
                    ans.add(new StringBuilder(ans.get(i)));
                    ans.get(i).append(Character.toLowerCase(ch));
                    ans.get(i).append(Character.toLowerCase(ch));
                }
            }
            else {
                for (int i = 0; i < n; i++) {
                    ans.get(i).append(ch);
                }
            }
        }

        List<String> finalAns = new ArrayList<String>();
        for (StringBuilder sb: ans)
            finalAns.add(sb.toString());
        return finalAns;
    }
}

// 3. 回溯法
class Solution {
    List<String> res = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        dfs(chs, n, 0);
        return res;
    }

    public void dfs(char[] chs, int n, int begin) {
        res.add(new String(chs));
        for (int i = begin; i < n; i++) {
            if (Character.isLetter(chs[i])) {
                char tmp = chs[i];
                chs[i] = (char)(chs[i] - 'a' >= 0 ? chs[i] - 32 : chs[i] + 32);
                dfs(chs, n, i + 1);
                chs[i] = tmp;
            }
        }
    }
}