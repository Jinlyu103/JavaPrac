package huaweiPrac.hj24_chorus;

import java.util.Scanner;

public class Main {
    /**
     * 合唱队
     * N位同学站成一排，音乐老师要请其中的N-K位同学出列，使得剩下的K位同学排成合唱队形
     * 合唱队形：
     * 设K位同学从左到右编号依次为1,2，。。。，K，身高分别为T1,T2,...,Tk，使得他们的身高满足：
     * 存在i(1<=i<=k) 使得T1<T2<...<Ti-1< Ti > Ti+1 >...>Tk
     * 计算虽少出列多少位同学，使得剩下的同学拍成合唱队形
     *
     * 动态规划：
     * dp[j][0]表示第j个人留下，左边的人数（加上自己）：
     *      计算左边的人数，需要从左往右遍历，左边只留下身高比自己矮的
     * dp[j][1]表示第j个人留下，右边的人数（加上自己）：
     *      计算右边的人数，需要从右往左遍历，右边只留下身高比自己矮的
     * 那么第j个人留下的话，留在队列里的总人数就是：
     *      dp[j][0] + dp[j][1]-1
     *      离开的人就是：N - (dp[j][0] + dp[j][1]-1)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int N = sc.nextInt();
            int[] T = new int[N];
            int maxStay = 0; //留下的人最多
            for (int i = 0; i<N; i++){
                T[i] = sc.nextInt();
            }
            int[][] dp = new int[N][2];
            //从左往右遍历,每个人左边出现的最多的人
            for (int i = 0; i<N; i++){
                if (i == 0){
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = 1;
                    for (int j = 0; j<i; j++){ //j为i左边的人
                        if (T[j] < T[i] && dp[j][0]+1>dp[i][0]){
                            //当第j个人的身高比第i个人矮，并且第j个人留下，
                            // 然后第i个人留下时，留下的人是否比之前计算得到的dp[i][0]大
                            dp[i][0] = dp[j][0] + 1;
                        }
                    }
                }
            }
            //从右往左遍历，每个人右边出现最多的人
            for (int i = N-1; i>=0; i--){
                if (i == N-1){
                    dp[i][1] = 1;
                } else {
                    dp[i][1] = 1;
                    for (int j = N-1; j>i; j--){
                        if (T[j] < T[i] && dp[j][1]+1 > dp[i][1]){
                            dp[i][1] = dp[j][1] + 1;
                        }
                    }
                }
            }
            //遍历dp数组
            for(int i = 0; i<N; i++){
                maxStay = Math.max(maxStay, dp[i][0]+dp[i][1]);
                System.out.println("左边："+dp[i][0] +", 右边： "+dp[i][1]);
            }
            System.out.println(N-maxStay+1);
        }
    }

}
