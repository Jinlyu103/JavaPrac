package integerBreak;

import java.rmi.MarshalException;
import java.util.Arrays;

public class Solution {
    /**
     * 整数拆分
     * @param n
     * @return
     */
    public int integerBreak(int n){
        /**
         * 动态规划：
         * 整数j可以拆分为：i+ (j-i)
         * 那么dp[j] = max(dp[j], i*(j-i), dp[j-i]*i)
         * i<j<n+1
         */
        int[] dp = new int[n+1];
        Arrays.fill(dp,0);

        dp[2] = 1;

        for (int i = 2; i < n+1 ; i++) {
            for (int j = i+1; j<n+1; j++){
                dp[j] = Math.max(dp[j], i*(j-i));
                dp[j] = Math.max(dp[j],i*dp[j-i]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.integerBreak(10));
        System.out.println(sol.integerBreak(2));
    }
}
