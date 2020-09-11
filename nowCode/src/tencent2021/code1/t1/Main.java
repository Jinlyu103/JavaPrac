package tencent2021.code1.t1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * 给定两个降序排序的链表，求出两个链表的公共部分
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int[] list1 = new int[n];
            for (int i = 0; i<n; i++){
                list1[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            int[] list2 = new int[m];
            for (int i = 0; i<m; i++){
                list2[i] = sc.nextInt();
            }

            List<Integer> res = new ArrayList<>();
            //双指针
            int i = 0; //指向链表1
            int j = 0; //指向链表2

            while (i<n && j < m){
                if (list1[i] == list2[j]){
                    res.add(list1[i]);
                    i++; j++;
                } else if (list1[i]>list2[j]){
                    i++;
                } else {
                    j++;
                }
            }

            //注意输出格式
            System.out.println(res);
        }
    }

}
