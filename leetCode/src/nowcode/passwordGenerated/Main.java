package nowcode.passwordGenerated;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * 密码生成器会进行M轮计算，每轮计算，小汪会输入两个数L,R(L<=R),
         * 密码生成器会将这两个数作为下标，将两个下标之间（包含）的所有槽位赋值为i（i为当前的轮次，i∈[1,M]）
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] a = new int[N];
        int L, R;
        int ans = 0;
        int mod = 100000009;


        for (int i = 0; i < M; i++){
            L = sc.nextInt();
            R = sc.nextInt();
            for (int j = L; j<= R; j++){
                a[j] = i+1;
            }
        }

        for (int i = 0; i<N; i++){
            ans = (ans + i*a[i]) % mod;
        }

        System.out.println(ans%mod);
    }
}
