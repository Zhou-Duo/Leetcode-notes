import java.util.HashMap;
import java.util.Map;

// 1. Brute-force, 两层遍历
/*
* 复杂度分析
* 时间复杂度：O(n^2)，其中 n 是数组的长度。
* 空间复杂度：O(1)。
*/
class Solution1 {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target)
                    return new int[]{i + 1, j + 1};
                else if (numbers[i] + numbers[j] > target)
                    break;
            }
        }
        return new int[]{-1, -1};
    }
}

// 2. 二分查找
/*
* 复杂度分析
* 时间复杂度：O(nlogn)，其中 n 是数组的长度。需要遍历数组一次确定第一个数，时间复杂度是 O(n)。
* 寻找第二个数使用二分查找，时间复杂度是 O(logn)，因此总时间复杂度是 O(nlogn)。
* 空间复杂度：O(1)。
*/

class Solution2 {
    public int[] twoSum(int[] numbers, int target) {
        for (int currentPos = 0; currentPos < numbers.length - 1; currentPos++) {
            int targetNum = target - numbers[currentPos];
            int start = currentPos + 1;
            int end = numbers.length - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                int midNum = numbers[mid];
                if (midNum == targetNum)
                    return new int[]{currentPos + 1, mid + 1};
                else if (midNum < targetNum)
                    start = mid + 1;
                else if (midNum > targetNum)
                    end = mid - 1;                
            }
        }
        return new int[]{-1, -1};
    }
}

// 3. 双指针
/* 
* 复杂度分析
* 时间复杂度：O(n)，其中 n 是数组的长度。两个指针移动的总次数最多为 n 次。
* 空间复杂度：O(1)。
 */
class Solution3 {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int currentSum = numbers[i] + numbers[j];
            if (currentSum == target)
                return new int[]{i + 1, j + 1};
            else if (currentSum > target)
                j -= 1;
            else if (currentSum < target)
                i +=1;
        }
        return new int[]{-1, -1};
    }
}

// 4. 哈希表，时间复杂度O(n)，空间复杂度O(n)
class Solution4 {
    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        Map<Integer, Integer> map = new HashMap<>();
        // 将数据存入哈希表
        for (int i = 0; i < len; i++) {
            map.put(numbers[i], i+1);
        }
        for (int i = 0; i < len; i++) {
            int targetNum = target - numbers[i];
            if (map.containsKey(targetNum))
                return new int[]{i+1, map.get(targetNum)};
        }
        return new int[]{-1, -1};
    }
}