package smallestRange;

import javax.swing.event.ListDataEvent;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    /**
     * 632. 最小区间
     * 有k个升序排列的整数数组，找到一个最小区间，使得k个列表中的每个列表至少有一个数包含在其中
     * @param nums
     * @return
     */
    public int[] smallestRange(List<List<Integer>> nums){
        /**
         * 最小区间满足：
         *  1、长度最小：需要计算
         *  2、长度相等时，起点最小：从每个列表中找最小的元素，组成新区间，作为起始区间
         *  如果之后没有长度比起始区间长度更短的，那么起始区间就是所求的最小区间
         * 使用优先队列，从k个列表中取数，将其按照从小到大排列
         */
        int k = nums.size(); //k个整数列表，均为升序
        int[] next = new int[k]; //存放k个指针,初始时均指向起始位置

        int minLeft = 0, maxRight = Integer.MAX_VALUE; //区间最左和最右的值
        PriorityQueue<Integer> priorQ = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer idx1, Integer idx2) {
                return nums.get(idx1).get(next[idx1]) - nums.get(idx2).get(next[idx2]);
            }
        });

        int minRange = maxRight - minLeft;
        int max = Integer.MIN_VALUE;

        //max: 从列表中最小元素，找最大的最小值，作为区间右端点
        for (int i = 0; i<k; i++){
            priorQ.offer(i); //根据指针next[i]，自动对优先队列priorQ进行排序
            max = Math.max(max, nums.get(i).get(0));
        }

        while (true){
            int minIdx = priorQ.poll(); //优先队列中最小元素所在列表序号，取出next[minIdx]指针指向的元素作为区间左端点
            int curRange = max - nums.get(minIdx).get(next[minIdx]); //新区间的长度
            //如果新区间长度比最小长度小，则更新最小长度
            if (curRange < minRange){
                minRange = curRange;
                minLeft = max - curRange;
                maxRight = max;
            }
            next[minIdx] ++;
            //如果最小元素所在列表的指针越界，说明遍历结束，退出循环
            if (next[minIdx] == nums.get(minIdx).size()){
                break;
            }
            priorQ.offer(minIdx); //重新入队
            max = Math.max(max, nums.get(minIdx).get(next[minIdx])); //
        }
        return new int[]{minLeft,maxRight};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {4,10,15,24,26};
        int[] nums2 = {0,9,12,20};
        int[] nums3 = {5,18,22,30};
        int[][] num = {nums1,nums2,nums3};

        List<List<Integer>> nums = new ArrayList<>();

        for (int i = 0; i<num.length; i++){
            List<Integer> tmp = new ArrayList<>();
            for (int j=0; j<num[i].length; j++){
                tmp.add(num[i][j]);
            }
            nums.add(tmp);
        }
        int[] range = sol.smallestRange(nums);
        System.out.println(range[0] + ", "+range[1]);
    }
}
