package maxProfit;

public class Solution {
    public int maxProfit(int[] prices){
        /**
         * 309. 最佳买卖股票时机，含冷冻期
         * 动态规划：
         * dp[i][0]:第i天不持有股票的最大利润
         * dp[i][1]:第i天持有股票的最大利润
         * 初始化：
         * dp[0][0] = 0
         * dp[0][1] = -p[0]
         * 转移方程：
         * dp[i][0] = max(dp[i-1][0], dp[i-1][1]+p[i])
         * dp[i][1] = max(dp[i-1][1], dp[i-2][0]-p[i])
         */
        int n = prices.length;
        if (n==0 || n==1)
            return 0;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[Math.max(i-2,0)][0] - prices[i]);
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

    public static void main(String[] args) {
        int[] p = {1,2,3,0,2};
        Solution sol = new Solution();
        System.out.println(sol.maxProfit(p));
    }
}
