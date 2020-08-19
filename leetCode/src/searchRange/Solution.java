package searchRange;

import java.util.Arrays;

public class Solution {
    public int[] searchRange(int[] nums, int target){
        /**
         * 在排序数组中查找元素的第一个和最后一个位置
         * 二分
         */
        int[] ans = new int[2];
        int n = nums.length;
        int leftMost = findExtreme(nums, target,true); //查找最左下标
        Arrays.fill(ans,-1);

        if (leftMost == n || nums[leftMost]!=target){
            return ans;
        }
        ans[0] = findExtreme(nums, target,true);
        //查找最右下标
        ans[1] = findExtreme(nums, target,false) - 1;
        return ans;
    }

    // left参数知识，当遇到nums[m] == target的时候应该往哪边查询，
    // true表示往左，false表示往右
    private int findExtreme(int[] nums, int target, boolean left){
        int lo = 0;
        int hi = nums.length;
        int m = 0;
        while (lo < hi){
            m = lo +(hi-lo)/2;
            if (nums[m] > target || (left && nums[m] == target)){
                hi = m;
            } else {
                lo = m+1;
            }
        }
        return lo;
    }

    public int[] searchRange1(int[] nums, int target){
        /**
         * 在排序数组中查找元素的第一个和最后一个位置
         * 二分
         * 第一个：寻找数组中小于等于target的最大位置
         * 最后一个：寻找数组中大于等于target的最小位置
         */
        int[] ans = new int[2];
        Arrays.fill(ans,-1);
        int left = findMaxIdxLess(nums, target);
        if (left == -1 || nums[left] != target){
            return ans;
        }
        ans[0] = left;
        ans[1] = findMinIdxLarger(nums, target);
        return ans;
    }

    //寻找大于等于目标值的最小位置下标
    private int findMinIdxLarger(int[] nums, int target){
        int lo = 0;
        int hi = nums.length - 1;
        int res = -1;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            if (nums[mid] < target){
                lo = mid + 1;
            }
            else if (nums[mid] > target){
                hi = mid-1;
            } else {
                res = mid;
                lo = mid + 1;
            }
        }
        return res;
    }

    //寻找小于等于目标值的最大位置下标
    private int findMaxIdxLess(int[] nums, int target){
        int lo = 0;
        int hi = nums.length-1;
        int res = -1;
        while (lo<=hi){
            int mid = lo+(hi-lo)/2;
            if(nums[mid] > target){
                hi = mid-1;
            }
            else if (nums[mid] < target){
                lo = mid+1;
            }else {
                res = mid;
                hi = mid-1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 6;
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.searchRange1(nums,target)));
    }
}
