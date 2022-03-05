import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://leetcode-cn.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/

// 利用 有向无环图 (DAG) 存在拓扑序
// 1. BFS
/* 
 * 复杂度分析：
 * 时间复杂度：每个 Node 入列一次，每次遍历其所有子孙，因此与图中边的数目有关，最坏情况下 O(n^2)。
 * 空间复杂度：O(n^2)。
 */
class Solution1 {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        // adj map
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        // 计算 degree， 以 degree = 0 的 node 为起点
        int[] degree = new int[n];
        for (int[] edge : edges) {
            degree[edge[1]] += 1;
            adjMap.computeIfAbsent(edge[0], z -> new ArrayList<Integer>()).add(edge[1]);
        }
        // 初始化 ans
        List<Set<Integer>> ancestorSets = new ArrayList<>();
        for (int i = 0; i < n; i++)
            ancestorSets.add(new HashSet<>());
        // BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                // degree == 0 代表计算完成
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            if (adjMap.get(curNode) != null) {
                // 若 adjMap.get(curNode) == null，则为终点 Node，无子孙
                for (int adjNode : adjMap.get(curNode)) {
                    degree[adjNode] -= 1;
                    if (degree[adjNode] == 0)
                        queue.offer(adjNode);
                    ancestorSets.get(adjNode).addAll(ancestorSets.get(curNode));
                    ancestorSets.get(adjNode).add(curNode);
                }
            }
        }
        // 转为 List<List<Integer>>
        List<List<Integer>> res = new ArrayList<>();
        for (Set<Integer> ancestorSet : ancestorSets) {
            List<Integer> ancestorList = new ArrayList<Integer>(ancestorSet);
            ancestorList.sort(Comparator.comparingInt(x -> x));
            res.add(ancestorList);
        }
        return res;
    }
}

// 2. DFS
// 用哈希表记录父子关系，从每个节点反向搜索其祖先节点，直到无祖先节点。
/* 
 * 复杂度分析：
 * 时间复杂度：每个 Node visit 一次，每次遍历其祖先，直到祖先已 visited / 无祖先。因此与图中边的数目有关，最坏情况下 O(n^2)。
 * 空间复杂度：O(n^2)。
 */
class Solution {
    Map<Integer, List<Integer>> parents = new HashMap<>();
    List<Set<Integer>> ancestorSets = new ArrayList<>();
    boolean[] visited;
        
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        // 建立反向图
        for (int[] edge : edges) {
            parents.computeIfAbsent(edge[1], z -> new ArrayList<Integer>()).add(edge[0]);
        }
        visited = new boolean[n];
        // 初始化 ans
        for (int i = 0; i < n; i++)
            ancestorSets.add(new HashSet<>());
        // DFS
        for (int i = 0; i < n; i++) {
            DFS(i);
        }
        // 转为 List<List<Integer>>
        List<List<Integer>> res = new ArrayList<>();
        for (Set<Integer> ancestorSet : ancestorSets) {
            List<Integer> ancestorList = new ArrayList<Integer>(ancestorSet);
            ancestorList.sort(Comparator.comparingInt(x -> x));
            res.add(ancestorList);
        }
        return res;
    }
    
    public void DFS(int curNode){
        if (visited[curNode] == true)
            return;
        visited[curNode] = true;
        if (parents.get(curNode) == null)
            return;
        else {
            for (int parentNode : parents.get(curNode)) {
                DFS(parentNode);
                ancestorSets.get(curNode).addAll(ancestorSets.get(parentNode));
                ancestorSets.get(curNode).add(parentNode);
            }
        }
    }
}
