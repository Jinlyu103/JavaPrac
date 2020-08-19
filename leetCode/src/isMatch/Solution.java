package isMatch;

public class Solution {
    public boolean isMatch(String s, String p){
        /**
         * 给一个字符串s 和一个字符规律 p, 请实现一个支持‘.’ 和‘*’的正则表达式匹配
         * '.': 匹配任意单个字符
         * '*'：匹配0个或多个前面的那一个字符
         * s可能为空，且只包含从a-z的小写字母
         * p可能为空，且只包含从a-z的小写字母，以及字符.和*
         *
         * 动态规划：dp[i][j]表示s中前i个字符可以被p中前j个字符所匹配
         * if s[i]=p[j] || p[j] = .: dp[i][j] = dp[i][j-1]
         * if p[j] = *: 考查p[j-1]
         *          if p[j-1] != s[i] && p[j-1] != . ： 相当于直接去掉 p[j-1]*,因为无法匹配
         *              dp[i][j] = dp[i][j-2]
         *          else:
         *              dp[i][j] = dp[i][j-1] p中的p[j-1]*以单个字符进行匹配
         *                      or dp[i][j-2] p中的p[j-1]*以0个字符进行匹配
         *                      or dp[i-1][j] p中的p[j-1]*以多个字符进行匹配
         */
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        //初始化
        dp[0][0] = true;
        for (int j =0;j<n;j++){
            if (p.charAt(j) == '*' && dp[0][j-1]) //a*b*c*....
                dp[0][j+1] = true;
        }
        //状态转移
        for (int i = 1; i<=m; i++){
            for (int j = 1; j <=n ; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                if (p.charAt(j-1) == '*'){
                    if (p.charAt(j-2) != s.charAt(i-1) && p.charAt(j-2) != '.'){
                        dp[i][j] = dp[i][j-2];
                    } else {
                        dp[i][j] = dp[i][j-1] || dp[i][j-2] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public boolean isMatch1(String s, String p) {
        int m = s.length();
        int n = p.length();

        /**
         * 定义二维数组：dp[i][j]表示字符串s的前i个字符与字符串p的前j个字符是否匹配
         * dp[i][j]是在已知dp[i-1][j-1]和s[i], p[j]的情况，讨论转移方程
         * 当s[i] = p[j]: dp[i][j] = dp[i-1][j-1]
         * p[j] = . : dp[i][j] = dp[i-1][j-1]
         * p[j] = * : dp[i][j] = ? 此时需要考虑s[i], p[j-1]
         *                     = dp[i][j-2]  if s[i] != p[j-1]
         *                     = dp[i-1][j] ||
         *                       dp[i][j-1] ||
         *                       dp[i][j-2] ||
         */


        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true; //s和p为空串时，匹配
        //初始化
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]){
                dp[0][i+1] = true;
            }
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                if (p.charAt(j-1) == '*'){
                    if (s.charAt(i-1) != p.charAt(j-2) && p.charAt(j-2)!='.'){
                        dp[i][j] = dp[i][j-2];
                    } else{
                        dp[i][j] = dp[i-1][j] || dp[i][j-1] || dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        Solution sol = new Solution();
        System.out.println(sol.isMatch1(s,p));
    }
}
