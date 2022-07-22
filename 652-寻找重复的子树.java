import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/* 
 * 复杂度分析
 * 时间复杂度：O(N^2)，其中 N 是二叉树上节点的数量。遍历所有节点，在每个节点处序列化需要时间 O(N)。
 * 空间复杂度：O(N^2)，count 的大小。
 */
class Solution {
    HashMap<String, Integer> count = new HashMap<>();
    List<TreeNode> ans = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialize(root);
        return ans;
    }

    private String serialize(TreeNode node) {
        if (node == null)
            return "#";
        StringBuffer sb = new StringBuffer();
        sb.append(serialize(node.left));
        sb.append(',');
        sb.append(node.val);
        sb.append(',');
        sb.append(serialize(node.right));
        sb.append(',');
        String serial = sb.toString();
        count.put(serial, count.getOrDefault(serial, 0) + 1);
        if (count.get(serial) == 2)
            ans.add(node);
        return serial;
    }
}