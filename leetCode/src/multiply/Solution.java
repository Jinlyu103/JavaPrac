package multiply;

public class Solution {
    public String multiply(String num1,String num2){
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0){
            return null;
        }
        if (num1.substring(0,1).equals("0") || num2.substring(0,1).equals("0")){
            return "0";
        }
        String res = "0"; //保存计算结果
        //num2逐位与num1相乘
        for (int i = num2.length()-1; i>=0; i--){
            int carry = 0;
            StringBuffer strBuf = new StringBuffer(); //保存num2逐位乘num1的结果
            //结果低位补0
            for (int j = 0; j<num2.length()-1-i; j++){
                strBuf.append(0);
            }
            int n2 = num2.charAt(i) - '0'; //num2的第i位数字
            for (int j = num1.length()-1; j>=0; j--){
                int n1 = num1.charAt(j) - '0'; //num1的第j位数字，从低位往高位
                int product = (n1*n2+carry) % 10;
                strBuf.append(product);
                carry = (n1*n2+carry)/10;
            }
            if (carry != 0){
                strBuf.append(carry);
            }
            //将当前结果加到res中
            res = addStrings(res, strBuf.reverse().toString());
        }
        return res;
    }

    //两个字符串相加
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
            int cur1 = num1.charAt(idx1) - '0';
            int cur2 = num2.charAt(idx2) - '0';
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
            int cur1 = num1.charAt(idx1) - '0';
            if (cur1+carry < 10){
                res.append(cur1+carry);
            } else {
                res.append((cur1+carry)%10);
            }
            carry = (cur1+carry)/10;
            idx1--;
        }
        while (idx2>=0){
            int cur2 = num2.charAt(idx2) - '0';
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

    public static void main(String[] args) {
        Solution sol = new Solution();
        String num1 = "123";
        String num2 = "456";
        System.out.println(sol.multiply(num1,num2));
    }
}
