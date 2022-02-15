class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right) { //  terminate when left = right
            int curRes = (right - left) * Math.min(height[left], height[right]);
            res = curRes > res ? curRes : res;
            if (height[left] < height[right])
                left++;
            else right--;
        }
        return res;
    }
}