package isMatch;

public class Solution1 {
    public boolean isMatch(String s, String p){
        /**
         * 给一个字符串s 和一个字符规律 p, 请实现一个支持‘.’ 和‘*’的正则表达式匹配
         *          * '?': 匹配任意单个字符
         *          * '*'：可以匹配任意字符串（包括空字符串）
         *          * s可能为空，且只包含从a-z的小写字母
         *          * p可能为空，且只包含从a-z的小写字母，以及字符.和*
         * 动态规划：dp[i][j]: 表示s中前i个字符可以被p中前j个字符所匹配
         * if s[i] = p[j] || p[j] = '?':
         *      dp[i][j] = dp[i-1][j-1]
         * if p[j] = '*' &&
         *    (dp[i-1][j] == true || dp[i][j-1] == true) ('*'匹配空串或者匹配多个字符的形式)
         *    dp[i][j] = true
         *
         * 初始化：
         *      dp[0][0] = true (s为空，p为空)
         *      dp[0][j] = true (s为空，p中只有*)
         *      dp[i][0] = false (s不空(i>=1)，p为空)
         *
         */
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        if (m == 0 && n == 0){
            return true;
        }
        //初始化，s为空，p为空
        dp[0][0] = true;
        //s不为空，p为空
        for (int i = 1; i <= m; i++){
            dp[i][0] = false;
        }
        //s为空，p不为空，只有p中全为*，才为true
        for (int j = 1; j <= n; j++){
            if (p.charAt(j-1) == '*' && dp[0][j-1]){
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*' && (dp[i-1][j] || dp[i][j-1])){
                    dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "*";
        Solution1 sol = new Solution1();
        System.out.println(sol.isMatch(s,p));
    }
}
