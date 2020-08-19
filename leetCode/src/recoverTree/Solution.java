package recoverTree;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {
    /**
     * 99. 恢复二叉搜索树
     * 中序遍历为升序序列
     * 1、找到二叉搜索树中序遍历得到值序列的不满足条件的位置
     * 2、若有两个，记为i，j(i<j, ai > a(i+1) && aj > a(j+1)
     *      那么需要交换的节点为a[i]和a[j+1]
     * 3、若有一个，记为i，那么对应被错误交换的节点记为ai和a(i+1)
     * 4、交换这两个节点
     */
    public void recoverTree(TreeNode root){
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int[] swapped = findTwoSwapped(nums);  //寻找中序遍历值序列中不满足条件的值
        recover(root,2,swapped[0],swapped[1]); //交换两个节点
    }

    private void inorder(TreeNode root, List<Integer> nums){
        if (root == null){
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    /**
     * 非递归中序遍历
     * @param root
     * @param nums
     */
    private void inorderNoRec(TreeNode root, List<Integer> nums){
        TreeNode pre = null;
        Stack<TreeNode> stk = new Stack<>();
        while (root != null || !stk.isEmpty()){
            while (root!=null){
                stk.push(root);
                root = root.left;
            }
            root = stk.pop(); //先访问最左节点，即最后一个入栈的左叶子节点
            nums.add(root.val);
            root = root.right;
        }
    }

    /**
     * 寻找值序列中被交换的位置
     * 如：
     * 1 3 2 4 5 6 7
     * 1 6 3 4 5 2 7
     * @param nums
     * @return
     */
    private int[] findTwoSwapped(List<Integer> nums){
        int[] res = new int[2];
        Arrays.fill(res,-1);
        for (int i = 0; i< nums.size()-1; i++){
            if (nums.get(i) < nums.get(i+1)){
                continue;
            }
            if (res[0]==-1){
                res[0] = nums.get(i);
                res[1] = nums.get(i+1);
            } else {
                res[1] = nums.get(i+1);
            }
        }
        return res;
    }

    private void recover(TreeNode root, int n, int x, int y){
        if (root != null){
            if (root.val==x || root.val == y){
                root.val = root.val==x?y:x;
                if (--n == 0){
                    return;
                }
            }
            recover(root.right, n, x, y);
            recover(root.left, n, x, y);
        }
    }
}
