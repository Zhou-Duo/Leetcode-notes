import java.util.Comparator;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int res = intervals.length;
        if (intervals.length == 1)
            return res;
        // 将区间按照会议开始实现升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] - o2[0] != 0)
                    return o1[0] - o2[0];
                else return o2[1] - o1[1];
            };
        });
        int[] baseInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            if (baseInterval[0] <= curInterval[0] && baseInterval[1] >= curInterval[1])
                res -= 1;
            else
                baseInterval = curInterval;
        }
        return res;
    }
}