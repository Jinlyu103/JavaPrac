package symmetricBinaryTree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }
    public boolean isSymmetric(TreeNode root){
        if (root == null){
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right){
        //递归判断左右子树是否为镜像对称
        if (left == null && right == null){
            return true;
        } else if((left == null && right != null) || (left!=null && right == null)){
            return false;
        } else if (left.val == right.val){
            return (isMirror(left.left, right.right) && isMirror(left.right, right.left));
        } else{
            return false;
        }
    }

    private boolean isMirror1(TreeNode left, TreeNode right){
        //迭代:二叉树层序遍历
        boolean flag = true;
        if (left == null && right == null){
            return true;
        }
        if ((left == null && right !=null) || (left!=null && right == null)){
            return false;
        }
        LinkedList<TreeNode> leftList = new LinkedList<TreeNode>();
        LinkedList<TreeNode> rightList = new LinkedList<TreeNode>();
        leftList.addLast(left);
        rightList.addLast(right);
        while (!leftList.isEmpty() && !rightList.isEmpty()){
            TreeNode cur_L = leftList.removeFirst();
            TreeNode cur_R = rightList.removeFirst();
            if (cur_L.val != cur_R.val){
                flag = false;
                break;
            }
            if ((cur_L.left == null && cur_R.right != null) || (cur_L.left!=null && cur_R.right == null)){
                flag = false;
                break;
            }else if((cur_L.right == null && cur_R.left != null) || (cur_L.right!=null && cur_R.left == null)){
                flag = false;
                break;
            }
            if (cur_L.left != null && cur_R.right != null){
                leftList.addLast(cur_L.left);
                rightList.addLast(cur_R.right);
            }
            if(cur_L.right != null && cur_R.left != null){
                leftList.addLast(cur_L.right);
                rightList.addLast(cur_R.left);
            }
        }
        return flag;
    }
}
