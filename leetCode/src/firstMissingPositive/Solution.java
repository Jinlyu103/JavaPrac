package firstMissingPositive;

public class Solution {
    public int firstMissingPositive(int[] nums){
        /**
         * 缺失的第一个正整数
         * 给你一个未排序的整数数组，请你找出其中没有出现的那个最小正整数
         */
        int n = nums.length;
        if (n == 0){
            return 0;
        }
        int tmp;
        int i = 0;
        //原地哈希
        while (i<n){
            tmp = nums[i];
            while (tmp>=0 && tmp<=n && tmp != nums[tmp-1]){
                nums[i] = nums[tmp-1];
                nums[tmp-1] = tmp;
                tmp = nums[i];
            }
            i++;
        }
        i = 0;
        int ans=Integer.MAX_VALUE;
        while (i<n){
            if (nums[i] != i+1){
                ans = i+1;
                break;
            }
            i++;
        }
        if (ans == Integer.MAX_VALUE){
            ans = n+1;
        }
        return ans;
    }
}
