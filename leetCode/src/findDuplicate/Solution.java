package findDuplicate;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findDuplicate(int[] nums) {//哈希表求解，时间复杂度O(n)
        int len = nums.length;
        int n = len - 1;
        Map<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (hmap.containsKey(nums[i])) {
                ans = nums[i];
                break;
            }
            hmap.put(nums[i], 1);
        }
        return ans;
    }

    public int findDuplicate1(int[] nums) { //二分查找，时间复杂度O(nlogn),空间复杂度O（1）
        //target存在的范围[1,n) 或者[left, right]
        int n = nums.length;
        int left = 1;
        int right = n - 1;
        int ans = 0;
        while (left <= right){
            int mid = left + (right - left)/2;
            int count = 0; //记录数组中小于等于mid的数的个数
            for (int k = 0; k < n; k++){
                if (nums[k] <= mid){
                    count ++;
                }
            }
            if (count <= mid){ //数组中mid的个数小于等于mid，说明target出现在mid右边，左指针右移
                left = mid + 1;
            }
            else{ //否则，说明target可能为mid或者在mid左边，更新ans值，并将右指针左移
                right = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }
}
