package levelOrderBottom;

import tree.TreeNode;

import java.util.*;

public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        if (root.left == null && root.right == null){
            List<Integer> tmp = new ArrayList<>();
            tmp.add(root.val);
            res.add(tmp);
            return res;
        }

        q.offer(root);
        levelOrder(q, res);
        Collections.reverse(res);
        return res;
    }

    public void levelOrder(Queue<TreeNode> q, List<List<Integer>> res){
        List<Integer> thisLev = new ArrayList<>();
        Queue<TreeNode> nxtLevel = new LinkedList<>();
        while (!q.isEmpty()){
            TreeNode cur = q.poll();
            thisLev.add(cur.val);
            if (cur.left!=null){
                nxtLevel.offer(cur.left);
            }
            if (cur.right!=null){
                nxtLevel.offer(cur.right);
            }
        }
        res.add(thisLev);
        if (nxtLevel.isEmpty()){
            return;
        } else {
            levelOrder(nxtLevel, res);
        }
    }
}
