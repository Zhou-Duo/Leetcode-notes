import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0)
            return new int[0][2];
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < curInterval[0])
                merged.add(curInterval);
            else {
                // 反之将当前区间合并至结果数组的最后区间
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], curInterval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}