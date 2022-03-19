// 1. 反转比较
class Solution1 {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }
}

// 2. 数学算法
class Solution2 {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10)
            div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
}

// 3. 反转后半部分与前半部分比较
class Solution3 {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }
}

