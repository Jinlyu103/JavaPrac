package isPalindrome;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
    public boolean isPalindrome(String s){
        /**
         * 给定一个字符串，验证其是否为回文串，只考虑字母和数字字符，忽略字母大小写
         */
        if (s == null || s.length() == 0){
            return true;
        }
        //处理字符串s中的空格和其他符号，并将其存入sList中
        List<Character> sList = new ArrayList<Character>();
        boolean ans = true;

        for (int i = 0; i <s.length() ; i++) {
            if (Character.isLetter(s.charAt(i))){ //将所有字母转换为小写格式
                sList.add(Character.toLowerCase(s.charAt(i)));
            } else if (Character.isDigit(s.charAt(i))){
                sList.add(s.charAt(i));
            } else {
                continue;
            }
        }

        int n = sList.size();
        if (n==0){
            return true;
        }
        int left = 0, right = n-1;
        while (left < right){
            if (sList.get(left) != sList.get(right)){
                ans = false;
                break;
            }
            left ++;
            right --;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "0p";
        Solution1 sol = new Solution1();
        System.out.println(sol.isPalindrome(s));
    }
}
