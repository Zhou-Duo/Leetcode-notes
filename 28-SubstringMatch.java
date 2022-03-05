// KMP 算法
/* 
 * 复杂度分析
 * 时间复杂度：O(n+m)，其中 n 是字符串 haystack 的长度，m 是字符串 needle 的长度。我们至多需要遍历两字符串一次。
 * 空间复杂度：O(m)，其中 m 是字符串 needle 的长度。我们只需要保存字符串 needle 的前缀函数。
 */
class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        int[] next = get_next(needle);
        System.out.print("next[]: ");
        for (int x : next) {
            System.out.print(x+" ");
        }
        System.out.println();
        int i = 0, j = 0;
        while (i < n && j < m) {
            System.out.println("i = "+i+", j = "+j+", comparing "+haystack.charAt(i)+" and "+needle.charAt(j));
            // j == 0 && H[i] != N[j], i 向右移一位
            if (j == 0 && haystack.charAt(i) != needle.charAt(j))
                i++;
            // j > 0 && H[i] != N[j], j = next[j]
            else if (j > 0 && haystack.charAt(i) != needle.charAt(j))
                j = next[j];
            // H[i] == N[j], i 和 j一起向右移一位
            else if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
        }
        if (j == m) {
            System.out.println("Found it when j = "+ j);
            return (i - m);
        }
        return -1;    
    }

    public int[] get_next(String needle) {
        // a b c a b x
        // 0 0 0 0 1 2
        if (needle.length() == 0)
            return new int[0];
        if (needle.length() == 1)
            return new int[]{0};
        int[] next = new int[needle.length()];
        next[0] = 0;
        next[1] = 0;
        int i = 1, j = 0;
        while (i < needle.length() - 1) {
            if (needle.charAt(i) == needle.charAt(j))
                next[++i] = ++j;
            else if (j == 0)
                next[++i] = j;
            else j = next[j];
        }
        return next;
    }
}