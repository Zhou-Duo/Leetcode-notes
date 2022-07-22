import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

class RandomizedSet {
    List<Integer> nums;
    Map<Integer, Integer> index;
    Random random;

    public RandomizedSet() {
        // O(1) 获取，必须用 ArrayList 而非 LinkedList
        nums = new ArrayList<>();
        index = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (index.containsKey(val))
            return false;
        else {
            index.put(val, nums.size());
            nums.add(val);
            return true;
        }
    }

    public boolean remove(int val) {
        if (!index.containsKey(val))
            return false;
        else {
            // 为了确保严格 O(1)，我们不能「在数组非结尾位置添加/删除元素」。
            // 最后一个元素交换要移除的元素降低移动元素的复杂度
            int i = index.get(val);
            int last = nums.get(nums.size() - 1);
            nums.set(i, last);
            index.put(last, i);
            nums.remove(nums.size() - 1);
            index.remove(val);
            return true;
        }
    }

    public int getRandom() {
        int randomInt = random.nextInt(nums.size());
        return nums.get(randomInt);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */