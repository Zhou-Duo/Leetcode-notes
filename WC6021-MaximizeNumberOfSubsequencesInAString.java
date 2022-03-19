// https://leetcode-cn.com/problems/maximize-number-of-subsequences-in-a-string/
// ## 注意考虑数据类型 long? int? BigInteger? 
class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        String text1 = pattern.charAt(0) + text;
        String text2 = text + pattern.charAt(1);
        return Math.max(subsequenceCount(text1, pattern), subsequenceCount(text2, pattern));
    }

    public long subsequenceCount(String text, String pattern) {
        long sum = 0;
        int countSoFar = 0;
        for (int index = text.length() - 1; index >= 0; index--) {
            if (text.charAt(index) == pattern.charAt(0))
                sum += countSoFar;
            if (text.charAt(index) == pattern.charAt(1))
                countSoFar++;
        }
        return sum;
    }
}
