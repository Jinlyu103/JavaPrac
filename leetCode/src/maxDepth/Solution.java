package maxDepth;

import tree.TreeNode;

public class Solution {
    /**
     * 104 给定一棵二叉树，找最大深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root){
        //递归
        if (root == null){
            return 0;
        }
        int maxD = 0;
        maxD = Math.max(maxD, maxDepth(root.left)+1);
        maxD = Math.max(maxD, maxDepth(root.right)+1);
        return maxD;
    }
}
