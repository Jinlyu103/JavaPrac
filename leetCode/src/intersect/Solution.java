package intersect;

import java.util.*;

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2){
        /**
         * 求解两个数组的交集
         */
        if (nums1.length > nums2.length)
            return intersect(nums2,nums1);

        int[] ans = new int[nums1.length];
        int idx = 0;

        Map<Integer,Integer> map1 = new HashMap<>();
        for (int i = 0; i < nums1.length ; i++) {
            if (map1.containsKey(nums1[i])){
                map1.put(nums1[i], map1.get(nums1[i]) + 1);
                continue;
            }
            map1.put(nums1[i],1);
        }

        for (int i = 0; i < nums2.length ; i++) {
            if (map1.containsKey(nums2[i]) && map1.get(nums2[i]) > 0){
                map1.put(nums2[i], map1.get(nums2[i])- 1);
                ans[idx++] = nums2[i];
            }
        }
        return Arrays.copyOfRange(ans, 0, idx);
    }

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        Solution sol = new Solution();
        System.out.println(sol.intersect(nums1,nums2));
    }
}
