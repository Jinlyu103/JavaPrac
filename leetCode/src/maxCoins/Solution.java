package maxCoins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 312. 戳气球
     * 有n个气球，编号为0 ~ n-1，nums[i]表示第i个气球上标的数字
     * 现要戳破所有气球，若戳破气球i,可以获得硬币：nums[left]*nums[i]*nums[right]
     * 假设nums[-1] = nums[n] = 1
     */
    int coins = 0;
    int maxCoins = 0;
    public int maxCoins(int[] nums){
        /**
         * 回溯法
         * 时间复杂度O(n!)，超时
         */
        int n = nums.length;
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return nums[0];
        }

        List<Integer> isPoked = new ArrayList<>();
        backtrack(nums, isPoked);
        return maxCoins;
    }

    private void backtrack(int[] nums, List<Integer> isPoked){
        if (isPoked.size() == nums.length){
            maxCoins = Math.max(maxCoins,coins);
        }
        for (int i = 0; i < nums.length; i++){
            if (isPoked.contains(i)){
                continue;
            }
            isPoked.add(i);
            int left = i-1;
            int right = i+1;
            int tmp = nums[i];
            while (left > -1 && isPoked.contains(left)){
                left --;
            }
            if (left!=-1){
                tmp = tmp*nums[left];
            }
            while (right<nums.length && isPoked.contains(right)){
                right ++;
            }
            if (right!=nums.length){
                tmp = tmp*nums[right];
            }
            coins += tmp;
            backtrack(nums, isPoked);
            coins -=tmp;
            isPoked.remove(Integer.valueOf(i));
        }
    }


    public int maxCoins1(int[] nums){
        /**
         * 分治法
         * def(i,j) = def(i,k)+def(k,j)+nums[i]*nums[k]*nums[j]
         * 即戳破i~j之间的气球，最后只剩下i,k,j三个相邻
         */
        int n = nums.length;
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return nums[0];
        }
        //加虚拟边界
        int[] nums2 = new int[n+2];
        /**
         * arraycopy参数说明：
         * nums:原数组
         * 0：源数组要复制的起始位置
         * nums2:目的数组
         * 1：目的数组放置的起始位置
         * n:要复制的长度
         */
        System.arraycopy(nums,0,nums2,1,n);
        nums2[0] = 1;
        nums2[n+1] = 1;
        n = nums2.length;
        //创建缓存数组
        int[][] cache = new int[n][n];
        //调用分治函数
        return div(nums2, n, cache);
    }

    private int div(int[] nums, int len, int[][] cache){
        int max = def(nums, len, 0, len-1, cache);
        return max;
    }

    private int def(int[] nums, int len, int begin, int end, int[][] cache){
        if (begin == end-1){
            return 0;
        }
        if (cache[begin][end]!=0){
            return cache[begin][end];
        }
        int max = 0;
        for (int i = begin+1;i<end; i++){
            int tmp = def(nums, len, begin, i, cache) +
                      def(nums,len,i,end,cache) +
                      nums[begin]*nums[i]*nums[end];
            if (tmp>max){
                max = tmp;
            }
        }
        cache[begin][end] = max;
        return max;
    }

    public int maxCoins3(int[] nums){
        /**
         * 动态规划：
         * dp[i][j]：戳破i~j之间的气球所获得的最大硬币数,不包括i,j
         * 转移方程：
         * dp[i][j] = max(dp[i][j], dp[i][k] + dp[k][j] + nums[i]*nums[k]*nums[j])
         */
        int n = nums.length;
        int[] nums2 = new int[n+2];
        System.arraycopy(nums,0,nums2,1,n);
        nums2[0] = 1;
        nums2[n+1] = 1;
        n = nums2.length;
        int[][] dp = new int[n][n];
        //开始dp, i为begin，j为end
        for (int i = n-2; i>=0; i--){
            for (int j = i+2; j<n; j++){
                for (int k = i+1; k<j; k++){
                    dp[i][j] = Math.max(dp[i][j], dp[i][k]+dp[k][j] + nums2[i]*nums2[k]*nums2[j]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        int[] nums = {35,16,83,87,84,59,48,41,20,54};
        Solution sol = new Solution();
        System.out.println(sol.maxCoins1(nums));
        System.out.println(sol.maxCoins3(nums));
    }
}
