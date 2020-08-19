package twoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target){
        int[] ans = new int[2];
        //key为target-nums[i], value为i
        Map<Integer,Integer> hmap = new HashMap<Integer,Integer>();
        Arrays.fill(ans,-1);
        for (int i = 0; i<nums.length; i++){
            if (hmap.containsKey(nums[i])){
                ans[1] = i;
                ans[0] = hmap.get(nums[i]);
            }
            else {
                hmap.put(target-nums[i],i);
            }
        }
        return ans;
    }

    public int[] twoSum2(int[] nums, int target){
        //双指针
        int n = nums.length;
        int left = 0;
        int right = n-1;
        int[] ans = new int[2];
        Arrays.fill(ans, -1);
        while (left < right){
            if (nums[left] + nums[right] == target){
                ans[0] = left + 1;
                ans[1] = right+1;
                break;
            } else if (nums[left] + nums[right] > target){
                right --;
            } else {
                left ++;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.twoSum(nums,target)));
    }
}
