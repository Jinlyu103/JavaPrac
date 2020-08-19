package calculateMinimumHP;

import java.util.Arrays;

public class Solution {
    public int calculateMinimumHP(int[][] dungeon){
        /**
         * 174. 地下城游戏
         * 骑士救公主。有一个M*N的二维网格，骑士初始在左上角，公主初始在右下角
         * 假设骑士初始健康值为H
         * 动态规划：
         * dp[i][j]: 骑士在（i,j)的健康值
         * dp[0][0] = H + dungeon[0][0] 初始
         * 顺序，骑士找公主，转移方程：
         *      dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + dungeon[i][j] //从上，或者从左
         * 反过来，返程，（m-1，n-1）到达（0,0）
         *      dp[m-1][n-1] = 0
         * 转移方程：
         *      1、从下返回，下个坐标减掉下个坐标 的健康值，可得出回到当前坐标的健康值
         *      dp[i][j] = min(dp[i+1][j] - dungeon[i+1][j], dp[i][j+1] - dungeon[i][j+1])
         */
        int m = dungeon.length;
        int n = dungeon[0].length;
        if (m == 1 && n == 1){
            return Math.max(1,1-dungeon[0][0]);
        }
        int[][] dp = new int[m][n];
        int max =1<<20; //初始的能量值
        for (int i = 0; i<m; i++)
            Arrays.fill(dp[i],max);
        dp[m-1][n-1] = 1;
        for (int i = m-1; i>=0; i--){
            for (int j = n-1; j >=0 ; j--) {
                if (i == m-1 && j == n-1){
                    continue;
                } else if (i==m-1){ //只能从右往左返回，减掉右边坐标的健康值
                    dp[i][j] = Math.min(dp[i][j], Math.max(1,dp[i][j+1] - dungeon[i][j+1]));
                } else if (j==n-1){ //只能从下往上返回，减掉下边坐标的健康值
                    dp[i][j] = Math.min(dp[i][j], Math.max(1,dp[i+1][j] - dungeon[i+1][j]));
                } else {
                    dp[i][j] = Math.min(Math.max(1,dp[i+1][j]-dungeon[i+1][j]), Math.max(1,dp[i][j+1]-dungeon[i][j+1]));
                }
            }
        }
        return Math.max(1,dp[0][0]-dungeon[0][0]);
    }

    public static void main(String[] args) {
        int[][] dungeon = {{5,23,-48,-21,-72,-62,-19,-55,-3,-93,-84,14,-34,46,-82,-46,39,26,47,-71,-46,-3,-59,-93,-13,-72,19,-43,-51,-2,-6,-53,12,-40,15,-94,37,-3,-32,-97}};
        Solution sol = new Solution();
        System.out.println(sol.calculateMinimumHP(dungeon));
    }
}
