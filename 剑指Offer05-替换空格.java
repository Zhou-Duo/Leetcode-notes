// 1. 使用封装函数
class Solution1 {
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }
}

// 2. 遍历添加
class Solution2 {
    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ')
                res.append("%20");
            else res.append(c);
        }
        return res.toString();
    }
}
