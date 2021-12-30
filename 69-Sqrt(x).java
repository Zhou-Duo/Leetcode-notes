class Solution {
    public int mySqrt(int x) {
        // 特殊值判断 ***
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }

        int left = 0;
        int right = x;
        while (left <= right) { //终止条件为 left > right
            int mid = left + (right - left) / 2;
            // 防止溢出, 用 x / mid > mid 代替 mid * mid < x
            if (x / mid == mid)
                return mid;
            else if (x / mid > mid)
                left = mid + 1;
            else if (x / mid < mid)
                right = mid - 1;
        }
        return right;
    }
}