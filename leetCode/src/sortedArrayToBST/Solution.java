package sortedArrayToBST;

public class Solution {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x;}
    }

    public TreeNode sortedArrayToBST(int[] nums){
        /**
         * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
         *
         * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
         * 二叉搜索树的性质：中序遍历为升序序列
         * 高度平衡：左右子树高度差，那么在递归选择根节点的时候尽量选择中间节点作为根节点
         */
        return buildBST(nums,0,nums.length-1);
    }

    private TreeNode buildBST(int[] nums, int left, int right){
        if (left > right){
            return null;
        }
        //选择中间节点右边的节点作为根节点
        int rIdx = (left+right+1)/2;
        TreeNode root = new TreeNode(nums[rIdx]);
        root.left = buildBST(nums, left, rIdx-1);
        root.right = buildBST(nums,rIdx+1,right);
        return root;

    }
}
