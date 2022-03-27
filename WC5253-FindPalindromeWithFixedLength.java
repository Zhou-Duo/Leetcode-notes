// https://leetcode-cn.com/problems/find-palindrome-with-fixed-length/comments/
class Solution {
    public long[] kthPalindrome(int[] queries, int intLength) {
        long[] res = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = kthPalindromeSingle(queries[i], intLength);
        }
        return res;
    }

    public long kthPalindromeSingle(int query, int intLength) {
        // 卡点：如何构建回文数。注意不用求出 centerBit
        long pal = (long) Math.pow(10, (intLength - 1) / 2);
        if (query - 1 >= 9 * pal) {
            return -1;
        }
        pal += query - 1;
        long end = pal;
        if (intLength % 2 == 1) {
            end /= 10;
        }
        while (end != 0) {
            pal = pal * 10 + end % 10;
            end /= 10;
        }
        return pal;
    }
}