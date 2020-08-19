package removeDuplicateNodes;

import java.util.*;

public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }

    public static ListNode removeDuplicateNodes(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        //List<Integer> vList = new ArrayList<Integer>();
        Map<Integer,Integer> vli = new HashMap<>();
        //vList.add(head.val);
        vli.put(head.val,1);
        ListNode cur = head.next;
        ListNode pre = head;
        while (cur!=null){
            if (!vli.containsKey(cur.val)){
                vli.put(cur.val,1);
                pre.next = cur;
                pre = pre.next;
            }
            cur = cur.next;
        }
        pre.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        ListNode cur = l;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(1);

        System.out.println(removeDuplicateNodes(l).val);
    }
}
