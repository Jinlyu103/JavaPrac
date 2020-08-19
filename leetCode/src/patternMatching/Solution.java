package patternMatching;

public class Solution {
    public boolean patternMatching(String pattern, String value){
        String[] s = new String[2]; //s[0]表示a匹配的串，s[1]表示b匹配的串
        return backtrack(s,pattern,0,value,0);
    }

    private boolean backtrack(String[] s, String pattern, int pIndex, String value, int vIndex){
        if (pIndex == pattern.length() && vIndex == value.length())
            return true;
        if (pIndex >= pattern.length() || vIndex > value.length())
            return false;
        int num = pattern.charAt(pIndex) - 'a';
        if (s[num] == null){
            //a或者b还没有匹配字符串，尝试每一种可能
            for (int i = vIndex; i<=value.length();i++){
                s[num] = value.substring(vIndex, i);
                if ((s[num] == null || s[num^1] == null || !s[num].equals(s[num^1])) && backtrack(s, pattern,pIndex+1,value,i))
                    return true;
            }
            //失配
            s[num] = null;
            return false;
        }
        else {
            //a或b已经匹配了字符串，判断当前位置能否匹配上
            int end = vIndex + s[num].length();
            if (end > value.length() || !s[num].equals(value.substring(vIndex,end)))
                return false;
            return backtrack(s, pattern, pIndex+1, value, end);
        }
    }

    public static void main(String[] args) {
        String p = "abba", v= "dogcatcatdog";
        Solution sol = new Solution();
        System.out.println(sol.patternMatching(p,v));
    }
}
