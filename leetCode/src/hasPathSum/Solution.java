package hasPathSum;

import tree.TreeNode;
import java.util.LinkedList;

public class Solution {
    public boolean hasPathSum1(TreeNode root, int sum){
        //DFS。应该用队列，而不是栈
        boolean ans = false;
        int pathSum = 0;
        if (root == null)
            return false;
        TreeNode cur = root;
        LinkedList<TreeNode> stk = new LinkedList<>();
        stk.addLast(root);
        //深度优先搜索
        while (!stk.isEmpty()){
            cur = stk.removeLast();
            pathSum += cur.val;
            if (cur.left == null && cur.right == null){ // 如果是叶子节点
                if (pathSum == sum){
                    ans = true;
                    break;
                } else { //减去该叶子节点值
                    pathSum -= cur.val;
                }
            } else { //不是叶子节点
                if (cur.left != null)
                    stk.addLast(cur.left);
                if (cur.right != null)
                    stk.addLast(cur.right);
            }
        }
        return ans;
    }

    public boolean hasPathSum(TreeNode root, int sum){
        //递归
        if (root == null){
            return false;
        }
        int pathSum = 0;
        return isPathSumEqual(root, pathSum, sum);
    }

    private boolean isPathSumEqual(TreeNode root, int p, int sum){
        if (root == null){
            return false;
        }
        p += root.val;
        if (root.left == null && root.right == null){
            return p == sum;
        }
        return isPathSumEqual(root.left, p, sum) || isPathSumEqual(root.right, p, sum);
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2);
        int sum = 1;
        Solution sol = new Solution();
        System.out.println(sol.hasPathSum(r,sum));
    }
}
