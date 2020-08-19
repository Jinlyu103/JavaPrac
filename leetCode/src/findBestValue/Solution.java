package findBestValue;

import java.util.Arrays;

public class Solution {
    public int findBestValue(int[] arr, int target) {
        /**
         * 转变数组后最接近目标值的数组和
         * 将数组中大于value的元素转变为value之后，使所有元素和最接近target，找出这样的value
         * 排序+枚举value
         * value的上界为max（arr)
         * 下界为0
         */
        int n = arr.length;
        int value = 0;
        int ans = 0;
        int dif = 1<<20;
        int[] pre = new int[n+1];
        pre[0] = 0;
        Arrays.sort(arr);
        //计算数组前缀和，这样就可以在O(1)的时间内计算转变后的数组和
        for (int i = 1; i < n+1 ; i++) {
            pre[i] = pre[i-1] + arr[i-1];
        }
        //value的上界可以优化为min(max(arr),target)。
        // 因为当value的值大于target或者max（arr）之后，数组的和与target的差异会越来越大或者数组不再变化
        while (value <= Math.min(arr[n-1],target)){
            int j=0;
            while (j < n){ //找到大于value的下标
                if (arr[j] > value){
                    break;
                }
                j++;
            }
            //转变后的数组和
            int sum = pre[j] + (n-j)*value;
            if (Math.abs(sum-target) < dif){
                ans = value;
                dif = Math.abs(sum-target);
            }
            value ++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4,9,3};
        int target = 10;
        Solution sol = new Solution();
        System.out.println(sol.findBestValue(arr,target));
    }
}
