import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> index1 = new HashMap<>();
        for (int index = 0; index < list1.length; index++) {
            index1.put(list1[index], index);
        }
        List<String> res = new ArrayList<String>();
        int minIndexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (index1.containsKey(list2[i])) {
                int IndexSum = index1.get(list2[i]) + i;
                if (IndexSum < minIndexSum) {
                    minIndexSum = IndexSum;
                    res.clear();
                    res.add(list2[i]);
                }
                else if (IndexSum == minIndexSum)
                    res.add(list2[i]);
            }
        }
        // List 直接用 toArray() 会 return Object[], 需要明确 initialize 输出的 array type
        return res.toArray(new String[res.size()]);
    }
}