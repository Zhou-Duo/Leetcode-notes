import java.util.List;

class Solution {
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0)
            return new ArrayList<String>();
        List<String> resString = new ArrayList<String>();
        if (nums.length == 0) {
            resString.add(nums[0] + "->" + nums[0]);
        }
        List<int[]> res = new ArrayList<int[]>();
        int[] curInterval = new int[] { nums[0], nums[0] };
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == curInterval[1] + 1)
                curInterval[1] += 1;
            else {
                res.add(curInterval);
                curInterval = new int[] { nums[i], nums[i] };
            }
        }
        res.add(curInterval);
        for (int[] interval : res) {
            if (interval[0] != interval[1])
                resString.add(interval[0] + "->" + interval[1]);
            else
                resString.add(String.valueOf(interval[0]));
        }
        return resString;
    }
}

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> resString = new ArrayList<String>();
        int L = 0;
        // 用双指针防止溢出
        for (int R = 0; R < nums.length; R++) {
            if (R == nums.length - 1 || nums[R + 1] != nums[R] + 1) {
                // 断开 interval
                if (L == R)
                    resString.add(String.valueOf(nums[L]));
                else
                    resString.add(nums[L] + "->" + nums[R]);
                L = R + 1;
            }
        }
        return resString;
    }
}