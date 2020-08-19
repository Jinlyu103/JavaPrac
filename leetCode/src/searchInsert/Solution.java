package searchInsert;

public class Solution {
    public int searchInsert(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int t = 5;
        Solution sol = new Solution();
        System.out.println(sol.searchInsert(nums,t));
    }
}
