import java.util.Collections;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
// 1. 递归
class Solution1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        postorder(root, res);
        return res;
    }

    public void postorder(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }
}

// 2. 迭代
class Solution2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                // 重新压入栈
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}

// 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
class Solution3 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        Collections.reverse(res);
        return res;
    }
}

// 3. Morris 后序遍历
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;
        TreeNode curr = root;
        while (curr != null) {
            // 有左子树
            if (curr.left != null) {
                predecessor = curr.left;
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                while (predecessor.right != null && predecessor.right != curr)
                    predecessor = predecessor.right;
                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = curr;
                    curr = curr.left;
                    continue;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    predecessor.right = null;
                    postMorris(curr.left, res);
                }
            }
            // 如果没有左子树，则直接访问右子树
            curr = curr.right;
        }
        postMorris(root, res);
        return res;
    }

    public void postMorris(TreeNode root, List<Integer> res) {
        // 反转链表
        TreeNode reverseNode = reverseList(root);
        // 遍历链表
        TreeNode cur = reverseNode;
        while (cur != null) {
            res.add(cur.val);
            cur = cur.right;
        }
        // 反转回来
        reverseList(reverseNode);
    }

    // 反转链表
    public TreeNode reverseList(TreeNode head) {
        TreeNode cur = head;
        TreeNode pre = null;
        while (cur != null) {
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}