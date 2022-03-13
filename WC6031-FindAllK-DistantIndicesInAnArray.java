// https://leetcode-cn.com/problems/find-all-k-distant-indices-in-an-array/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                for (int j = -k; j <= k; j++) {
                    int index = i + j;
                    if (index >= 0 && index <= nums.length - 1 && !res.contains(index))
                        res.add(index);
                }
            }
        }
        List<Integer> resList = new ArrayList<>(res);
        Collections.sort(resList);
        return resList;
    }
}