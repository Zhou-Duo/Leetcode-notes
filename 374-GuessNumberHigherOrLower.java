class Solution {
    int guess(int num) {
        /**
         * @param num your guess
         * @return -1 if num is lower than the guess number
         *         1 if num is higher than the guess number
         *         otherwise return 0
         */
        return 0;
    }

    public int guessNumber(int n) {
        int left = 0;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == 0)
                return mid;
            else if (guess(mid) == -1)
                right = mid - 1;
            else if (guess(mid) == 1)
                left = mid + 1;
        }
        return left;
    }

}