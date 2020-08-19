package minimumTotal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * 120. 三角形最小路径和
     *      给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     *      相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle){
        /**
         * （i，j）的相邻节点：（i+1,j）和（i+1,j+1)
         * 动态规划：dp[n+1][n+1]
         * dp[i][j]表示从点（i，j)达到底边的最小路径和，采用自底向上的方式
         *
         * dp[i][j] = tr[i][j] + min(dp[i+1][j],dp[i+1][j+1])
         */
        int n = triangle.size();
        if (n == 0){
            return 0;
        }
        int[][] dp = new int[n+1][n+1];

        //从最后一行开始递推
        for (int i = n-1; i >=0 ; i--) {
            //上一行最多只能取到与下一行行数相等的列
            for (int j = 0; j<=i; j++){
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i+1][j], dp[i+1][j+1]);
            }
        }
        return dp[0][0];
    }

    public int minimumTotal1(List<List<Integer>> triangle){
        /**
         * （i，j）的相邻节点：（i+1,j）和（i+1,j+1)
         * 空间优化
         * 动态规划：dp[n+1]
         * dp[j]表示从点（i，j)达到底边的最小路径和，采用自底向上的方式
         *
         * dp[j] = tr[i][j] + min([j],dp[j+1])
         */
        int n = triangle.size();
        if (n == 0){
            return 0;
        }
        int[] dp = new int[n+1];

        //从最后一行开始递推
        for (int i = n-1; i >=0 ; i--) {
            //上一行最多只能取到与下一行行数相等的列
            for (int j = 0; j<=i; j++){
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }
        return dp[0];
    }
}
