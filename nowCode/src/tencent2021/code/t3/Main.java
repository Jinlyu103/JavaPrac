package tencent2021.code.t3;

import java.util.Scanner;

public class Main {
    /**
     * 数字之和
     * 题意：给一个数n，把它拆成a+b=n，要求a和b的数位和最大，求这个数位和
     * 思路：
     * 贪心方法：假设n是x位数，可以让a成为x-1个9组成的数，b = n-a
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i<T; i++) {
            int n = sc.nextInt();
            //AC思路
            int a = 0;
            while (a<=n){
                a = a*10 + 9;
            }
            a = a/10;
            System.out.println(s(a) + s(n-a));
//            int maxV = 0;
//            for (int j = 1; j<n; j++){
//                maxV = Math.max(maxV, s(j)+s(n-j));
//            }
//            System.out.println(maxV);
        }
    }

    //数a的各位之和
    public static int s(int a){
        int res = 0;
        while (a > 0){
            res += a%10;
            a = a/10;
        }
        return res;
    }
}
