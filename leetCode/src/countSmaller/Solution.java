package countSmaller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<Integer> countSmaller(int[] nums){
        /**
         * 315. 计算右侧小于当前元素的个数
         * 暴力超时
         */
        List<Integer> counts = new ArrayList<>();
        int n = nums.length;
        if (n==0){
            return counts;
        }
        for (int i = 0; i < n; i++){
            int c = 0;
            for (int j = i+1; j<n; j++){
                if (nums[j] < nums[i]){
                    c ++;
                }
            }
            counts.add(c);
        }
        return counts;
    }

    public List<Integer> countSmaller1(int[] nums){
        int n = nums.length;
        List<Integer> counts = new ArrayList<>();
        if (n == 0)
            return counts;
        List<Integer> sorted = new ArrayList<>();
        for (int i = n-1; i>=0; i--){
            int idx = findIdx(sorted, nums[i]); //nums[i]右边有idx个元素小于nums[i]
            sorted.add(idx, nums[i]); //将nums[i]插入到sorted数组的idx个位置
            counts.add(idx);
        }
        Collections.reverse(counts);
        return counts;
    }

    //target应该放在sorted数组的第几个位置,二分法查找插入位置
    private int findIdx(List<Integer> sorted, int target){
        int n = sorted.size();
        if (n == 0){
            return 0;
        }
        int l = 0, r = n-1;
        while (l<=r){
            int m = (l+r)/2;
            if (sorted.get(m) <target){
                l = m+1;
            } else {
                r = m-1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums = {5,2,6,1};
        Solution sol = new Solution();
        System.out.println(sol.countSmaller1(nums));
    }
}
