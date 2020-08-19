package threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums){
        /**
         * 排序+双指针
         * 先将nums按照从小到大的顺序排序
         * 对于第i个数，如果nums[i]>0,后面的数也大于0，相加不可能等于0
         * 如果i>0并且nums[i] == nums[i-1],需要去除重复值
         * L = i+1
         * R = n-1
         * 判断nums[i]+nums[L]+nums[R]
         *      = 0: ans.add(list(nums[i],nums[L],nums[R]))
         *          然后左右分别去除重复值之后，左指针右移，右指针左移
         *      >0: R -= 1
         *      <0: L += 1
         */
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int n = nums.length;
        if (n < 3){
            return null;
        }
        Arrays.sort(nums);
        for(int i = 0; i<n; i++){
            if (nums[i] > 0){
                break;
            }
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int L = i+1;
            int R = n-1;
            while (L < R){
                if (nums[i]+nums[L]+nums[R] == 0){
                    List<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(nums[i]);
                    tmp.add(nums[L]);
                    tmp.add(nums[R]);
                    ans.add(tmp);
                    while (L < R && nums[L] == nums[L+1]){
                        L++;
                    }
                    while (L<R && nums[R] == nums[R-1]){
                        R--;
                    }
                    L++;
                    R--;
                } else if (nums[i] + nums[L] + nums[R] > 0){
                    R --;
                } else {
                    L ++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Solution sol = new Solution();
        System.out.println(sol.threeSum(nums).toString());
    }
}
