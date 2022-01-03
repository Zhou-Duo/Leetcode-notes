// 双指针
/* 
* 时间复杂度：O(N)，其中 N 为字符数组的长度。一共执行了 N/2 次的交换。
* 空间复杂度：O(1)。只使用了常数空间来存放若干变量。 
*/

class Solution {
    public void reverseString(char[] s) {
        int start = 0, end = s.length - 1;
        while (start < end)
            swap(s, start++, end--);
    }

    public void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}