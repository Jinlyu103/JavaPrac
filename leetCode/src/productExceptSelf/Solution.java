package productExceptSelf;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] productExceptSelf(int [] nums){
        int n = nums.length;
        int[] ans = new int[n];
        Map<Integer, Integer> lHmap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> rHmap = new HashMap<Integer, Integer>();
        //计算nums[i]左边元素的乘积
        for (int i = 0; i < n; i++) {
            if (i == 0){
                lHmap.put(i, 1);
            } else {
                int l = lHmap.get(i-1) * nums[i-1];
                lHmap.put(i, l);
            }
        }
        //计算nums[i]右边元素的乘积
        for (int i = n-1; i >= 0 ; i--) {
            if (i == n-1){
                rHmap.put(i, 1);
            } else{
                int r = rHmap.get(i+1) * nums[i+1];
                rHmap.put(i, r);
            }
        }
        //计算ans
        for (int i = 0; i < n ; i++) {
            ans[i] = lHmap.get(i) * rHmap.get(i);
        }
        return ans;
    }

    public int[] productExceptSelf1(int [] nums){
        //计算nums中的数除自身之外其他元素的乘积
        int n = nums.length;
        int[] ans = new int[n];
        //先计算第i个数左边的元素乘积，存入ans
        for (int i = 0; i < n ; i++) {
            if (i == 0){
                ans[i] = 1;
            } else {
                ans[i] = ans[i-1] * nums[i-1];
            }
        }
        //计算第i个数右边的元素乘积
        int right = 1;
        for (int i = n-1; i >= 0 ; i--) {
            if (i == n-1){
                continue;
            }
            right = nums[i+1] * right;
            ans[i] *= right;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {4,3,2,1,2};
        Solution sol = new Solution();
        int[] ans = sol.productExceptSelf1(nums);
        for (int i = 0; i < ans.length ; i++) {
            System.out.print(ans[i] + ",");
        }
    }
}
