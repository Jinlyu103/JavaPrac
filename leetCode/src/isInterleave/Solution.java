package isInterleave;

public class Solution {
    /**
     * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3){
        /**
         * 动态规划，前提：len1 + len2 = len3
         * dp[i][j] 表示：s1 的前 i个字符和s2的前j个字符交错成s3的前i+j个字符
         * 转移：
         * if s3[i+j] == s1[i]:
         *      dp[i][j] = dp[i-1][j]
         * if s2[i+j] == s2[j]:
         *      dp[i][j] = dp[i][j-1]
         */
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len1+len2!=len3){
            return false;
        }
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;

        for (int i = 0; i<= len1; i++){
            for (int j = 0; j <= len2; j++){
                if (i>0){
                    dp[i][j] = dp[i][j] || (s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j]);
                }
                if (j>0){
                    dp[i][j] = dp[i][j] || (s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s1 = "ab";
        String s2 = "bc";
        String s3 = "babc";
        System.out.println(sol.isInterleave(s1,s2,s3));
    }
}
