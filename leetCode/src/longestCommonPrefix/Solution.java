package longestCommonPrefix;

public class Solution {
    public String longestCommonPrefix(String[] strs){
        /**
         * 编写一个函数来查找字符串数组中的最长公共前缀。
         * 如果不存在公共前缀，返回空字符串 ""
         * 首先需要确定公共前缀的长度，然后返回strs[0]的前缀等长subString
         */
        int n = strs.length;
        if (n==0){
            return "";
        }
        if (n==1){
            return strs[0];
        }
        int longestPre = 1<<10;
        for (int i = 0; i<n-1;i++){
            longestPre = Math.min(longestPre, lcp(strs[i],strs[i+1]));
        }
        //System.out.println(longestPre);
        return strs[0].substring(0,longestPre);
    }

    private int lcp(String s1, String s2){
        int n1 = s1.length(), n2 = s2.length();
        if (n1==0 || n2 == 0){
            return 0;
        } else if (s1.charAt(0) == s2.charAt(0)){
            return lcp(s1.substring(1), s2.substring(1)) + 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        Solution sol = new Solution();
        System.out.println(sol.longestCommonPrefix(strs));
    }
}
