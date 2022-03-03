// 1. 列表遍历拼接
class Solution1 {
    public String reverseLeftWords(String s, int n) {
        n = n % s.length();
        if (n <= 0)
            return s;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            res.append(s.charAt((n + i) % s.length()));
        }
        return res.toString();
    }
}

// 2. 字符串切片
class Solution2 {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }
}