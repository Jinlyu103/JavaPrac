package countBinarySubstrings;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 696. 计数二进制子串
     * 给定一个只包含0和1 的字符串，计算具有相同数量的0和1的非空（连续）子字符串的数量，
     * 并且这些子字符串中的所有0和所有1都是组合在一起的
     */
    public int countBinarySubstrings(String s){
        /**
         * 用一个数组counts记录字符串中连续的0、1的个数：
         * 如00110011,counts={2,2,2,2}
         * 那么counts中相邻的元素一定是不同的字符的个数，即要么为0要么为1，设为u,v
         * 则为u个0/1,v个1/0
         * 那么其构成满足条件的子串个数为：min(u,v)
         * 因此，求出了counts数组之后，需要遍历counts中相邻的元素
         */
        int len = s.length();
        if (len <= 1){
            return 0;
        }
        List<Integer> counts = new ArrayList<>();
        int res = 0;
        char pre = s.charAt(0);
        int cntPre = 1;
        for (int i = 1; i<len; i++){
            if (s.charAt(i) != pre){
                counts.add(cntPre);
                pre = s.charAt(i);
                cntPre = 1;
                continue;
            }
            cntPre ++;
        }
        counts.add(cntPre);
        for (int i = 1; i<counts.size(); i++){
            res += Math.min(counts.get(i-1), counts.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "00110011";
        System.out.println(sol.countBinarySubstrings(s));

    }
}
