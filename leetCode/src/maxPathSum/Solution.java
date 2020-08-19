package maxPathSum;

public class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int maxS = (-1)*(1<<20);

    public int maxPathSum(TreeNode root){
        /**
         * 给定一棵非空二叉树，返回最大路径和
         * 递归
         */
        calMaxPath(root);
        return maxS;
    }

    private int calMaxPath(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = Math.max(0,calMaxPath(root.left)); //计算左子树递归值
        int right = Math.max(0,calMaxPath(root.right));//计算右子树递归值
        //选择左中右,还是max(左,右)
        int res = Math.max(root.val + left + right, root.val + Math.max(left,right));

        //更新全局最大值
        maxS = Math.max(maxS, res);
        //返回经过root节点的最大路径和
        return (root.val + Math.max(left,right));
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode r = new TreeNode(-1);
        r.left = new TreeNode(-1);
        r.right = new TreeNode(-1);
        System.out.println(sol.maxPathSum(r));

    }

}
