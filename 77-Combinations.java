import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 1. 递归
/* 
 * 组合问题，相对于排列问题而言，不计较一个组合内元素的顺序性（即 [1, 2, 3] 与 [1, 3, 2] 认为是同一个组合）。
 * 因此很多时候需要按某种顺序展开搜索，这样才能做到不重不漏。
 */
class Solution1 {
    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                List<Integer> thisValue = new ArrayList<>();
                thisValue.add(i);
                res.add(thisValue);
            }
            return res;
        }

        for (int i = n; i >= 1; i--) {
            List<List<Integer>> ans = combine(i - 1, k - 1);
            for (int j = 0; j < ans.size(); j++) {
                ans.get(j).add(i);
            }
            res.addAll(ans);
        }
        return res;
    }
}

// 2. 回溯算法
/* 
 * 暂时未完全理解
 */
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        // 从 1 开始是题目的设定
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点
        for (int i = begin; i <= n; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
        }
    }
}