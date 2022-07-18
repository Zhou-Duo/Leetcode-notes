import java.util.HashSet;
import java.util.Set;

// 1. 用哈希集合检测循环
/* 
 * 数位平方和可能拥有三种情况：
 * 1. 最终会得到 1。
 * 2. 最终会进入循环。
 * 3. 值会越来越大，最后接近无穷大。（可证明不可能，例如三位数最大999的数位和为243，这意味着它要么被困在 243 以下的循环内，要么跌到 1）
 */
class Solution1 {
    public boolean isHappy(int n) {
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        while (true) {
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            if (sum == 1)
                return true;
            else if (set.contains(sum))
                return false;
            else {
                set.add(sum);
                n = sum;
                sum = 0;
            }
        }
    }
}

// 2. 快慢指针检测循环
class Solution2 {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
            if (fast == 1)
                return true;
        } while (slow != fast) 
        return false;
    }

    public int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}