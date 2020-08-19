package longestConsecutive;

import java.util.*;

public class Solution {
    public int longestConsecutive(int[] nums){
        /**
         * 给定一个未排序的整数数组，找出最长连续序列的长度
         * 解法：哈希表
         */
        int n = nums.length;
        if (n <= 1){
            return n;
        }
        Map<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        List<Integer> consList = new ArrayList<Integer>(); //存放连续的序列
        int maxLen = 1;
        for (int i = 0; i < n ; i++) {
            if (hmap.containsKey(nums[i] - 1)){
                hmap.put(nums[i] - 1, nums[i]); //找到nums[i]的前驱节点nums[i]-1
            }
            if (hmap.containsKey(nums[i] + 1)){
                hmap.put(nums[i], nums[i]+1); //找到nums[i]的后继节点nums[i]+1
            } else {
                hmap.put(nums[i],null); //nums[i]还没有后继节点
            }
        }

        for (Integer k: hmap.keySet()){
            if (hmap.get(k) == null){
                continue;
            }
            consList.clear();
            Integer tmp = k;
            while (tmp != null){
                consList.add(tmp);
                tmp = hmap.get(tmp);
                //hmap.put(tmp-1, null);
            }
            maxLen = Math.max(maxLen, consList.size());
        }
        return maxLen;
    }

    public int longestConsecutive1(int[] nums){
        /**
         * 用哈希表存放每个端点值对应的连续区间的长度
         */
        int n = nums.length;
        if (n <= 1){
            return n;
        }
        Map<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        int maxLen = 1;
        //遍历数组nums
        for (int i = 0; i < n ; i++) {
            if (hmap.containsKey(nums[i])){ //nums[i]已存在，跳过不作处理
                continue;
            }
            //计算nums[i]左边、右边相邻数已有的区间长度：left、right
            int left = 0;
            if (hmap.containsKey(nums[i] - 1)){ //哈希表中要存在nums[i]-1
                left = hmap.get(nums[i]-1);
            }
            int right = 0;
            if (hmap.containsKey(nums[i] + 1)){ //同样，nums[i]+1也要存在于哈希表中
                right = hmap.get(nums[i] + 1);
            }
            int curLen = left + right + 1; //计算当前连续区间长度
            maxLen = Math.max(curLen, maxLen); //更新最大长度

            if (hmap.containsKey(nums[i]-1)) //更新区间左端点的长度值
                hmap.put(nums[i] - left, curLen);
//            else
//                hmap.put(nums[i],curLen);

            if (hmap.containsKey(nums[i]+1)) //更新区间右端点的长度值
                hmap.put(nums[i] + right, curLen);
//            else
            hmap.put(nums[i],curLen);
        }
        return maxLen;
    }
    public static void main(String[] args) {
        int[] nums = {4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};
        Solution sol = new Solution();
        System.out.println(sol.longestConsecutive1(nums));
    }
}
