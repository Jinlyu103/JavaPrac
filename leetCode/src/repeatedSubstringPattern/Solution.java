package repeatedSubstringPattern;

public class Solution {
    /**
     * 459. 满足题目要求的字符串的性质：
     * 将两个s拼接得到新字符串str，移除str的开头首字符和末尾字符
     * 最后str若包含子串s，说明s可以由其多个子串重复构成
     * 否则返回false
     *
     * 证明：s = s' s'...s', 由n'个s'重复n/n'次构成的
     * 那么是  s+s 即为：s's'...s' s's'...s',由2n'个s'构成
     * 破坏开头的s'和末尾的s'，中间的重复的s'一定能构成包含n'个s'的子串，就说明满足题目要求
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s){
        int n = s.length();
        StringBuffer strB = new StringBuffer(s + s);
        strB.deleteCharAt(0);
        strB.deleteCharAt(strB.length()-1);
        int idx = strB.indexOf(s);
        if (idx == -1){
            return false;
        }
        return true;
    }
}
