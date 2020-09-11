package combinationSum;

import javax.xml.transform.SourceLocator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        if (candidates.length == 0){
            return res;
        }

        Arrays.sort(candidates); //排序+剪枝
        backtrack(candidates,target,0,comb,res);
        return res;
    }

    public void backtrack(int[] candidates, int target, int sum, List<Integer> comb, List<List<Integer>> res){
        if (sum == target){
            List<Integer> tmp = new ArrayList<>();
            for (Integer c : comb) {
                tmp.add(c);
            }
            Collections.sort(tmp);
            if (!res.contains(tmp)) {
                res.add(tmp);
            }
            return;
        }

        for (int i = 0; i<candidates.length; i++){
            if ((sum + candidates[i]) > target){
                continue;
            }
            comb.add(candidates[i]);
            backtrack(candidates,target,sum+candidates[i], comb, res);
            comb.remove(comb.size()-1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] cands = {2,3,5};
        System.out.println(sol.combinationSum(cands, 8));
    }
}
