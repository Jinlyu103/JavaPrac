package findLength;

public class Solution {
    public int findLength(int[] A, int[] B){
        /**
         * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
         * 动态规划：dp[i][j]：A[i:]与B[j:]的最长公共子数组的长度
         * 转移关系：
         * dp[i][j] = dp[i+1][j+1]  if A[i] == B[j]
         *          = 0 otherwise
         * 初始化：dp[aLen-1][bLen-1] = 1 if A[aLen-1] == B[bLen-1]
         *                           = 0 otherwise
         */
        int n = A.length, m = B.length;
        int[][] dp = new int[n][m];
        int maxLen = 0;

        for (int i = n-1; i>=0; i--){
            for (int j = m-1; j >= 0; j--) {
                if ((i == n-1 || j == m-1) && A[i] == B[j]) {
                    dp[i][j] = 1;
                    maxLen = Math.max(dp[i][j],maxLen);
                    continue;
                }
                if (A[i] == B[j])
                    dp[i][j] = dp[i+1][j+1]+1;
                maxLen = Math.max(dp[i][j],maxLen);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] A = {1,2,3,2,1};
        int[] B = {3,2,1,4,7};
        Solution sol = new Solution();
        System.out.println(sol.findLength(A,B));
    }
}
