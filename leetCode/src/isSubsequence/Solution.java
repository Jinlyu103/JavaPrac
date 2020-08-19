package isSubsequence;

public class Solution {
    /**
     * 给定字符串s和t,判断s是否为t的子序列
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t){
        /**
         * 对于s[i]和t[j]
         * if s[i] == t[j]:
         *      i++, j++
         * else
         *      j++
         */
        if (s.equals(t) || s.length() == 0){
            return true;
        }
        int sLen = s.length();
        int tLen = t.length();
        if (sLen > tLen){
            return false;
        }
        int sIdx = 0;//指向s的指针
        int tIdx = 0;//指向t的指针
        while (sIdx<sLen && tIdx<tLen){
            if (s.charAt(sIdx) == t.charAt(tIdx)){
                sIdx++;
            }
            tIdx ++;
        }
        if (sIdx == sLen){
            return true;
        }
        return false;
    }
}
