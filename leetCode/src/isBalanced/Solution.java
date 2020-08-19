package isBalanced;

import tree.TreeNode;

public class Solution {
    /**
     * 判断二叉树是否是高度平衡
     * 自顶向下
     */
    public boolean isBalanced(TreeNode root){
        if (root == null){
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = height(root.left) + 1;
        int right = height(root.right) + 1;

        if (Math.abs(left-right) > 1 || height(root.left) == -1 || height(root.right) == -1){
            return -1;
        }

        return Math.max(left, right) +1;
    }

    //自底向上
    public boolean isBalanced1(TreeNode root){
        return height1(root) >= 0;
    }

    public int height1(TreeNode root){
        if (root == null){
            return 0;
        }
        int le = height1(root.left); //先求左右子树的高度
        int ri = height1(root.right);

        //左右子树不是高度平衡的二叉树，或者左右子树的高度差大于1，那么以root为根节点的树不是高度平衡的二叉树
        if (le == -1 || ri == -1 || Math.abs(le - ri) > 1){
            return -1;
        } else {
            return Math.max(le,ri) + 1; //返回树的高度
        }
    }
}
