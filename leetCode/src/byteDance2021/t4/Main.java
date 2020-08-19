package byteDance2021.t4;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        //输入n个正整数
        for (int i = 0; i<n; i++){
            a[i] = sc.nextInt();
        }

        Arrays.sort(a); //排序
        int[] pre = new int[n+1]; //计算前缀和, 经过排序之后，前缀和数组递增
        pre[0] =0;
        for (int i = 1; i<n+1; i++){
            pre[i] = pre[i-1] + a[i-1];
        }

        //k = n时，和%m 的值最大
        if (pre[n] < m){
            System.out.println(pre[n]);
        } else {
            int res = 0;
            //任选k个数求和sum，并计算%m的值最大是多少
            for (int k = 0; k<=n; k++){
                int L = 0;
                int R = k;
                while (L<=R && R<=n && pre[R]-pre[L] >= m){
                    res = Math.max(res, (pre[R] - pre[L]) %m);
                    L++;
                    R++;
                }
                if (L<=R && R<=n && pre[R] - pre[L]<m){
                    res = Math.max(res, (pre[R] - pre[L])%m);
                }
            }
            System.out.println(res);
        }
    }
}
