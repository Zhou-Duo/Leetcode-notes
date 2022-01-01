
// 1. 合并后排序
/*
* 时间复杂度：O((m+n)log(m+n))。
* 排序序列长度为 m+n，套用快速排序的时间复杂度即可，平均情况为 O((m+n)log(m+n))。
* 
* 空间复杂度：O(log(m+n))。
* 排序序列长度为 m+n，套用快速排序的空间复杂度即可，平均情况为 O(log(m+n))。
*/
import java.util.Arrays;

class Solution1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}

// 2. 双指针
/*
* 时间复杂度：O(m+n)。
* 指针移动单调递增，最多移动 m+nm+n 次，因此时间复杂度为 O(m+n)。
*
* 空间复杂度：O(m+n)。
* 需要建立长度为 m+nm+n 的中间数组 sorted。
*/
class Solution2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        /*
         * 注意此处条件不可合并
         * 必须先判断 p1, p2 是否等于 m, n 来排除 m, n 等于 0
         * 否则出现 OutOfIndex 错误
         */
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
    }
}

// 3. 逆向双指针
/* 
* 方法二中，之所以要使用临时变量，是因为如果直接合并到数组 nums1 中，nums1 中的元素可能会在取出之前被覆盖。
* 那么如何直接避免覆盖 nums1 中的元素呢？观察可知，nums1 的后半部分是空的，可以直接覆盖而不会影响结果。
* 因此可以指针设置为从后向前遍历，每次取两者之中的较大者放进 nums1 的最后面。 
*/

/*
* 时间复杂度：O(m+n)。
* 指针移动单调递减，最多移动 m+nm+n 次，因此时间复杂度为 O(m+n)。
* 
* 空间复杂度：O(1)。
* 直接对数组 nums1 原地修改，不需要额外空间。
*/
class Solution3 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1; 
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) { /* 注意此处条件 */
            if (p1 == -1) /* 注意此处条件 */
                cur = nums2[p2--];
            else if (p2 == -1)
                cur = nums1[p1--];
            else if (nums1[p1] > nums2[p2])
                cur = nums1[p1--];
            else
                cur = nums2[p2--];
            nums1[tail--] = cur;
        }
    }
}