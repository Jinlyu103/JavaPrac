package shortestPalindrome;

public class Solution {
    public String shortestPalindrome(String s){
        int n = s.length();
        if (n<=1){
            return s;
        }
        int maxLen = 0;
        for (int i = 1; i<n; i++){
            StringBuffer sub = new StringBuffer(s.substring(0,i));
            if (sub.reverse().toString().equals(s.substring(0,i))){
                maxLen = i;
            }
        }

        StringBuffer sub = new StringBuffer(s.substring(maxLen+1));
        sub.reverse();
        String res = sub + s;
        return res;
    }

    public String shortestPalindrome1(String s){
        if (s.length()<=1){
            return s;
        }
        StringBuffer strB = new StringBuffer(s);
        strB.reverse();
        String newStr = s+"#"+strB.toString();
        int maxL = 0;
        //KMP: 计算新串的最长公共前后缀
        for (int l = s.length(); l>0; l--){
            String pre = newStr.substring(0,l);
            String post = newStr.substring(newStr.length()-l);
            if (pre.equals(post)){
                maxL = l;
                break;
            }
        }
        return strB.substring(0,strB.length() - maxL)+s;
    }

    public boolean isPalindrome(String s){
        StringBuffer strB = new StringBuffer(s);
        strB.reverse();
        if (strB.toString().equals(s)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aacecaaa";
        System.out.println(sol.shortestPalindrome1(s));
    }
}
