import java.util.Arrays;

// 1. 模拟，每次取两种最多的水匹配
class Solution1 {
    public static int fillCups(int[] amount) {
        int step = 0;
        while (true) {
            Arrays.sort(amount);
            if (amount[2] > 0 && amount[1] > 0) {
                amount[2] -= 1;
                amount[1] -= 1;
                step++;
            } else {
                step += amount[2];
                return step;
            }
        }
    }
}

// 2. 贪心
class Solution2 {
    public static int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[0] + amount[1] <= amount[2])
            return amount[2];
        else {
            int t = amount[0] + amount[1] - amount[2];
            return (t + 1) / 2 + amount[2];
        }
    }
}

class Solution {
    public int fillCups(int[] amount) {
        return Math.max(amount[0],
                Math.max(amount[1], Math.max(amount[2], (amount[0] + amount[1] + amount[2] + 1) / 2)));
    }
}
