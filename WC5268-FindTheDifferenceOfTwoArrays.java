import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }
        List<Integer> res1  = new ArrayList<>();
        List<Integer> res2  = new ArrayList<>();
        for (int num : set1) {
            if (!set2.contains(num))
                res1.add(num);
        }
        for (int num : set2) {
            if (!set1.contains(num))
                res2.add(num);
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(res1);
        res.add(res2);
        return res;
    }
}