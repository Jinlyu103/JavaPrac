package flatten;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    //给定一个二叉树，原地将其展开为一个单链表
    //前序遍历
    public void flatten(TreeNode root){
        if (root == null){
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        preOrder(root, list); //前序遍历二叉树，结果存放在list中
        root = list.get(0); //第一个节点为根节点
        TreeNode cur = root;
        for (int i = 1; i<list.size(); i++){
            cur.left = null;
            cur.right = list.get(i);
            cur = cur.right;
        }
    }

    private void preOrder(TreeNode root, List<TreeNode> list){
        if (root == null){
            return;
        }
        list.add(root);
        preOrder(root.left, list);
        preOrder(root.right,list);
    }
}
