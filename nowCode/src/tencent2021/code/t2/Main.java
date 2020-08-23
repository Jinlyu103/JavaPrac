package tencent2021.code.t2;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    /**
     * 第k小子串
     * 题意：给一个长度为n的字符串，问字典序第k（k在5以内）大的子串是什么
     * 解析：
     * k在5以内，那答案的长度也不会超过5
     * 将长度为5以内的子串抠出来排序
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int k = sc.nextInt();
        TreeSet<String> set = new TreeSet<>(); //treeSet元素自动有序

        // a, aa, aab, aaba, aabaa, ab, aba, abaa, b, ba, baa

        int n = s.length();
        for (int i = 0; i<n; i++){
            StringBuffer tmp = new StringBuffer();
            for (int j = 0; j<5; j++){
                if (i+j<n){
                    tmp.append(s.charAt(i+j));
                    set.add(tmp.toString());
                }
            }
        }
        int i = 0; //输出第k大的字符串，也就是第set.size() - k 个
        for (String str: set){
            if (i<set.size()-k){
                i++;
                continue;
            } else {
                System.out.println(str);
                break;
            }
        }
    }
}
