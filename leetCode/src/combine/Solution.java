package combine;

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 给定两个整数n和k，返回1，。。。n中所有可能的k个数的组合
     * c（n,k) = n!/k!(n-k)!
     */
    public List<List<Integer>> combine(int n, int k){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        backTrack(res, comb, n, k);
        return res;
    }

    public void backTrack(List<List<Integer>> res, List<Integer> comb, int n, int k){
        if (comb.size() == k){
            List<Integer> tmp = new ArrayList<>();
            for (Integer i:comb){
                tmp.add(i);
            }
            res.add(tmp);
            return;
        }

        for (int i = 1; i<=n; i++){
            if (!valid(i, comb)){
                continue;
            }
            comb.add(i);
            backTrack(res,comb,n,k);
            comb.remove(comb.size()-1);
        }
    }

    public boolean valid(int x, List<Integer> comb){
        if (comb.size() == 0){
            return true;
        }
        for (int i = 0; i<comb.size(); i++){
            if (x<=comb.get(i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.combine(4,2));
    }
}
