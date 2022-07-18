import java.util.LinkedList;
import java.util.Queue;

class Solution1 {
    public boolean canChange(String start, String target) {
        if (start.length() != target.length())
            return false;
        if ((start.charAt(0) == 'L' && target.charAt(0) != 'L')
                || (start.charAt(target.length() - 1) == 'R' && target.charAt(target.length() - 1) != 'R'))
            return false;
        Queue<Character> orderStart = new LinkedList<Character>();
        Queue<Character> orderTarget = new LinkedList<Character>();
        for (int i = 0; i < start.length(); i++) {
            if (i < (start.length() - 1) && start.charAt(i) == 'R' && start.charAt(i + 1) == 'L') {
                if (target.charAt(i) != 'R' || target.charAt(i + 1) != 'L')
                    return false;
            }
            if (start.charAt(i) != '_')
                orderStart.add(start.charAt(i));
            if (target.charAt(i) != '_')
                orderTarget.add(target.charAt(i));
            if (start.charAt(i) == 'L' && orderStart.size() > orderTarget.size())
                return false;
            if (target.charAt(i) == 'R' && orderStart.size() < orderTarget.size())
                return false;
        }
        while (!orderStart.isEmpty() || !orderTarget.isEmpty()) {
            if (orderStart.poll() != orderTarget.poll())
                return false;
        }
        return true;
    }
}

class Solution2 {
    public boolean canChange(String start, String target) {
        // 无论怎么移动，由于 L 和 R 无法互相穿过对方，那么去掉 _ 后的剩余字符应该是相同的，否则返回 false。
        if (!start.replaceAll("_", "").equals(target.replaceAll("_", "")))
            return false;
        /*
         * 用双指针遍历 start[i] 和 target[j]，分类讨论：
         * * 如果当前字符为 L 且 i<j，那么这个 L 由于无法向右移动，返回 false；
         * * 如果当前字符为 R 且 i>j，那么这个 R 由于无法向左移动，返回 false。
         */
        for (int i = 0, j = 0; i < start.length(); i++) {
            if (start.charAt(i) == '_')
                continue;
            while (target.charAt(j) == '_')
                j++;
            if (i != j && (start.charAt(i) == 'L') == (i < j))
                return false;
            ++j;
        }
        return true;
    }
}