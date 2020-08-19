package new21Game;

import java.util.Arrays;

public class Solution {
    public double new21Game(int N, int K, int W){
        /**
         * 新21点
         * 动态规划：dp[i]表示当前得分为i时，抽牌点数小于N的概率,当得分大于等于k的时候，抽牌停止
         * 初始化：dp大小：K+W+1:(可能得到的最大得分为K+W-1）
         *      当 k<=i<= N 且 i < K+W 时，dp[i] = 1
         *      当 i > N && i <K+W 时，dp[i] = 0
         *      当 i = k-1时， dp[i] = min(N-K+1,W) / W
         *
         * 状态转移方程：
         *      dp[i] = 抽到1获胜的概率 + 抽到2获胜的概率 + ... + 抽到W获胜的概率
         *            = dp[i+1]*1/W + dp[i+2]*1/W +...+ dp[i+W] * 1/W
         *            = (dp[i+1] +...+dp[i+W])/W
         *      为了方便，再写出dp[i+1]
         *      dp[i+1] = (dp[i+2] +...+dp[i+1+W]) /W
         *      两式相减：
         *      dp[i] - dp[i+1] = (dp[i+1] - dp[i+w+1])/W
         *      ==>
         *      dp[i] = dp[i+1] + (dp[i+1] - dp[i+w+1])/W
         */
        if (N >= K-1+W || K==0){
            return 1.0;
        }
        double[] dp = new double[K+W+1];
        for (int i = K; i <=N && i<K+W; i++) {
            dp[i] = 1.0;
        }
        dp[K-1] = 1.0 * Math.min(N-K+1,W)/W;
        for (int i = K-2; i >=0 ; i--) {
            dp[i] = dp[i+1] - (dp[i+W+1] - dp[i+1])/W;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int N = 21;
        int K = 17;
        int W = 10;
        System.out.println(sol.new21Game(N,K,W));
    }
}
