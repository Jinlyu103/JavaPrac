package countSubstrings;

public class Solution {
    /**
     * 647. 回文子串
     * 给定一个字符串，计算字符串中有多少个回文子串
     */
    public int countSubstrings(String s){
        if (s.length() == 0){
            return 0;
        }
        /**
         * 动态规划：dp[i][j]表示s[i]~s[j]是否为回文串
         * dp[i][j] = true if j-i < 3 && s[i] = s[j]
         * dp[i][j] = dp[i+1][j-1] if s[i] = s[j]
         * dp[i][j] = false if s[1] != s[j]
         *
         * 考虑右上角或者左下角
         */
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int res = 0;
        //考虑左下角
        for (int i = 0; i<n; i++){
            for (int j = 0; j<=i; j++){
                if (i-j<3 && s.charAt(i) == s.charAt(j)){
                    dp[j][i] = true;
                } else if (s.charAt(i) == s.charAt(j)){
                    dp[j][i] = dp[j+1][i-1];
                } else {
                    dp[j][i] = false;
                }
                if (dp[j][i]){
                    res ++;
                }
            }
        }
        return res;
    }
}
