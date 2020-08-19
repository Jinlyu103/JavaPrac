package serializeBinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Codec {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x){ val = x;}
    }

    //将二叉树编码为一个单独的字符串（序列化
    public String serialize(TreeNode root){
        /**
         * DFS，树的先序遍历,利用栈
         */
        StringBuffer str = new StringBuffer();
        //利用辅助栈进行深度优先搜索
        LinkedList<TreeNode> stk = new LinkedList<TreeNode>();
        if (root == null){
            return "null";
        }
        TreeNode cur = root;
        while (cur != null || !stk.isEmpty()){
            if (cur != null){
                str.append(cur.val+ ",");
                //res.add(cur.val);
                stk.addLast(cur);
                cur = cur.left;
            } else {
                //res.add(null);
                str.append("null" + ",");
                cur = stk.removeLast();
                cur = cur.right;
            }
        }
        return (str.toString()).substring(0, str.length()-1);
    }

    //将编码数据解码为二叉树（反序列化）
    public TreeNode deserialize(String data){
        String[] arr = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(arr));
        //递归
        return rdeserialize(dataList);
    }

    private TreeNode rdeserialize(List<String> l){
        if (l == null || l.size() == 0){
            return null;
        }
        if ("null".equals(l.get(0))){
            l.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(l.remove(0)));
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);
        return root;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(5);
        t.left.left = new TreeNode(3);
        t.left.right = new TreeNode(4);
        Codec sol = new Codec();
        System.out.println(sol.serialize(t));
        TreeNode r = sol.deserialize(sol.serialize(t));
        System.out.println(r.val);
    }
}
