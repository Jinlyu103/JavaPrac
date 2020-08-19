package threeSumClosest;

import java.util.Arrays;

public class Solution {
    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
     * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
     * 假定每组输入只存在唯一答案。
     */
    public int threeSumCloset(int[]  nums, int target){
        /**
         * 排序+双指针
         */
        int ans = 0;
        int dif = 1<<10;
        Arrays.sort(nums);
        for (int i = 0; i<nums.length;i++){
            if (i > 0 && nums[i] == nums[i-1])
                continue;
//            if (nums[i] > target)
//                break;
            int L = i+1;
            int R = nums.length - 1;
            while (L<R){
                int tmp = nums[i] + nums[L] + nums[R];
                if (Math.abs(target - tmp) < dif){
                    ans = tmp;
                    dif = Math.abs(target - tmp);
                }
                if (dif == 0)
                    return ans;
                if (L<R && nums[i] + nums[L] + nums[R] < target){
                    L++;
                    continue;
                }
                if (L<R && nums[i] + nums[L] + nums[R] > target){
                    R --;
                    continue;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,1,55};
        int target = 3;
        Solution sol = new Solution();
        System.out.println(sol.threeSumCloset(nums,target));
    }
}
