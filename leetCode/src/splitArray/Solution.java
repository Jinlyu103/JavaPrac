package splitArray;

import javax.xml.transform.SourceLocator;
import java.util.Arrays;

public class Solution {
    /**
     * 将数组nums分成m个非空子数组
     * 在1....n之间插 m 个杠
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m){
        /**
         * 动态规划：
         * dp[i][j]:nums前 i (0~i-1) 个数划分成了j(j = 1,...,m)个子数组 各自和的最小 最大值
         * 初始化：将二维数组全置为最大值Integer.MaxValue
         * dp[0][0] = 0
         * 然后计算dp[i][j]:划分子问题如下
         *      将 前k个元素划分为j-1个子数组，剩下的（第k+1~i）作为一个子数组，那么这j个连续子数组的各自和的最大值为：
         *      max(dp[k][j-1], sum(k+1,i))
         *      sum(k+1,i) = pre[i] - pre[k]
         *      所以：
         *      dp[i][j] = min(dp[i][j], max(dp[k][j-1], sum(k+1,i)))
         *
         * 因此题目的结果为：dp[n][m]
         */
        int n = nums.length;
        int[] pre = new int[n+1];
        //记录前缀和
        pre[0] = 0;
        for (int i = 1; i <=n ; i++) {
            pre[i] = pre[i-1]+nums[i-1];
        }
        int[][] dp = new int[n+1][m+1];
        //初始化
        for (int i = 0; i <= n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++){
            for (int j = 1; j<=Math.min(i,m); j++){
                int max = 0;
                for (int k = 0; k < i; k++){
                    int sub = pre[i] - pre[k]; //sub(nums[k:i])
                    max = Math.max(dp[k][j-1], sub);
                    dp[i][j] = Math.min(max, dp[i][j]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        int[] nums = {7,2,5,8,10};
        int m = 2;
        Solution sol = new Solution();
        System.out.println(sol.splitArray(nums,m));
    }
}
