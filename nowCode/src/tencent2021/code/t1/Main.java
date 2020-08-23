package tencent2021.code.t1;

import java.util.*;

public class Main {
    /**
     * 删除节点
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //节点数
        int k = sc.nextInt(); //删除第k个节点
        int[] a = new int[n];
        for (int i = 0; i<n; i++){
            int tmp = sc.nextInt();
            a[i] = tmp;
        }
        //输出a, 不输出s[k-1]
        StringBuffer res = new StringBuffer();
        for (int i = 0; i<n; i++){
            if (i == k-1){
                continue;
            }
            res.append(a[i] + " ");
        }
        System.out.println(res.toString());
    }
}

class ListNode{
    public int val;
    public ListNode next;
    public ListNode(){}
    public ListNode(int x){
        val = x;
    }
}
