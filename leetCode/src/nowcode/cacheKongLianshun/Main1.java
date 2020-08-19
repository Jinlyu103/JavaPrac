package nowcode.cacheKongLianshun;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), D = sc.nextInt();
        if (N<=2){
            System.out.println(-1);
            return;
        }
        int[] buildings = new int[N];
        for (int i=0;i<N;i++){
            buildings[i] = sc.nextInt();
        }
        int left = 0,right = 2;
        long res = 0;
        int mod = 99997867;

        while (left<N-2){
            while (right < N && buildings[right] - buildings[left] <= D){
                right ++;
            }
            if (right-1-left >=2){
                long cnt = right-1-left;
                res += cnt * (cnt-1)/2;
            }
            left ++;
        }
        res %= mod;
        System.out.println(res);
    }
}