package longestValidParentheses;

public class Solution {
    public int longestValidParentheses(String s){
        /**
         * 最长有效括号匹配，括号匹配一般用栈来做
         * 栈，动态规划
         * dp[i]:以s[i]结尾的子串最长有效字符串长度
         * 初始化：s[0] = 0
         * 转移方程：
         * if s[i] = "(":
         *      ...(, dp[i] = dp[i-1]
         * else(s[i] = ")"):
         *      ...()：dp[i] = dp[i-2]+2  if i>0 && s[i-1] == '('
         *      ...)): if i>0 && s[i-1] ==')'
         *              dp[i] = dp[i-1]+dp[i-dp[i-1]-1] + 2  if i-dp[i-1] - 1 >= 0 && s[i-dp[i-1]] == '('
         */
        int n = s.length();
        int[] dp = new int[n];
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ')'){
                //...()
                if (i > 0 && s.charAt(i-1) == '('){
                    dp[i] = dp[Math.max(0,i-2)] + 2;
                } else if (i>0 && s.charAt(i-1) == ')'){
                    //找到前面最后一个未匹配的左括号
                    if (i-dp[i-1] -1 >= 0 && s.charAt(i-dp[i-1] - 1) == '('){
                        dp[i] = dp[i-1] + dp[Math.max(0, i-dp[i-1]-2)] + 2;
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "(()())";
        System.out.println(sol.longestValidParentheses(s));
    }
}
