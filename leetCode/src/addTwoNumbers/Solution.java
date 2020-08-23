package addTwoNumbers;
class ListNode{
    int val;
    ListNode next;
    ListNode(int x){ val = x;}
}

public class Solution {
    /**
     * 两数相加
     * 给两个非空的链表来表示两个非负的整数。其中，他们各自的位数是按照逆序的方式存储的，并且他们的每一个节点只能存储一位数字
     * 如果将这两个数相加起来，则会返回一个信道链表来表示它们的和
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        int carry = 0;
        ListNode head = l1;
        ListNode cur = head;
        while (l1!=null && l2!=null){
            int res = l1.val + l2.val + carry;
            cur.val = res%10; //更新当前节点的值
            carry = res/10; //进位
            if (cur.next != null)
                cur = cur.next;

            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null){
            int res = l1.val + carry;
            cur.val = res%10;
            carry = res / 10;

            if (cur.next !=null)
                cur = cur.next;
            l1 = l1.next;
        }

        while (l2 != null){
            int res = l2.val + carry;
            cur.next = new ListNode(res%10);
            carry = res/10;

            cur = cur.next;
            l2 = l2.next;
        }

        if (carry > 0){
            cur.next = new ListNode(carry);
        }
        return head;
    }

    /**
     * 递归的思路求解
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNums(ListNode l1, ListNode l2){
        return add(l1, l2, 0); //进位为0
    }

    public ListNode add(ListNode l1, ListNode l2, int carry){
        if (l1 == null && l2 == null){
            if (carry > 0){
                return new ListNode(carry);
            } else return null;
        }
        //如果l1为空，l1指向一个值为0 的节点
        if (l1 == null){
            l1 = new ListNode(0);
        }
        if (l2 == null){
            l2 = new ListNode(0);
        }
        int res = l1.val + l2.val + carry;
        carry = res / 10;
        l1.val = res%10;
        l1.next = add(l1.next, l2.next, carry);
        return l1;

    }
}
