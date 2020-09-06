package binaryTreePaths;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> binaryTreePaths(TreeNode root){
        List<String> res = new ArrayList<>();

        if (root == null){
            return res;
        }
        inorder(root, res, "");
        return res;
    }

    public void inorder(TreeNode r, List<String> res, String s){
        if (r.left !=null)
            inorder(r.left, res, s+ r.val + "->");
        if (r.right!=null){
            inorder(r.right, res, s+ r.val + "->");
        }
        if (r.left == null && r.right == null){
            res.add(s + r.val);
        }
//        if (r != null){
//            String h = r.val+"";
//            if (r.left == null && r.right == null){ //叶子节点
//                StringBuffer strBuf = new StringBuffer();
//                for (int i = 0; i<s.length(); i++){
//                    strBuf.append(s.charAt(i));
//                }
//                strBuf.append(r.val);
//                res.add(strBuf.toString());
//            }
//            if (r.left!=null){
//                inorder(r.left, res,s+h+"->");
//            }
//            if (r.right!=null){
//                inorder(r.right, res, s+h+"->");
//            }
//        }
    }
}
