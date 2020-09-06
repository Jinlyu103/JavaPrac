package baidu.t2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main1 {
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

        //题目中允许重复移动到某一个格子
        //每次都走相邻计分最小的格子
        int x = 0, y = 0;
        //int s = 0;
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};
        while (x!=N-1 && y!=N-1){
            int t_x = x ;
            int t_y = y ;
            int dis = 10;
            for (int i = 0; i<4; i++){
                int nxt_x = x + dx[i];
                int nxt_y = y + dy[i];
                if (nxt_x < 0 || nxt_y < 0 || nxt_x >= N || nxt_y >= N){
                    continue;
                }
                //如果nxt_x,nxt_y没有越界，计算到相邻的位置的值
                if (Math.abs(board[nxt_x][nxt_y] - board[x][y]) <= dis){
                    dis = Math.abs(board[nxt_x][nxt_y] - board[x][y]);
                    t_x = nxt_x;
                    t_y = nxt_y;
                }
            }
            //更新相邻位置
            dp[t_x][t_y] = dp[x][y] + dis;
            x = t_x;
            y = t_y;
        }
        System.out.println(dp[N-1][N-1]);
    }
}
