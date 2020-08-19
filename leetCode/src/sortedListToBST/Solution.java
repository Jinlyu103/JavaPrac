package sortedListToBST;

import tree.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    public TreeNode sortedListToBST(ListNode head){
        if (head == null){
            return null;
        }
        int n = 0;
        ListNode cur = head;
        Map<Integer, ListNode> map = new HashMap<>();
        while (cur != null){
            map.put(n, cur);
            n++;
            cur = cur.next;
        }
        int mid = n/2;
        ListNode r = map.get(mid); //根节点对应的listNode
        ListNode pre = map.get(mid - 1);
        if (pre != null){
            pre.next = null;
        } else {
            head = null;
        }

        TreeNode le = sortedListToBST(head); //递归生成左子树
        TreeNode ri = sortedListToBST(r.next); //递归生成右子树

        return new TreeNode(r.val, le, ri);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        ListNode h = new ListNode(-10);
        ListNode cur = h;
        cur.next = new ListNode(-3);
        cur = cur.next;
        cur.next = new ListNode(0);
        cur = cur.next;
        cur.next = new ListNode(5);
        cur = cur.next;
        cur.next = new ListNode(9);

        TreeNode r = sol.sortedListToBST(h);
    }
}
