package respace;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int respace(String[] dictionary, String sentence){
        /**
         * 恢复空格： 断开文章sentence中的单词，要求未识别的字符最少，并返回未识别的字符数
         * 动态规划：
         * dp[i]表示前i个字符中最少未识别的字符数
         *转移方程：
         * 假设[0，j-1]为[0，i-1]的子问题，即已知dp[j],若[j,i-1]为字典中的单词，那么
         * dp[i] = min(dp[i],dp[j])
         * 若[j,i-1]不是字典中的单词，那么
         * dp[i] = min(dp[i],dp[j] + i-j)
         * 超时
         */
        int n = sentence.length();
        if (n==0){
            return 0;
        }
        int[] dp = new int[n+1];
        List<String> wDict = Arrays.asList(dictionary);
        Arrays.fill(dp,1<<20);
        dp[0] = 0;
        for (int j = 0; j < n; j++){
            for (int i = j+1; i < n+1 ; i++) {
                if (wDict.contains(sentence.substring(j,i))){
                    dp[i] = Math.min(dp[i], dp[j]);
                } else {
                    dp[i] = Math.min(dp[i], dp[j]+i-j);
                }
            }
        }
        return dp[n];
    }

    public int respace1(String[] dictionary, String sentence){
        /**
         * 恢复空格： 断开文章sentence中的单词，要求未识别的字符最少，并返回未识别的字符数
         * 动态规划：
         * dp[i]表示前i个字符中最少未识别的字符数
         *转移方程：
         * 假设[0，j-1]为[0，i-1]的子问题，即已知dp[j],若[j,i-1]为字典中的单词，那么
         * dp[i] = min(dp[i],dp[j])
         * 若[j,i-1]不是字典中的单词，那么
         * dp[i] = min(dp[i],dp[j] + i-j)
         * 需要优化：
         * [0,i-1] 区间的末尾逐个截取字典每个单词的长度，看看截出来的是否是字典单词，
         * 如果是，则 dp[i] = Math.min(dp[i], dp[i - word.length])
         */
        int n = sentence.length();
        if (n==0){
            return 0;
        }
        int[] dp = new int[n+1];
        //List<String> wDict = Arrays.asList(dictionary);
        Arrays.fill(dp,1<<20);
        dp[0] = 0;
        for (int i = 1; i <=n ; i++) {
            dp[i] = dp[i-1] + 1;
            for (String word:dictionary) {
                if (i-word.length() >=0 && word.equals(sentence.substring(i-word.length(), i)))
                    dp[i] = Math.min(dp[i], dp[i-word.length()]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        String[] dictionary = {"looked","just","like","her","brother"};
        String sentence = "jesslookedjustliketimherbrother";
        Solution sol = new Solution();
        System.out.println(sol.respace1(dictionary,sentence));
    }
}
