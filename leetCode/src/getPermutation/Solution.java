package getPermutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    /**
     * 60. 第k个排列
     * 给出集合{1,2,3，...，n}
     * 按大小顺序排列出所有排列情况
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k){
        if (n == 1 && k == 1){
            return "1";
        }

        List<String> arr = new ArrayList<>();
        HashSet<Integer> nums = new HashSet<>();
        String res = backTrack(n, k, arr, nums, "");
        return res;
    }

    //回溯：191/200,最后9个测试用例超时
    //只剪去右侧分支
    public String backTrack(int n, int k, List<String> arr, HashSet<Integer> nums, String s){
        if (s.length() == n){
            arr.add(s);
            if (arr.size() == k){
                return arr.get(k-1);
            } else {
                return "";
            }
        }

        for (int i = 1; i<n+1; i++){
            if (nums.contains(i)){
                continue;
            }
            nums.add(i);
            String ret = backTrack(n,k,arr, nums, s+Integer.toString(i));
            if (ret.length() == n){
                return ret;
            }
            nums.remove(i);
        }
        return "";
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.getPermutation(3,3));
    }
}
