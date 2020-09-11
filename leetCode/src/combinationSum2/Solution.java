package combinationSum2;

import java.util.*;

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target){
        List<List<Integer>> resIdx = new ArrayList<>();
        HashSet<Integer> comb = new HashSet<>(); //存放元素下标

        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0){
            return res;
        }

        backtrack(candidates, target, resIdx, comb);

        for (int i = 0; i<resIdx.size(); i++) {
            List<Integer> list = resIdx.get(i);
            List<Integer> combNum = new ArrayList<Integer>();
            for (int j = 0; j < list.size(); j++) {
                combNum.add(candidates[list.get(j)]);
            }
            Collections.sort(combNum);
            if (!res.contains(combNum))
                res.add(combNum);
        }

        return res;
    }

    public void backtrack(int[] candidates, int target, List<List<Integer>> res, HashSet<Integer> comb){
        if (target == 0){
            List<Integer> tmpIdx = new ArrayList<>();
            for (Integer c: comb){
                tmpIdx.add(c);
            }
            Collections.sort(tmpIdx);
            if (! res.contains(tmpIdx)){
                res.add(tmpIdx);
            }
            return;
        }

        for (int i = 0; i<candidates.length; i++){
            if (comb.contains(i) || (target - candidates[i] < 0)){
                continue;
            }
            comb.add(i);
            backtrack(candidates,target-candidates[i], res, comb);
            comb.remove(i);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {10,1,2,7,6,1,5};
        System.out.println(sol.combinationSum2(nums, 8));
    }
}
