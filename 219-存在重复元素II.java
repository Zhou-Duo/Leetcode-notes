import java.util.Map;
import java.util.Set;

// 1. 哈希集合
class Solution1 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k)
                return true;
            else
                map.put(nums[i], i);
        }
        return false;
    }
}

// 2. 滑动窗口
class Solution2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            /* 
             * 如果一个滑动窗口的结束下标是 i，则该滑动窗口的开始下标是 max(0,i−k)。
             * 如果 i > k，则下标 i - k - 1 处的元素被移出滑动窗口，因此将 nums[i−k−1] 从哈希集合中删除
             */
            if (i > k)
                window.remove(nums[i - k - 1]);
            if (!window.add(nums[i]))
                return true;
        }
        return false;
    }
}