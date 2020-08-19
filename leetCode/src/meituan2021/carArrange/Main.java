package meituan2021.carArrange;

import java.util.Scanner;

public class Main {
    /**
     * A：要租a辆汽车
     * B：要租b辆汽车
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        int[][] M = new int[n][2]; //输入利润
        for (int i = 0; i<n; i++){
            M[i][0] = sc.nextInt();
            M[i][1] = sc.nextInt();
        }
        /**
         * 动态规划
         * dp[i][k][t]：表示总共有i辆车，A地最多调度k辆车，b地最多调度t辆车
         */
         int[][][] dp = new int[n+1][a+1][b+1];
         for (int i = 1; i<n+1; i++){
             for (int k = 0; k<a+1; k++){
                 for (int t = 0; t<b+1; t++){
                     if (k == 0 && t!=0) {
                         //A地没有需求
                         dp[i][k][t] = Math.max(dp[i][k][t-1] + M[i-1][1], dp[i-1][k][t]);
                     } else if (k!=0 && t==0){
                         //B地没有需求
                         dp[i][k][t] = Math.max(dp[i][k-1][t] +M[i-1][0], dp[i-1][k][t]);
                     } else if (k==0 && t==0){
                         dp[i][k][t] = 0;
                     } else {
                         dp[i][k][t] = Math.max(dp[i-1][k-1][t] + M[i-1][0], dp[i-1][k][t-1] + M[i-1][1]);
                         dp[i][k][t] = Math.max(dp[i-1][k][t] , dp[i][k][t]);
                     }
                 }
             }
         }
        System.out.println(dp[n][a][b]);
    }
}
