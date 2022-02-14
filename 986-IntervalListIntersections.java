import java.util.ArrayList;
import java.util.List;

/* 
 * 1. 怎么求交集区间
 * 交集区间的 start 取的是 A、B 子区间中较大的左界。
 * 交集区间的 end 取的是 A、B 子区间中较小的右界。
 * 只要满足 start <= end，就形成了一个交集区间。
 * 2. 双指针移动的时机
 * 求完一个交集区间后，较早结束的子区间，不可能再和别的子区间重叠，它的指针要移动。
 * 较长的子区间还有可能和别人重叠，它的指针暂时不动。
 */
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res =  new ArrayList<>();
        int p = 0;
        int q = 0;
        while (p < firstList.length && q < secondList.length) {
            int start = Math.max(firstList[p][0], secondList[q][0]);
            int end = Math.min(firstList[p][1], secondList[q][1]);
            if (start <= end) 
                res.add(new int[] {start,end});
            if (firstList[p][1] < secondList[q][1])
                p++;
            else q++;
        }
        return res.toArray(new int[res.size()][]);
    }
}