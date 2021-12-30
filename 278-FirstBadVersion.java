/* The isBadVersion API is defined in the parent class VersionControl.
    boolean isBadVersion(int version); */

class Solution {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) { // 循环直至区间左右端点相同
            int mid = left + (right - left) / 2; // 防止计算时溢出
            /*
             * 不能用（left+right）/2形式，当left和right都是int，两个值的初始值都超过int限定大小的一半，
             * 那么left+right就会发生溢出，所以应该用left+(right-left)/2来防止求中值时候的溢出。
             */
            if (isBadVersion(mid))
                right = mid; // 答案在区间 [left, mid] 中
            else
                left = mid + 1; // 答案在区间 [mid+1, right] 中
        }
        // 此时有 left == right，区间缩为一个点，即为答案
        return left;
    }

    public boolean isBadVersion(int n) {
        /* return if version n is a bad version */
        return false;
    }
}
