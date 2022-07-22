import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

// 1. 哈希映射
class TwoSum {
    List<Integer> nums;
    Map<Integer, Integer> numCounts;

    public TwoSum() {
        numCounts = new HashMap<>();
        nums = new ArrayList<>();
    }

    public void add(int number) {
        numCounts.put(number, numCounts.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : numCounts.entrySet()) {
            int hubu = value - entry.getKey();
            if (hubu == entry.getKey()) {
                if (numCounts.get(hubu) > 1)
                    return true;
            } else {
                if (numCounts.containsKey(hubu))
                    return true;
            }
        }
        return false;
    }
}

// 2. 双指针
class TwoSum2 {
    List<Integer> nums;
    Boolean is_sorted;

    public TwoSum() {
        nums = new ArrayList<>();
        is_sorted = false;
    }

    public void add(int number) {
        nums.add(number);
        is_sorted = false;
    }

    public boolean find(int value) {
        if (!is_sorted) {
            Collections.sort(nums);
            is_sorted = true;
        }
        int left = 0, right = nums.size() - 1;
        while (left < right) { // cease when left == right
            int sum = nums.get(left) + nums.get(right);
            if (sum == value)
                return true;
            else if (sum < value)
                left += 1;
            else
                right += 1;
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */