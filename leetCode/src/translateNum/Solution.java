package translateNum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int translateNum(int num){
        /**
         * 把数字翻译成字符串
         * 计算一个数字num有多少中不同的翻译方法
         * 1、将num中的每一位转换为按位数组
         * 2、dp[i]表示前i位数字的翻译方法
         * 转移方程:
         *      dp[i] = dp[i-1] if nums[i-1]nums[i] >= 26
         *      dp[i] = dp[i-1]+dp[i-2] if nums[i-1]nums[i] <26 and i>1
         *      dp[i] = dp[i-1]+1 if nums[i-1]nums[i] < 26 and i ==1
         */
        if (num<10){ //num为个位数，只有一种翻译方式
            return 1;
        }
        List<Integer> numList = new ArrayList<Integer>();
        while (num>0){
            numList.add(num%10);
            num = num/10;
        }
        Collections.reverse(numList);
        int n = numList.size();
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n ; i++) {
            int p = numList.get(i-1);
            int q = numList.get(i);
            if (p!=0 && p*10+q <= 25){
                if (i == 1)
                    dp[i] = dp[i-1]+1;
                if (i>1){
                    dp[i] = dp[i-2] + dp[i-1];
                }
            } else {
                dp[i] = dp[i-1];
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int num = 12258;
        Solution sol = new Solution();
        System.out.println(sol.translateNum(num));
    }
}
