package rob;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    /**
     * 打家劫舍III
     * 二叉树房屋
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x){ val = x;}
    }
    public int rob(TreeNode root){
        int ans = 0;
        ans = Math.max(dp(root,0), dp(root,1));
        return ans;
    }
    private int dp(TreeNode r, int flag){
        if (r == null){
            return 0;
        }
        if(flag == 0){
            int ans_L = Math.max(dp(r.left,0), dp(r.left, 1));
            int ans_R = Math.max(dp(r.right,0), dp(r.right, 1));
            return ans_L + ans_R;
        }else{
            return r.val + dp(r.left, 0) + dp(r.right, 0);
        }
    }

    //记忆化优化
    public int rob2(TreeNode root){
        Map<TreeNode, Integer> memory = new HashMap<>(); //记录偷某个节点所能得到的最大金额
        return robInternal(root, memory);
    }

    private int robInternal(TreeNode root, Map<TreeNode,Integer> memo){
        if (root ==null){
            return 0;
        }
        if (memo.containsKey(root))
            return memo.get(root);

        int money = root.val;
        if (root.left != null){//左孩子不能偷，但是左孩子的后代可以偷
            money += robInternal(root.left.left, memo) + robInternal(root.left.right, memo);
        }
        if (root.right != null){
            money += robInternal(root.right.left, memo) + robInternal(root.right.right, memo);
        }
        money = Math.max(money, robInternal(root.left, memo)+robInternal(root.right, memo));
        memo.put(root, money);
        return money;
    }

    //继续优化
    public int rob3(TreeNode root){
        /**
         * 使用一个大小为2的数组表示节点偷或者不偷，0表示不偷，1表示偷
         * 任何一个节点能偷到最大钱的状态可定义：
         *      若当前节点不偷：那么当前节点能偷到的钱数 = 左孩子能偷到的钱+右孩子能偷到的钱。(左、右孩子也可以选择不偷)
         *      当前节点偷： 那么当前节点能偷到的钱数 = 左孩子选择不偷时能得到的钱+右孩子选择不偷时能得到的钱+当前节点的钱数
         * root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) + Math.max(rob(root.right)[0], rob(root.right)[1])
         * root[1] = rob(root.left)[0] + rob(root.right)[0] + val
         */
        int[] rootRes = robInternal1(root);
        return Math.max(rootRes[0], rootRes[1]);
    }

    private int[] robInternal1(TreeNode root){
        int[] res = new int[2];
        if (root == null){
            return res;
        }
        int[] left = robInternal1(root.left);
        int[] right= robInternal1(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + root.val;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

    }
}
