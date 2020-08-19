package subarraysDivByK;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int subarraysDivByK(int[] A, int K){
        //前缀和，时间复杂度O(n^2)，运行超时
        int len = A.length;
        int[] pre = new int[len+1];
        int ans = 0;
        pre[0] = 0;
        for(int i = 1; i <= len; i++){
            pre[i] = pre[i-1] + A[i-1];
        }
        for (int i = 0; i<len; i++){
            for (int j = i+1; j<= len; j++){
                if (Math.abs(pre[j] - pre[i]) % K == 0){
                    ans ++;
                }
            }
        }
        return ans;
    }

    public int subarraysDivByK1(int[] A, int K){
        /**
         * 前缀和+哈希表
         * 根据同余定理:
         *     给定一个正整数m，如果两个整数a和b满足a-b能够被m整除，
         *     即(a-b)/m得到一个整数，那么就称整数a与b对模m同余，记作a≡b(mod m)，即 (a 模 m) == (b 模 m)
         * 用哈希表存放 pre[i]%k 出现的次数
         */
        Map<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        int n = A.length;
        int[] pre = new int[n];
        int ans = 0;

        for (int i = 0; i<n; i++){
            if (i == 0){
                pre[i] = A[0];
            }
            else{
                pre[i] = pre[i-1] + A[i];
            }
            int mod = Math.floorMod(pre[i],K); //java取模运算
            if (mod == 0){
                ans ++;
            }
            if (hmap.containsKey(mod)){
                ans += hmap.get(mod);
                hmap.put(mod, hmap.get((mod))+ 1);
            }
            else{
                hmap.put(mod, 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {-1, 2 ,9};
        int k = 2;
        Solution sol = new Solution();
        System.out.println(sol.subarraysDivByK1(A, k));
    }
}
