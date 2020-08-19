package wordBreak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 单词拆分：给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
     *          判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词
     * 动态规划:
     * dp[i]:表示前i个字符是否可以被拆分
     * 初始化：dp[0] = true
     * 定义一个start指针，指向每个单词的开始，s(start,i)为每次即将匹配的单词
     * dp[i] = dp[i - w_len]， w_len =s(start,i).length
     */
    public boolean wordBreak(String s, List<String> wordDict){
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true; //初始化
        int start = 0;
        for (int i = 1; i < n+1; i++) {
            String tmp = s.substring(start,i);
            if (wordDict.contains(tmp)){
                dp[i] = dp[i-tmp.length()];
                //如果匹配上的位置再往后一位也能匹配，开始点不变
                if (i<n && wordDict.contains(s.substring(start,i+1)))
                    continue;
                start = i;
            } else {
                int k = start;
                while (k>=0){
                    if (dp[k] && wordDict.contains(s.substring(k,i))){
                        dp[i] = dp[k];
                    }
                    if (dp[i])
                        break;
                    k--;
                }
                start = i;
            }
        }
        return dp[n];
    }

    public boolean wordBreak1(String s, List<String> wordDict){
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = 0; i < n;i++){
            for (int j = i+1; j < n+1 ; j++) {
                if (dp[i] && wordDict.contains(s.substring(i,j)))
                    dp[j] = true;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        String s = "goalspecial";
        List<String> wdict = new ArrayList<>();
        wdict.add("go");
        wdict.add("goal");
        wdict.add("goals");
        wdict.add("special");
        Solution sol = new Solution();
        System.out.println(sol.wordBreak1(s,wdict));
    }
}
