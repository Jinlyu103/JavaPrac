package isFlipedString;

public class Solution {
    /**
     * 面试题 01.09 字符串轮转
     * 轮转非逆序
     */
    public boolean isFlipedString(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 != len2){
            return false;
        }
        if (len1 == 0 && len2 == 0){
            return true;
        }
        return (s1+s1).contains(s2);
    }

    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        Solution sol = new Solution();
        System.out.println(sol.isFlipedString(s1,s2));
    }
}
