import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countSumAB = new HashMap<>();
        int ans = 0;
        for (int a : A) {
            for (int b : B) {
                countSumAB.put(a + b, countSumAB.getOrDefault(a + b, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                if (countSumAB.containsKey(-c - d)) {
                    ans += countSumAB.get(-c - d);
                }
            }
        }
        return ans;
    }
}