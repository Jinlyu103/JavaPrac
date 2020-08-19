package uniqueBinarySearchTree;

import tree.TreeNode;

import java.util.*;

public class Solution {
    /**
     * 96. 不同的二叉搜索树
     * 给定一个整数n, 求以1，...，n为节点组成的二叉搜索树有多少种
     * 特点：中序遍历为升序序列
     */
    public int numTrees(int n){
        /**
         * 动态规划：dp[i]表示1，。。。i组成的二叉搜索树的个数
         * 分析：
         *  以k为根节点的二叉搜索树，那么1,...,k-1构建左子树，k+1,...,n构建右子树
         *  因此以k为根节点的二叉搜索树的个数为：
         *          左子树的个数 * 右子树的个数
         *  计算不同k的情况下，二叉搜索树的个数，并累加
         *  解：时间复杂度O(n^2)
         *      1...i一共i个节点构建二叉搜索树，去除根节点，剩下i-1个节点构建左右子树：
         *      左子树节点个数（0），右子树节点个数(i-1)
         *      ...
         *      左子树节点个数（i-1)，右子树节点个数0
         *      累加起来就是：
         *          dp[i] = dp[0]*dp[i-1] + ... +dp[i-1] * dp[0]
         *       初始化：
         *       dp[0] = 1空节点的情况是1种
         *       dp[1] = 1只有一个节点的情况也是1种
         */
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            for (int j = 0; j < i; j++){
                dp[i] += dp[j]*dp[i-1-j];
            }
        }
        return dp[n];
    }

    /**
     * 96. 不同的二叉搜索树II
     * 给定一个整数n，生成所有由1...n为节点所组成的二叉搜索树
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n){
        /**
         * 递归
         */
        if(n==0){
            return new LinkedList<TreeNode>();
        }
        return genTrees(1,n);
    }

    private List<TreeNode> genTrees(int start, int end){
        List<TreeNode> allTrees = new LinkedList<>(); //用于存放生成的二叉树
        if (start>end){
            allTrees.add(null);
            return allTrees;
        }

        //枚举所有可能的根节点
        for (int i = start; i<=end; i++){
            //递归求解所有可能的左子树
            List<TreeNode> leftTrees = genTrees(start, i-1);
            //递归求解所有可能的右子树
            List<TreeNode> rightTrees = genTrees(i+1,end);
            for (TreeNode left: leftTrees) {
                for (TreeNode right: rightTrees) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = left;
                    cur.right = right;
                    allTrees.add(cur);
                }
            }
        }
        return allTrees;
    }

    public static void main(String[] args) {
        int n = 3;
        Solution sol = new Solution();
        System.out.println(sol.generateTrees(n));
    }
}
