package maxScoreSightseeingPair;

import nowcode.robotJumping.Main;

import java.util.LinkedList;

public class Solution {
    public int maxScoreSightseeingPair(int[] A){
        /**
         * 最佳观光组合
         * 一对景点（i<j）组成的观光组合的得分为（A[i]+A[j]+i-j）
         * 返回一对观光景点能取得的最高分
         * A[i]+A[j]+i-j
         * =(A[i]+i) + (A[j]-j)
         * 其中，A[i]+i或A[j]-j是固定的，需要先固定一个找另一个的最大值
         *
         */
        int ans = 0;
        int n = A.length;
//        int max = A[0]+0;
//        for (int j = 1; j < n ; j++) {
//            ans = Math.max(ans, max + A[j]-j);
//            //同时维护max
//            max = Math.max(max, A[j]+j);
//        }

        int max = A[n-1] - (n-1);
        for (int i = n-2; i >= 0 ; i--) {
            ans = Math.max(ans, max+A[i]+i);
            max = Math.max(max, A[i]-i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {6,1,5,2,8};
        Solution sol = new Solution();
        System.out.println(sol.maxScoreSightseeingPair(A));
    }
}
