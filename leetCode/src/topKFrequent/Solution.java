package topKFrequent;

import java.util.*;

public class Solution {
    /**
     * 给定一个非空数组，返回其中出现频率前k高的元素
     */
    public int[] topKFrequent(int[] nums, int k){
        Map<Integer, Integer> numsFreq = new HashMap<>();           //统计元素出现频率
        PriorityQueue<int[]> freq = new PriorityQueue<>(new Comparator<int[]>() {//频率：元素
            @Override
            public int compare(int[] o1, int[] o2) {
                return -Integer.compare(o1[0], o2[0]); //按照出现频率降序排序
            }
        });

        for (int i = 0; i<nums.length; i++){
            if (numsFreq.containsKey(nums[i])){
                    numsFreq.put(nums[i], numsFreq.get(nums[i])+1);
            } else {
                numsFreq.put(nums[i],1);
            }
        }

        for (Integer key: numsFreq.keySet()){
            int[] tmp = {numsFreq.get(key), key};
            freq.offer(tmp);
        }

        int[] res = new int[k];
        for (int i = 0; i<k; i++){
            int[] arr = freq.poll();
            res[i] = arr[1];
        }

        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,1,-1,2,-1,2,3};
        int k = 2;
        int[] res = sol.topKFrequent(nums, k);
        for (int r:res){
            System.out.println(r);
        }
    }
}
