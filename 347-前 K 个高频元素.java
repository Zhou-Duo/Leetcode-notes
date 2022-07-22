import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// 1. 直接排序 O(nlogn)
class Solution1 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        int[] ans = new int[k];
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(counts.entrySet());
        Collections.sort(list, (e1, e2) -> -(e1.getValue().compareTo(e2.getValue())));
        for (int i = 0; i < k; i++) {
            ans[i] = list.get(i).getKey();
        }
        return ans;
    }
}

// 2. PriorityQueue O(nlogk)
// 不需要全部排序，只需要维护大小为 k 的小顶堆
/*
 * 时间复杂度：遍历原数组，并使用哈希表记录出现次数，每个元素需要 O(1) 的时间，共需 O(N) 的时间。
 * 随后，我们遍历「出现次数数组」，由于堆的大小至多为 k，因此每次堆操作需要 O(logk) 的时间，共需 O(Nlogk) 的时间。二者之和为
 * O(Nlogk)。
 */
class Solution2 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        int[] ans = new int[k];
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        /*
         * 利用堆的思想：建立一个小顶堆，然后遍历「出现次数数组」：
         * 如果堆的元素个数小于 k，就可以直接插入堆中。
         * 如果堆的元素个数等于 k，则检查堆顶与当前出现次数的大小。如果堆顶更大，说明至少有 k
         * 个数字的出现次数比当前值大，故舍弃当前值；否则，就弹出堆顶，并将当前值插入堆中。
         */
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() >= k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[] { num, count });
                }
            } else
                queue.offer(new int[] { num, count });
        }
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll()[0];
        }
        return ans;
    }
}

class Solution3 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        return counts.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -entry.getValue()))
                .limit(k)
                .mapToInt(entry -> entry.getKey())
                .toArray();
    }
}