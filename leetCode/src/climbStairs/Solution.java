package climbStairs;

public class Solution {
    public int climbStairs(int n){
        /**
         * 动态规划：dp[i]表示爬上第i阶的方法
         * dp[0] = 1
         * dp[1] = 1
         * dp[i] = dp[i-1]+dp[i-2] if i >= 2
         */
        if (n<=1){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i<n+1;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 2;
        Solution sol = new Solution();
        System.out.println(sol.climbStairs(n));
    }
}
