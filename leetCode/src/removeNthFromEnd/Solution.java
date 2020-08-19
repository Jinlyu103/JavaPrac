package removeNthFromEnd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
    }
}

public class Solution {
    /**
     * 删除链表倒数第n个节点，返回链表头结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n){
        Map<Integer, ListNode> hp = new HashMap<>();
        int idx = 0;
        ListNode cur = head;
        //总共有idx个节点，最后一个节点为第idx-1个节点
        while (cur != null){
            hp.put(idx, cur);
            idx++;
            cur = cur.next;
        }
        if (idx < n){
            return head;
        }
        //倒数第n个节点：第idx-n
        ListNode delNode = hp.get(idx-n);
        if (delNode == head){
            head = head.next;
            return head;
        }
        //倒数第n+1个节点：第idx-n-1
        ListNode pre = hp.get(idx-n-1);
        pre.next = delNode.next;
        return head;
    }
}
