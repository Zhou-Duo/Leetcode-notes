import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 遍历区间列表：
        // 首先将新区间左边且相离的区间加入结果集
        List<int[]> res = new ArrayList<int[]>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0])
            res.add(intervals[i++]);
        // 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
        // 将最终合并后的新区间加入结果集
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res.add(newInterval);
        // 最后将新区间右边且相离的区间加入结果集
        while (i < intervals.length) {
            res.add(intervals[i++]);
        }
        return res.toArray(new int[res.size()][]);
    }
}