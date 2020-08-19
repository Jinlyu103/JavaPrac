package countAndSay;

public class Solution {
    public String countAndSay(int n){
        /**
         * 外观数列
         * 递归求解，根据第n-1个数的结果，推导第n个结果
         */
        StringBuilder s = new StringBuilder();
        int p = 0;
        int cur = 1;
        if (n==1)
            return "1";
        if (n==2)
            return "11";
        String str = countAndSay(n-1); //递归
        //根据n-1, 求解 n
        for (cur = 1; cur <str.length(); cur++){
            //如果当前字符与前面紧邻字符不等，更新结果
            if (str.charAt(p) != str.charAt(cur)){
                int count = cur - p;
                //count个(s中第p个)字符
                s.append(count).append(str.charAt(p));
                p = cur;
            }
        }
        //更新最后一段
        if (p != cur){
            int count = cur - p;
            s.append(count).append(str.charAt(p));
        }
        return s.toString();
    }
}
