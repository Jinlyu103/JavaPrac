package sumNums;

public class Solution {
    public int sumNums(int n){
        /**
         * 计算1+2+...+n
         * 不能用if、while、for循环等条件控制语句
         * 因此思路：
         * 递归,但是不能用条件运算符判断递归的出口。因此要用到逻辑运算符的短路性质
         *
         */
        //用&&
        //boolean flag = (n>0) && ((n += sumNums(n-1)) >0);
        //用||
        boolean flag = (n == 0) || ((n += sumNums(n-1))>0);
        return n;
    }
}
