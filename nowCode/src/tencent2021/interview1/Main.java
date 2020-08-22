package tencent2021.interview1;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //腾讯测开一面手撕代码题

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int root = sc.nextInt();

        //构建二叉树
        TreeNode r = new TreeNode(root);
        for (int i = 0; i<n; i++){
            int fa = sc.nextInt();
            int lch = sc.nextInt();
            int rch = sc.nextInt();

            TreeNode f = findFather(r, fa); //根据fa在已经构建的二叉树中寻找父亲节点

            if (lch != 0){
                f.left = new TreeNode(lch);
            }
            if (rch != 0){
                f.right = new TreeNode(rch);
            }
        }

        int node = sc.nextInt();
        List<Integer> inOrderList = new ArrayList<>(); //中序遍历序列
        inorder(r, inOrderList); //中序遍历二叉树
        int idx = inOrderList.indexOf(node);
        if (idx == inOrderList.size()-1 || idx == -1){ //node如果不在二叉树中，就会返回-1
            System.out.println(0);
        } else { //输出后继节点
            System.out.println(inOrderList.get(idx+1));
        }
    }

    public static TreeNode findFather(TreeNode r, int val){
        if (r == null){
            return null;
        }
        if (r.val == val){
            return r;
        }
        TreeNode le = findFather(r.left,val);
        if (le != null){
            return le;
        }
        TreeNode ri = findFather(r.right, val);
        return ri;
    }

    public static void inorder(TreeNode r, List<Integer> list){
        if (r != null){
            inorder(r.left, list);
            list.add(r.val);
            inorder(r.right, list);
        }
    }
}
