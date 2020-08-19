package addStrings;

public class Solution {
    /**
     * 415. 字符串相加
     * 给定两个字符串形式的非负整数num1，num2，计算他们的和，返回字符串
     */
    public String addStrings(String num1, String num2){
        int len1 = num1.length(), len2 = num2.length();
        if (len1==0){
            return num2;
        }
        if (len2 == 0){
            return num1;
        }
        int carry = 0;
        int idx1 = len1-1;
        int idx2 = len2-1;
        StringBuilder res = new StringBuilder();
        while (idx1>=0 && idx2>=0){
            int cur1 = Integer.valueOf(num1.substring(idx1,idx1+1));
            int cur2 = Integer.valueOf(num2.substring(idx2,idx2+1));
            if (cur1+cur2+carry < 10){
                res.append(cur1+cur2+carry);
            } else {
                res.append((cur1+cur2+carry)%10);
            }
            carry = (cur1+cur2+carry)/10;
            idx1--;
            idx2--;
        }
        while (idx1>=0){
            int cur1 = Integer.valueOf(num1.substring(idx1,idx1+1));
            if (cur1+carry < 10){
                res.append(cur1+carry);
            } else {
                res.append((cur1+carry)%10);
            }
            carry = (cur1+carry)/10;
            idx1--;
        }
        while (idx2>=0){
            int cur2 = Integer.valueOf(num2.substring(idx2,idx2+1));
            if (cur2+carry < 10){
                res.append(cur2+carry);
            } else {
                res.append((cur2+carry)%10);
            }
            carry = (cur2+carry)/10;
            idx2--;
        }
        if (carry != 0){
            res.append(carry);
        }
        return res.reverse().toString();
    }
}
