package minSubArrayLen;

public class Solution {
    public static int minSubArrayLen(int[] nums, int s){
        //前缀和。复杂度O(n^2)
        int n = nums.length;
        int[] pre = new int[n+1];
        pre[0] = 0;
        for (int i = 1; i<n+1; i++){
            pre[i] = pre[i-1] + nums[i-1];
        }
        //nums[i] + ... + nums[j] >= s,长度为j-i+1
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j<n+1;j++){
                if (pre[j] - pre[i] < s)
                    continue;
                ans = Math.min(ans, j-i);
            }
        }
        if (ans == Integer.MAX_VALUE)
            return 0;
        return ans;
    }

    public static int minSubArrayLen1(int s, int[] nums){
        //时间复杂度O(nlogn)
        int n = nums.length;
        int[] pre = new int[n+1];
        pre[0] = 0;
        //计算前缀和
        for (int i = 1; i<n+1; i++){
            pre[i] = pre[i-1] + nums[i-1];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n+1; i++) {
            int L = i+1; //前缀和下标
            int R = n;   //前n个数的和
            //二分法
            while (L<=R){
                int mid = L+(R-L)/2;
                if (pre[mid] - pre[i] < s){
                    L = mid+1;
                } else {
                    ans = Math.min(ans, mid - i);
                    R = mid-1;
                }
            }
        }
        if (ans == Integer.MAX_VALUE){
            return 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(minSubArrayLen1(s,nums));
    }
}
