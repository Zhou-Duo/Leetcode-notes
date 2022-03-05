import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/most-frequent-number-following-key-in-an-array/
class Solution {
    public int mostFrequent(int[] nums, int key) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == key) {
                int target = nums[i + 1];
                map.put(target, map.getOrDefault(target, 0)+1);
            }
        }
        int maxCnt = 0;
        int resTarget = 0;
        for (int target : map.keySet()) {
            if (map.get(target) > maxCnt) {
                resTarget = target;
                maxCnt = map.get(target);
            }
        }
        return resTarget;
    }
}