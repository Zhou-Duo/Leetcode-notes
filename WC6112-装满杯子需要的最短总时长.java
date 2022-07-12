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
                step ++;
            }
            else {
                step += amount[2];
                return step;
            }
        }
    }
}

// 2. 贪心
class Solution2 {
    public static int fillCups(int[] amount) {
        int step = 0;
        while (true) {
            Arrays.sort(amount);
            if (amount[2] > 0 && amount[1] > 0) {
                amount[2] -= 1;
                amount[1] -= 1;
                step ++;
            }
            else {
                step += amount[2];
                return step;
            }
        }
    }
}
