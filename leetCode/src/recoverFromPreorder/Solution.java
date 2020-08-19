package recoverFromPreorder;

import java.util.*;

public class Solution {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){ val = x;}
    }

    public TreeNode recoverFromPreorder(String S) {
        /**
         * 从先序遍历还原二叉树
         * 给定一棵树的先序遍历序列字符串，遍历的每个节点处，输出D条短线（S是该节点的深度）
         * 根节点的深度为0
         * 如果节点只有一个子节点，那么保证该子节点为左子节点
         * 用栈来做
         */
        if (S == null || S.length() == 0){
            return null;
        }
        //TreeNode root = new TreeNode((int)S.charAt(0));
        int pos = 0;
        LinkedList<TreeNode> stk = new LinkedList<>();
        while (pos < S.length()){
            int dep = 0;
            while (pos < S.length() && S.charAt(pos) == '-'){
                pos ++;
                dep ++; //下一个节点的深度
            }
            int start = pos; //记录下一个节点值起始位置
            while (pos < S.length() && S.charAt(pos) != '-'){
                pos ++;
            }
            int val = Integer.parseInt(S.substring(start, pos)); //下一个节点的值
            TreeNode node = new TreeNode(val); //创建下一个节点
            if (stk.isEmpty()){ //下一个节点为root节点，入栈，不用找父亲
                stk.addLast(node);
                continue;
            }
            while (stk.size() > dep){ //栈顶节点不是父亲节点，出栈，直到栈顶节点为父亲节点
                stk.removeLast();
            }
            if (stk.peekLast().left != null){
                stk.peekLast().right = node; //下一个节点为栈顶父亲节点的右子节点
            } else {
                stk.peekLast().left = node;
            }
            stk.addLast(node); //每一个节点都要入栈一次
        }
        return stk.get(0); //栈底元素即为根节点
    }

    public static void main(String[] args) {
        String s = "1-2--4--3-5--6--7";
        Solution sol = new Solution();
        TreeNode t = sol.recoverFromPreorder(s);
        TreeNode cur = t;
        LinkedList<TreeNode> stk = new LinkedList<>();
        while (cur != null || !stk.isEmpty()){
            if (cur != null){
                System.out.println(cur.val);
                stk.addLast(cur);
                cur = cur.left;
            } else {
                cur = stk.removeLast();
                cur = cur.right;
            }
        }
    }
}
