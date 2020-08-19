package rob;

import javax.naming.InsufficientResourcesException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
//    int maxRes;
//    Map<Integer, Integer> robbedMoney = new HashMap<Integer, Integer>(); //房屋编号：金额
    public int rob(int[] nums){
        /**
         * 动态规划：
         *      dp[i][0]:不偷第i间屋子所获得的的最大金额
         *      dp[i][1]:偷第i间屋子所获的的最大金额
         *      dp[i][0] = max(dp[i-1][0], dp[i-1][1]
         *      dp[i][1] = dp[i-1][0] + nums[i]
         */
        int n = nums.length;
        if (n == 0){
            return 0;
        }
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i<n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0]+nums[i];
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

    public int rob2(int[] nums){
        /**
         * 打家劫舍II：
         * 回溯法，求出所有可能的方案，再返回最大值
         * 回溯不能通过全部的测试用例
         * maxRes = 0;
         * if (nums.length == 0){
         *     return 0;
         * }
         * if (nums.length == 1){
         *     return nums[0];
         * }
         *backtrack(nums);
         *return maxRes;
         */
        /**
         * 动态规划，从不偷第一号房和不偷第n-1号房出发，将问题转换为198题的无环问题
         */
        int n = nums.length;
        if (n==0){
            return 0;
        }
        if(n==1){
            return nums[0];
        }
        int ans1 = 0;
        int ans2 = 0;
        int[][] dp = new int[n][2];
        //不偷第一间房，从第二间房到n间房考虑单排列
        dp[1][0] = 0;
        dp[1][1] = nums[1];
        for(int i = 2; i<n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0]+nums[i];
        }
        ans1 = Math.max(dp[n-1][1], dp[n-1][0]);
        //System.out.println(ans1);
        //不偷第n间房，从第一间到n-1间房考虑单排列
        dp[0][1] = nums[0];
        dp[0][0] = 0;
        for (int i = 1; i < n-1; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        ans2 = Math.max(dp[n-2][1], dp[n-2][0]);
        //System.out.println(ans2);
        return Math.max(ans1, ans2);
    }

//    public void backtrack(int[] nums){
//        if (robbedMoney.size() == nums.length/2){
//            //已经没有剩下的选择
//            int total = 0;
//            for (Integer i: robbedMoney.keySet()) {
//                System.out.println("robbed house[" + i +"]");
//                total += robbedMoney.get(i);
//            }
//            System.out.println();
//            maxRes = Math.max(maxRes, total);
//        }
//        for (int i = 0; i <nums.length ; i++) {
//            if (!isValid(i, nums.length)){
//                continue;
//            }
//            robbedMoney.put(i, nums[i]); //做出选择
//            backtrack(nums);
//            robbedMoney.remove(i); //撤销选择
//        }
//    }

//    private boolean isValid(int i, int n){
//        boolean flag = true;
//        if (robbedMoney.containsKey(i)
//                || robbedMoney.containsKey((i+1)%n)
//                || robbedMoney.containsKey((i-1 + n)%n)){
//            flag = false;
//        }
//        return flag;
//    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println(sol.rob2(nums));
    }
}
