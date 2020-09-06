package baidu.t2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //单组输入
        int N = sc.nextInt();
        int[][] board = new int[N][N];
        //输入棋盘
        for (int i = 0; i<N; i++){
            for (int j = 0; j<N; j++){
                board[i][j] = sc.nextInt();
            }
        }

        int[][] dp = new int[N][N]; //dp[i][j] 表示走到（i,j）的最小计分
        for (int i = 0; i<N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        //初始化
        dp[0][0] = 0; //在原地时计分为0
        //初始化第一列
        for (int i = 1; i<N; i++){
            dp[i][0] = Math.min(dp[i][0], dp[i-1][0] + Math.abs(board[i-1][0] - board[i][0]));
        }
        //初始化第一行
        for (int i = 1; i<N; i++){
            dp[0][i] = Math.min(dp[0][i], dp[0][i-1] + Math.abs(board[0][i-1] - board[0][i]));
        }

        //题目中允许重复移动到某一个格子
        //我先不考虑这种情况
        for (int i = 1; i<N; i++){
            for (int j = 1; j<N; j++){
                int from_left = Math.abs(board[i][j] - board[i][j-1]) + dp[i][j-1];
                int from_up= Math.abs(board[i][j] - board[i-1][j]) + dp[i-1][j];
                dp[i][j] = Math.min(dp[i][j], Math.min(from_left, from_up));
            }
        }
        System.out.println(dp[N-1][N-1]);
    }
}
