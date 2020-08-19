package uniquePathsWithObstacles;

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        /**
         * 不同路径
         * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
         *
         * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
         *
         * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
         *
         * DFS，动态规划：
         * dp[i][j]表示到达坐标为（i，j）路径数
         * dp[i][j] = dp[i-1][j] + dp[i][j-1] if (i,j)没有障碍物
         * dp[i][j] = 0 if (i,j)有障碍物
         */
        int m = obstacleGrid.length;
        if (m == 0){
            return 0;
        }
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //初始化第一行
        if (obstacleGrid[0][0] != 1)
            dp[0][0] = 1;
        for (int i = 1; i<m; i++){
            if (obstacleGrid[i][0] != 1)
                dp[i][0] = dp[i-1][0];
        }
        //初始化第一列
        for (int j = 1; j<n; j++){
            if (obstacleGrid[0][j] != 1)
                dp[0][j] = dp[0][j-1];
        }

        //DFS
        for (int i = 1; i < m ; i++) {
            for (int j = 1; j < n ; j++) {
                if (obstacleGrid[i][j] != 1){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
