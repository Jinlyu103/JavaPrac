package findMagicIndex;

public class Solution {
    /**
     * 面试题：魔术索引,满足条件nums[i] = i
     * 给定一个有序数组，编写一种方案找出魔术索引，若存在，在数组 找出一个魔术索引，若没有返回-1
     * 若存在多个返回索引值最小的一个
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums){
        //逐个遍历
        int n = nums.length;
        for(int i=0; i<n; i++){
            if (nums[i] == i){
                return i;
            }
        }
        return -1;
    }

    //优化
    public int findMagicIndex1(int[] nums){
        /**
         * 在直接遍历上进行跳跃性优化
         * 第一种：数组中无重复元素：
         *      数组有序，假设nums[i] = i
         *      那么0，...,i-1: 值小于等于自身下标：nums[k] < k
         *          i+1,...,n-1: 值大于等于自身下标：nums[j] > j
         *      因此，当遍历到某个元素，其值小于下标，可以直接跳跃至nums[i]位置，即 i = nums[i]
         */
        int n = nums.length;
        int i = 0;
        while (i<n){
            if (nums[i] == i){
                return i;
            }
            if (nums[i] > i){ //i跳跃到nums[i]
                i = nums[i];
            }
            else i+=1;
        }
        return -1;
    }
}
