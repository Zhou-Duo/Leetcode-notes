import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode-cn.com/problems/sort-the-jumbled-numbers/
class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<int[]> list = new ArrayList<>();
        for (int num : nums) {
            list.add(new int[]{num, mappingNum(mapping, num)});
        }
        // * 卡点：想成了map如何根据value排序
        Collections.sort(list, (x,y) -> x[1] - y[1]);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i)[0];
        }
        return nums;  
    }

    public int mappingNum(int[] mapping, int num) {
        int bit = 1, res = 0;
        if (num == 0)
            return mapping[0];
        while (num > 0) {
            int bitNum = num % 10;
            int mappingNum = mapping[bitNum];
            res += mappingNum * bit;
            num /= 10;
            bit *= 10;
        }
        return res;
    }
}