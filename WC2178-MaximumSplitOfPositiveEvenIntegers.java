import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/maximum-split-of-positive-even-integers/
// 贪心
class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 == 1)
            return new ArrayList<>();
        Long even = 2L;
        List<Long> list = new ArrayList<>();
        while (finalSum >= 2 * even + 2) {
            list.add(even);
            finalSum -= even;
            
        }
        return list;
    }
}